package com.argusoft.abdmhackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.argusoft.emcare")
@EntityScan(basePackages = "com.argusoft.emcare")
public class AbdmHackathonApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AbdmHackathonApplication.class, args);
    }

}
