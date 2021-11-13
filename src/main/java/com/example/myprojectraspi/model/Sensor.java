package com.example.myprojectraspi.model;

import com.pi4j.io.gpio.digital.DigitalState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Sensors database table
@Data
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private DigitalState status;
    private Integer addressSensorInput;
    private String sensorType;
}
