package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.util.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class ShadeService {
    private final ShadeRepository shadeRepository;

    @Autowired
    public ShadeService(ShadeRepository shadeRepository){
        this.shadeRepository = shadeRepository;
    }
    public static void shadeOpening(String id, ) {
        final var console = new Console();
        var pi4j = Pi4J.newAutoContext();

        var openShadeBedRoom = DigitalOutput.newConfigBuilder(pi4j)
                this.id = .id()
                .name("Przełącznik")
                .address(21)
                .shutdown(DigitalState.HIGH)
                .initial(DigitalState.HIGH)
                .provider("pigpio-digital-output");
        var shadeOpenBedroom = pi4j.create(openShadeBedRoom);
        shadeOpenBedroom.high();
        shadeOpenBedroom.pulse(20, TimeUnit.SECONDS);
    }
    public List<ShadeEntity> findAllShades() {
        return shadeRepository.findAll();
    }

    public ShadeEntity addShade(ShadeEntity shadeEntity){
        return shadeRepository.save(shadeEntity);
    }


    public void deleteShade(Long id){
        shadeRepository.deleteShadeEntityById(id);
    }
}
