package com.example.myprojectraspi.Controller;


import Exceptions.ResourceNotFoundException;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.example.myprojectraspi.service.ShadeService;
import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.util.Console;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class ShadeController {

     private final ShadeService shadeService;

    private final ShadeRepository shadeRepository;

    public ShadeController(ShadeService shadeService, ShadeRepository shadeRepository){
        this.shadeService = shadeService;
        this.shadeRepository = shadeRepository;
        }

        @GetMapping("/shades/allShades")
        public ResponseEntity<List<ShadeEntity>> getAllShades(){

            List<ShadeEntity> allShades = shadeRepository.findAll();
            return new ResponseEntity<>(allShades, HttpStatus.OK);

        }
        @PostMapping("/shades/allShades")
    public ResponseEntity<ShadeEntity> addShade(@RequestBody ShadeEntity shadeEntity){
        ShadeEntity newEntity = shadeRepository.save(shadeEntity);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);

        }
        @DeleteMapping("/shades/allShades/{id1}")
    public ResponseEntity<Map<String, Boolean>> deleteEntity(@PathVariable Long id1) {
        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException("Shade not exist with id :" + id1));
        shadeRepository.delete(shadeEntity);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
        }
        @PutMapping("/shades/allShades/move/{id1}")
        public ResponseEntity<ShadeEntity> move(@PathVariable Long id1, @RequestParam Integer value) {
        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        shadeEntity.setValue(value);
        shadeRepository.save(shadeEntity);
            int changedValue = (((shadeEntity.getValue() * shadeEntity.getTimeToOpenAndCloseShade())/100));

            if (changedValue > shadeEntity.getStatus()){
                int finalValue = changedValue - shadeEntity.getStatus();
                final var console = new Console();
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
                shadeEntity.setStatus(changedValue);
                shadeRepository.save(shadeEntity);
                pi4j.shutdown();
            } else if (changedValue< shadeEntity.getStatus()){
                System.out.println(changedValue);
                System.out.println(shadeEntity.getStatus());
                int finalValue = shadeEntity.getStatus()-changedValue;
                final var console = new Console();
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
                shadeEntity.setStatus(changedValue);
                shadeRepository.save(shadeEntity);
                pi4j.shutdown();
            }

        return ResponseEntity.ok(shadeEntity);
        }


}
