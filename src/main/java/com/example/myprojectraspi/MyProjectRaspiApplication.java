package com.example.myprojectraspi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyProjectRaspiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyProjectRaspiApplication.class, args);
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
