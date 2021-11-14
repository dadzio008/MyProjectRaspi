package com.example.myprojectraspi.service;

import Exceptions.ResourceNotFoundException;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class ShadeService {
    private final ShadeRepository shadeRepository;


    public ShadeService(ShadeRepository shadeRepository) {
        this.shadeRepository = shadeRepository;
    }

    //moving shade
    public void moveShade(Long id1, Integer value) {

        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        shadeEntity.setValue(value);
        shadeRepository.save(shadeEntity);
        int changedValueToMilisecons = (int) ((shadeEntity.getValue() * (shadeEntity.getTimeToOpenAndCloseShade()) * 10));

        if (changedValueToMilisecons > shadeEntity.getStatus()) {
            int finalValue = changedValueToMilisecons - shadeEntity.getStatus();
            var moveShade = DigitalOutput.newConfigBuilder(pi4j())
                    .id(shadeEntity.getId())
                    .name(shadeEntity.getName())
                    .address(shadeEntity.getAddressClose())
                    .shutdown(DigitalState.HIGH)
                    .initial(DigitalState.HIGH)
                    .provider(shadeEntity.getProvider());
            var move = pi4j().create(moveShade);
            System.out.println(finalValue);
            move.pulseLow(finalValue, TimeUnit.SECONDS);
            shadeEntity.setStatus(changedValueToMilisecons);
            shadeRepository.save(shadeEntity);

        } else if (changedValueToMilisecons < shadeEntity.getStatus()) {
            System.out.println(changedValueToMilisecons);
            System.out.println(shadeEntity.getStatus());
            int finalValue = shadeEntity.getStatus() - changedValueToMilisecons;


            var moveShade = DigitalOutput.newConfigBuilder(pi4j())
                    .id(shadeEntity.getId())
                    .name(shadeEntity.getName())
                    .address(shadeEntity.getAddressOpen())
                    .shutdown(DigitalState.HIGH)
                    .initial(DigitalState.HIGH)
                    .provider(shadeEntity.getProvider());
            var move = pi4j().create(moveShade);
            System.out.println(finalValue);
            move.pulseLow(finalValue, TimeUnit.SECONDS);
            shadeEntity.setStatus(changedValueToMilisecons);
            shadeRepository.save(shadeEntity);

        }
    }

//    public void close() {
//        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//            if (shadeEntity.getStatus() > 0) {
//                var sunriseOutput = Pi4J.newAutoContext();
//                var pinCloseOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                        .id(shadeEntity.getId())
//                        .name(shadeEntity.getName())
//                        .address(shadeEntity.getAddressClose())
//                        .shutdown(DigitalState.HIGH)
//                        .initial(DigitalState.HIGH)
//                        .provider("pigpio-digital-input");
//                var shadeMoveClose = sunriseOutput.create(pinCloseOutputConfig);
//                shadeMoveClose.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
//                shadeEntity.setStatus(setStatus);
//                sunriseOutput.shutdown();
//            }
//        }
//    }
//
//    public void open() {
//        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//            if (shadeEntity.getStatus() > 0) {
//                var sunriseOutput = Pi4J.newAutoContext();
//                var pinOpenOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                        .id(shadeEntity.getId())
//                        .name(shadeEntity.getName())
//                        .address(shadeEntity.getAddressOpen())
//                        .shutdown(DigitalState.HIGH)
//                        .initial(DigitalState.HIGH)
//                        .provider("pigpio-digital-input");
//                var shadeMoveClose = sunriseOutput.create(pinOpenOutputConfig);
//                shadeMoveClose.pulseLow(timeCloseShade, TimeUnit.MILLISECONDS);
//                shadeEntity.setStatus(0);
//                sunriseOutput.shutdown();
//            }
//        }
//    }
    public static Context pi4j() {
        var pi4j = Pi4J.newAutoContext();
        return pi4j;
    }
}

