package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class SunriseChange {

    public static final int DIGITAL_INPUT_PIN = 22;


    private final ShadeRepository shadeRepository;

    public SunriseChange(ShadeRepository shadeRepository) {
        this.shadeRepository = shadeRepository;
    }


    public void changeInput() {

        final var console = new Console();
        var sunriseInput = Pi4J.newAutoContext();


        var config = DigitalInput.newConfigBuilder(sunriseInput)
//                    .id("my-digital-input")
                .address(DIGITAL_INPUT_PIN)
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input");


        var input = sunriseInput.create(config);
        input.addListener(e -> {
                    if (e.state() == DigitalState.LOW) {
                        open();
                        System.out.println(input.state() + "open");
//                        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//                            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//                            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//
//                            var sunriseOutput = Pi4J.newAutoContext();
//                            if (shadeEntity.getStatus() > 0) {
//                                var pinOOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                                        .id(shadeEntity.getId())
//                                        .name(shadeEntity.getName())
//                                        .address(shadeEntity.getAddressOpen())
//                                        .shutdown(DigitalState.HIGH)
//                                        .initial(DigitalState.HIGH);
//                                var shadeMoveOpen = sunriseOutput.create(pinOOutputConfig);
//                                shadeMoveOpen.pulseLow(timeCloseShade, TimeUnit.MILLISECONDS);
//                                shadeEntity.setStatus(0);
//
//
//                            } else {
//                                System.err.println("Something gone wrong1");
//                            }
//                            sunriseOutput.shutdown();
//                        }
                    } else {
                        close();

                        System.out.println(input.state() + "closed");

//                        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//                            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//                            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//
//                            var sunriseOutput = Pi4J.newAutoContext();
//                            if (shadeEntity.getStatus() > 0) {
//                                var pinCOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                                        .id(shadeEntity.getId())
//                                        .name(shadeEntity.getName())
//                                        .address(shadeEntity.getAddressClose())
//                                        .shutdown(DigitalState.HIGH)
//                                        .initial(DigitalState.HIGH);
//                                var shadeMoveClose = sunriseOutput.create(pinCOutputConfig);
//                                shadeMoveClose.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
//                                shadeEntity.setStatus(setStatus);
//                            } else {
//                                System.err.println("Something gone wrong2");
//                            }
//                            sunriseOutput.shutdown();
//                        }
                    }

            });

        }

    public void open() {
        var sunriseOutput = Pi4J.newAutoContext();
        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
            if (shadeEntity.getStatus() > 0) {
                var pinOOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
                        .id(shadeEntity.getId())
                        .name(shadeEntity.getName())
                        .address(shadeEntity.getAddressOpen())
                        .shutdown(DigitalState.HIGH)
                        .initial(DigitalState.HIGH);
                var shadeMoveOpen = sunriseOutput.create(pinOOutputConfig);
                shadeMoveOpen.pulseLow(timeCloseShade, TimeUnit.MILLISECONDS);
                shadeEntity.setStatus(0);
            } else {
                System.err.println("Something gone wrong1");
            }
        }
        sunriseOutput.shutdown();
    }

    public void close() {
        var sunriseOutput = Pi4J.newAutoContext();
        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {

            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
            if (shadeEntity.getStatus() > 0) {
                var pinOOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
                        .id(shadeEntity.getId())
                        .name(shadeEntity.getName())
                        .address(shadeEntity.getAddressClose())
                        .shutdown(DigitalState.HIGH)
                        .initial(DigitalState.HIGH);
                var shadeMoveClose = sunriseOutput.create(pinOOutputConfig);
                shadeMoveClose.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
                shadeEntity.setStatus(setStatus);
            } else {
                System.err.println("Something gone wrong2");
            }
        }
        sunriseOutput.shutdown();
        System.out.println("END");


    }



}


