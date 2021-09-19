package com.example.myprojectraspi.service;

import com.example.myprojectraspi.PrintInfo;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputProvider;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import org.apache.catalina.util.ErrorPageSupport;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SunriseChange {


    private final ShadeRepository shadeRepository;

    public SunriseChange(ShadeRepository shadeRepository){
        this.shadeRepository = shadeRepository;
    }

    public void changeInput(ShadeEntity shadeEntity) {
        final var console = new Console();
        var pi4j = Pi4J.newAutoContext();
        Platforms platforms = pi4j.platforms();

        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();
        AtomicInteger pressCount = new AtomicInteger();
        PrintInfo.printLoadedPlatforms(console, pi4j);
        PrintInfo.printDefaultPlatform(console, pi4j);
        PrintInfo.printProviders(console, pi4j);

        var config = DigitalInput.newConfigBuilder(pi4j)
                .id("input")
                .name("input")
                .address(22)
                .pull(PullResistance.PULL_DOWN)
                .build();


        DigitalInputProvider digitalInputProvider = pi4j.provider("pigpio-digital-input");

        var input = digitalInputProvider.create(config);

        List<ShadeEntity> shadeEntityList = shadeRepository.findAll();




    }
    public void changeOutput() {

    }

}
