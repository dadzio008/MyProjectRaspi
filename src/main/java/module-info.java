module com.example.myprojectraspi {
    requires com.pi4j;
    requires com.pi4j.plugin.pigpio;

    // SLF4J MODULES
    requires org.slf4j;
    requires org.slf4j.simple;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.web;
    requires java.persistence;
    requires java.transaction;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.security.config;

    uses com.pi4j.extension.Extension;
    uses com.pi4j.provider.Provider;

    // allow access to classes in the following namespaces for Pi4J annotation processing
    opens com.example.myprojectraspi to com.pi4j;

}