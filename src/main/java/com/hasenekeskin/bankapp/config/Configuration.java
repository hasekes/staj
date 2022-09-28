package com.hasenekeskin.bankapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ModelMapper genarateModelMapper(){
        return new ModelMapper();
    }
}
