package com.natergy.natergyh5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan(basePackages = {"com.natergy.natergyh5.filter"})
@SpringBootApplication
@ComponentScan("com.natergy")
public class NatergyH5Application {

    public static void main(String[] args) {
        SpringApplication.run(NatergyH5Application.class, args);
    }

}
