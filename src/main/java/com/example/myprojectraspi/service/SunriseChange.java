package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.Sensor;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.SensorRepository;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Configuration
public class SunriseChange {

    public static final int DIGITAL_INPUT_PIN = 22;

    public static DigitalState digitalStateInput;

    private ShadeRepository shadeRepository;

    private ShadeService shadeService;

    private SensorRepository sensorRepository;

    public SunriseChange(ShadeRepository shadeRepository, ShadeService shadeService, SensorRepository sensorRepository) {
        this.shadeRepository = shadeRepository;
        this.shadeService = shadeService;
        this.sensorRepository = sensorRepository;
    }
//  Input of sensor and calling move method from shadeService

    public void changeInput() {

        final var console = new Console();

        Sensor sensor = sensorRepository.findBySensorType("light");


        var config = DigitalInput.newConfigBuilder(shadeService.pi4j())
                .address(sensor.getAddressSensorInput())
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input");


        var input = shadeService.pi4j().create(config);
        input.addListener(e -> {
            DigitalState state = e.state();
            System.out.println(state);
                sensor.setStatus(state);
            System.out.println(state);
            sensorRepository.save(sensor);
            for (ShadeEntity shadeEntity: shadeRepository.findAll()) {
                if (sensor.getStatus() == DigitalState.HIGH) {
                    if (shadeEntity.getValue() != 100) {
                        shadeService.moveShade(shadeEntity.getId1(), 100);
                    }

                } else if (sensor.getStatus() == DigitalState.LOW) {
                    if (shadeEntity.getValue() != 0) {
                        shadeService.moveShade(shadeEntity.getId1(), 0);
                    }
                }

            }
            System.out.println(sensor.getStatus());
                sensorRepository.save(sensor);
            System.out.println(sensor.getStatus());
                });


        shadeService.pi4j().shutdown();

    }
}


