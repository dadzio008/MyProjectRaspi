package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SunriseChange {

    public static final int DIGITAL_INPUT_PIN = 22;

    public static DigitalState digitalStateInput;

    private final ShadeRepository shadeRepository;

    private final ShadeService shadeService;

    public SunriseChange(ShadeRepository shadeRepository, ShadeService shadeService) {
        this.shadeRepository = shadeRepository;
        this.shadeService = shadeService;
    }


    public void changeInput() {

        final var console = new Console();
        var sunriseInput = Pi4J.newAutoContext();


        var config = DigitalInput.newConfigBuilder(sunriseInput)
                .address(DIGITAL_INPUT_PIN)
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input");


        var input = sunriseInput.create(config);
        input.addListener(e -> {
            if (e.state() == DigitalState.LOW) {
                shadeService.open();
//                        open();
//                        System.out.println(input.state() + "open");
//                        System.out.println(input.state().toString() + "String");
//                        System.out.println(e.state() + "state");

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

            } else {
                shadeService.close();

//                        System.out.println(input.state() + "closed");
//                        System.out.println(input.state().toString() + "String");
//                        System.out.println(e.state().value() + "1");
//                        System.out.println(e.state() + "2");
//                for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//                    int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//                    int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//                    System.out.println(shadeEntity.getId1());
//                    System.out.println(shadeEntity.getStatus());
//                    System.out.println(shadeEntity.getValue());
//                    System.out.println("-------------");
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

//    public void open() {
//        var sunriseOutput = Pi4J.newAutoContext();
//        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//            if (shadeEntity.getStatus() > 0) {
//                var pinOOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                        .id(shadeEntity.getId())
//                        .name(shadeEntity.getName())
//                        .address(shadeEntity.getAddressOpen())
//                        .shutdown(DigitalState.HIGH)
//                        .initial(DigitalState.HIGH)
//                        .provider(shadeEntity.getProvider());
//                DigitalOutputProvider digitalOutputProvider = sunriseOutput.provider(shadeEntity.getProvider());
//                var shadeMoveOpen = digitalOutputProvider.create(pinOOutputConfig);
//                shadeMoveOpen.pulseLow(timeCloseShade, TimeUnit.MILLISECONDS);
//                shadeEntity.setStatus(0);
//            } else {
//                System.err.println("Something gone wrong1");
//            }
//        }
//        sunriseOutput.shutdown();
//    }
//
//    public void close() {
//        var sunriseOutput = Pi4J.newAutoContext();
//        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
//
//            int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
//            int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
//            if (shadeEntity.getStatus() > 0) {
//                var pinCOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
//                        .id(shadeEntity.getId())
//                        .name(shadeEntity.getName())
//                        .address(shadeEntity.getAddressClose())
//                        .shutdown(DigitalState.HIGH)
//                        .initial(DigitalState.HIGH)
//                        .provider(shadeEntity.getProvider());
//                DigitalOutputProvider digitalOutputProvider = sunriseOutput.provider(shadeEntity.getProvider());
//                var shadeMoveClose = digitalOutputProvider.create(pinCOutputConfig);
//                shadeMoveClose.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
//                shadeEntity.setStatus(setStatus);
//            } else {
//                System.err.println("Something gone wrong2");
//            }
//        }
//        sunriseOutput.shutdown();
//        System.out.println("END");
//
//
//    }
    }
}


