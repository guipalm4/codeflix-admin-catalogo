package com.guipalm4.codeflix.admin.catalogo.infrastructure;


import com.guipalm4.codeflix.admin.catalogo.infrastructure.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WebServerConfig.class, args);
    }
}
