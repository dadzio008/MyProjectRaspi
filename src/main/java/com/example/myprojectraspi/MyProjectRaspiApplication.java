package com.example.myprojectraspi;


import com.pi4j.Pi4J;
import com.pi4j.util.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyProjectRaspiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyProjectRaspiApplication.class, args);
        final var console = new Console();
        var pi4j = Pi4J.newAutoContext();
    }


//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        Connector ajpConnector = new Connector("AJP/1.3");
//        ajpConnector.setPort(9090);
//        ajpConnector.setSecure(false);
//        ajpConnector.setAllowTrace(false);
//        ajpConnector.setScheme("http");
//        ((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
//        tomcat.addAdditionalTomcatConnectors(ajpConnector);
//        return tomcat;
//    }

}
