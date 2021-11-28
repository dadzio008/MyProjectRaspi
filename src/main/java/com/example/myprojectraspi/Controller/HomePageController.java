package com.example.myprojectraspi.Controller;

import com.example.myprojectraspi.model.Sensor;
import com.example.myprojectraspi.repository.SensorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Home page controller
@RestController
@RequestMapping("/Home")
public class HomePageController {

    private SensorRepository sensorRepository;





    public HomePageController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    //Getting light sensor to display that there is night or day outside
//    @GetMapping
//    public ResponseEntity<Sensor> getLightSensor(String sensorType) {
//        Sensor sensor = sensorRepository.findBySensorType(sensorType);
//        return new ResponseEntity<>(sensor, HttpStatus.OK);
//    }
    @GetMapping
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
}
