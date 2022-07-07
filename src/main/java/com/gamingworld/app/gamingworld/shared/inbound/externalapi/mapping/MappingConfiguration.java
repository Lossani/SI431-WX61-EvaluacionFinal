package com.gamingworld.app.gamingworld.shared.inbound.externalapi.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("externalAPIMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public TwitchOAuthResponseMapper twitchOAuthResponseMapper(){ return new TwitchOAuthResponseMapper(); }

    @Bean
    public ExternalAPIMapper externalAPIMapper(){ return new ExternalAPIMapper(); }
}
