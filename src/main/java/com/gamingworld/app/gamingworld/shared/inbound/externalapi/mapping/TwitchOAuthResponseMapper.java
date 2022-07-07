package com.gamingworld.app.gamingworld.shared.inbound.externalapi.mapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamingworld.app.gamingworld.shared.inbound.externalapi.resource.TwitchOAuthResponseResource;

public class TwitchOAuthResponseMapper {

    public TwitchOAuthResponseResource toResource(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonString, TwitchOAuthResponseResource.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
