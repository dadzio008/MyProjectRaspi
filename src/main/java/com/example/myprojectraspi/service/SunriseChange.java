package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.Sensor;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.SensorRepository;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
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
    @Scheduled(fixedDelay = 60000)
    public void changeInput() {

        final var console = new Console();
        var inputchange = Pi4J.newAutoContext();

        Sensor sensor = sensorRepository.findBySensorType("light");


        var config = DigitalInput.newConfigBuilder(inputchange)
                .address(sensor.getAddressSensorInput())
                .pull(PullResistance.PULL_DOWN)
                .provider("pigpio-digital-input");


        var input = inputchange.create(config);
        input.addListener(e -> {
                sensor.setStatus(e.state());
                });


        inputchange.shutdown();
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
    }
}


