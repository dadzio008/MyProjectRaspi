package com.example.myprojectraspi.service;

import Exceptions.ResourceNotFoundException;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.util.Console;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class ShadeService {
    private final ShadeRepository shadeRepository;


    public ShadeService(ShadeRepository shadeRepository) {
        this.shadeRepository = shadeRepository;
    }


    public void moveShade(Long id1, Integer value) {

        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        shadeEntity.setValue(value);
        shadeRepository.save(shadeEntity);
        int changedValueToMilisecons = (int) ((shadeEntity.getValue() * (shadeEntity.getTimeToOpenAndCloseShade()) * 10));

        if (changedValueToMilisecons > shadeEntity.getStatus()) {
            int finalValue = changedValueToMilisecons - shadeEntity.getStatus();
            var pi4j = Pi4J.newAutoContext();
            var moveShade = DigitalOutput.newConfigBuilder(pi4j)
                    .id(shadeEntity.getId())
                    .name(shadeEntity.getName())
                    .address(shadeEntity.getAddressClose())
                    .shutdown(DigitalState.HIGH)
                    .initial(DigitalState.HIGH)
                    .provider(shadeEntity.getProvider());
            var move = pi4j.create(moveShade);
            System.out.println(finalValue);
            move.pulseLow(finalValue, TimeUnit.SECONDS);
            shadeEntity.setStatus(changedValueToMilisecons);
            shadeRepository.save(shadeEntity);
            pi4j.shutdown();
        } else if (changedValueToMilisecons < shadeEntity.getStatus()) {
            System.out.println(changedValueToMilisecons);
            System.out.println(shadeEntity.getStatus());
            int finalValue = shadeEntity.getStatus() - changedValueToMilisecons;

            var pi4j = Pi4J.newAutoContext();
            var moveShade = DigitalOutput.newConfigBuilder(pi4j)
                    .id(shadeEntity.getId())
                    .name(shadeEntity.getName())
                    .address(shadeEntity.getAddressOpen())
                    .shutdown(DigitalState.HIGH)
                    .initial(DigitalState.HIGH)
                    .provider(shadeEntity.getProvider());
            var move = pi4j.create(moveShade);
            System.out.println(finalValue);
            move.pulseLow(finalValue, TimeUnit.SECONDS);
            shadeEntity.setStatus(changedValueToMilisecons);
            shadeRepository.save(shadeEntity);
            pi4j.shutdown();
        }
    }
}

