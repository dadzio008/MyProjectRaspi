package com.example.myprojectraspi.service;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SunriseChange {


    private final ShadeRepository shadeRepository;

    public SunriseChange(ShadeRepository shadeRepository) {
        this.shadeRepository = shadeRepository;
    }

    @Scheduled(fixedRate = 600)
    public void changeInput() {
        final var console = new Console();
        var pi4j = Pi4J.newAutoContext();


        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("input")
                .name("input")
                .address(22)
                .pull(PullResistance.PULL_DOWN)
                .build();


        DigitalInputProvider digitalInputProvider = pi4j.provider("pigpio-digital-input");

        var input = digitalInputProvider.create(config);

        input.addListener(e -> {
            DigitalState state = (e.state());
            List<ShadeEntity> shadeEntityList = shadeRepository.findAll();
            for (int i = 1; i < shadeEntityList.size(); i++) {
                changeOutput(state);
            }

        });


    }

    public void changeOutput(DigitalState state) {
        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
            if (state.equals(DigitalState.LOW)) {
                if (shadeEntity.getStatus() > 0) {

                    var pi4j = Pi4J.newAutoContext();


                    var pinOutputConfig = DigitalOutput.newConfigBuilder(pi4j)
                            .id(shadeEntity.getId())
                            .name(shadeEntity.getName())
                            .address(shadeEntity.getAddressOpen())
                            .shutdown(DigitalState.HIGH)
                            .initial(DigitalState.HIGH)
                            .provider("pigpio-digital-output");
                    var shadeMove = pi4j.create(pinOutputConfig);
                    shadeMove.pulseLow(shadeEntity.getStatus(), TimeUnit.SECONDS);
                    shadeEntity.setStatus(0);
                    pi4j.shutdown();
                }else {
                    System.out.println("Nothing happened");
                }
            }else if (state.equals(DigitalState.HIGH)){
                if (shadeEntity.getStatus() < shadeEntity.getTimeToOpenAndCloseShade()) {
                    int timeCloseShade = shadeEntity.getTimeToOpenAndCloseShade() - shadeEntity.getStatus();
                    var pi4j = Pi4J.newAutoContext();


                    var pinOutputConfig = DigitalOutput.newConfigBuilder(pi4j)
                            .id(shadeEntity.getId())
                            .name(shadeEntity.getName())
                            .address(shadeEntity.getAddressClose())
                            .shutdown(DigitalState.HIGH)
                            .initial(DigitalState.HIGH)
                            .provider("pigpio-digital-output");
                    var shadeMove = pi4j.create(pinOutputConfig);
                    shadeMove.pulseLow(timeCloseShade, TimeUnit.SECONDS);
                    shadeEntity.setStatus(shadeEntity.getTimeToOpenAndCloseShade());
                    pi4j.shutdown();
                } else {
                    System.out.println("Nothing happened");
                }
            } else {
                throw new RuntimeException("Something gone wrong");
            }
        }

    }
}
