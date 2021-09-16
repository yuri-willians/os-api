package com.yuri.os.config;

import com.yuri.os.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    // @Value("${spring.jpa.hibernate.ddl-auto}")
    // private String ddl;

    @Bean
    public void instanciaDB() {
        // if (ddl.equals("create")) {
            this.dbService.instanciaDB();
        // }
    }
}
