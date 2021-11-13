package com.example.myprojectraspi;


import com.example.myprojectraspi.service.SunriseChange;
import com.pi4j.util.Console;
import org.hibernate.id.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication


public class MyProjectRaspiApplication {

    public static void main(String[] args) {
           ConfigurableApplicationContext app = SpringApplication.run(MyProjectRaspiApplication.class, args);

//        SunriseChange sunriseChange = (SunriseChange)app.getBean("sunriseChange");
//
//        sunriseChange.changeInput();
    }




}
