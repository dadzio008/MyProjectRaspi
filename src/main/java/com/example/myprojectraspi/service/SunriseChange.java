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
        var sunriseInput = Pi4J.newAutoContext();


        var config = DigitalInput.newConfigBuilder(sunriseInput)
                .id("input")
                .name("input")
                .address(22)
                .pull(PullResistance.PULL_DOWN)
                .build();


        DigitalInputProvider digitalInputProvider = sunriseInput.provider("pigpio-digital-input");

        var input = digitalInputProvider.create(config);

        input.addListener(e -> {
            DigitalState state = (e.state());
            List<ShadeEntity> shadeEntityList = shadeRepository.findAll();
            System.out.println(shadeEntityList.size());
            changeOutput(state);


        });


    }

    public void changeOutput(DigitalState state) {
        for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
            System.out.println(shadeEntity.getId1());
            if (state.equals(DigitalState.LOW)) {
                if (shadeEntity.getStatus() > 0) {

                    var sunriseOutput = Pi4J.newAutoContext();


                    var pinOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
                            .id(shadeEntity.getId())
                            .name(shadeEntity.getName())
                            .address(shadeEntity.getAddressOpen())
                            .shutdown(DigitalState.HIGH)
                            .initial(DigitalState.HIGH)
                            .provider("pigpio-digital-output");
                    var shadeMove = sunriseOutput.create(pinOutputConfig);
                    shadeMove.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
                    shadeEntity.setStatus(0);
                    sunriseOutput.shutdown();
                }else {
                    System.out.println("Nothing happened");
                }
            }else if (state.equals(DigitalState.HIGH)){
                if (shadeEntity.getStatus() < shadeEntity.getTimeToOpenAndCloseShade()) {
                    int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
                    var sunriseOutput = Pi4J.newAutoContext();
                    int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);

                    var pinOutputConfig = DigitalOutput.newConfigBuilder(sunriseOutput)
                            .id(shadeEntity.getId())
                            .name(shadeEntity.getName())
                            .address(shadeEntity.getAddressClose())
                            .shutdown(DigitalState.HIGH)
                            .initial(DigitalState.HIGH)
                            .provider("pigpio-digital-output");
                    var shadeMove = sunriseOutput.create(pinOutputConfig);
                    shadeMove.pulseLow(timeCloseShade, TimeUnit.SECONDS);
                    shadeEntity.setStatus(setStatus);
                    sunriseOutput.shutdown();
                } else {
                    System.out.println("Nothing happened");
                }
            } else {
                throw new RuntimeException("Something gone wrong");
            }
        }

    }
}
