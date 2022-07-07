package com.gamingworld.app.gamingworld.profile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public ProfileMapper profileMapper(){ return new ProfileMapper(); }
}
