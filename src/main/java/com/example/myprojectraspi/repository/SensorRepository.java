package com.example.myprojectraspi.repository;

import com.example.myprojectraspi.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findBySensorType(String sensorType);
}
