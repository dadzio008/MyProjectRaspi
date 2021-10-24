package com.example.myprojectraspi.service;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.IOType;
import com.pi4j.io.binding.DigitalOutputBinding;
import com.pi4j.io.gpio.digital.*;
import com.pi4j.util.Console;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class SunriseChange {

    public static final int DIGITAL_INPUT_PIN=22;


    private final ShadeRepository shadeRepository;

    public SunriseChange(ShadeRepository shadeRepository) {
        this.shadeRepository = shadeRepository;
    }


    @Scheduled(fixedRate = 12000)
    public void changeInput() {

        final var console = new Console();
        var sunriseInput = Pi4J.newAutoContext();



            var config = DigitalInput.newConfigBuilder(sunriseInput)
//                    .id("my-digital-input")
                    .address(DIGITAL_INPUT_PIN)
                    .pull(PullResistance.PULL_DOWN)
                    .provider("pigpio-digital-input");

            // get a Digital Input I/O provider from the Pi4J context


            var input = sunriseInput.create(config);
        System.out.println("begin");
        input.addListener(e -> {
            for (ShadeEntity shadeEntity : shadeRepository.findAll()) {
                int timeCloseShade = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000) - shadeEntity.getStatus();
                int setStatus = (int) (shadeEntity.getTimeToOpenAndCloseShade() * 1000);
                DigitalState state = (e.state());
                Integer count = (Integer) e.source().metadata().get("count").value();
                console.println(e + " === " + count);
                if (state == DigitalState.LOW) {
                    System.out.println(e.state() + "open");
                    if (shadeEntity.getStatus() > 0) {
                        System.out.println(e.state().toString() + "open1");
                        var pinOOutputConfig = DigitalOutput.newConfigBuilder(sunriseInput)
                                .id(shadeEntity.getId())
                                .name(shadeEntity.getName())
                                .address(shadeEntity.getAddressOpen())
                                .shutdown(DigitalState.HIGH)
                                .initial(DigitalState.HIGH);
                        var shadeMoveOpen = sunriseInput.create(pinOOutputConfig);
                        shadeMoveOpen.pulseLow(timeCloseShade, TimeUnit.MILLISECONDS);
                        shadeEntity.setStatus(0);
                    } else {
                        System.err.println("Something gone wrong1");
                    }
                } else if (state == DigitalState.HIGH) {
                    if (shadeEntity.getStatus() < timeCloseShade) {
                        var pinCOutputConfig = DigitalOutput.newConfigBuilder(sunriseInput)
                                .id(shadeEntity.getId())
                                .name(shadeEntity.getName())
                                .address(shadeEntity.getAddressClose())
                                .shutdown(DigitalState.HIGH)
                                .initial(DigitalState.HIGH);
                        var shadeMoveClose = sunriseInput.create(pinCOutputConfig);

                        shadeMoveClose.pulseLow(shadeEntity.getStatus(), TimeUnit.MILLISECONDS);
                        shadeEntity.setStatus(setStatus);
                    } else {
                        System.err.println("Something gone wrong2");
                    }
                }
                System.out.println("END");


            }
        });


                console.print("THE STARTING DIGITAL INPUT STATE IS [");
        console.println(input.state() + "]");
        sunriseInput.shutdown();

    }

}
