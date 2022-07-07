package com.gamingworld.app.gamingworld.shared.inbound.game.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("gameMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public GameMapper gameMapper(){ return new GameMapper(); }
}
