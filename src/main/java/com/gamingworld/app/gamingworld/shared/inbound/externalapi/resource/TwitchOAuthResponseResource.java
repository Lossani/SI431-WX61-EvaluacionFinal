package com.gamingworld.app.gamingworld.shared.inbound.externalapi.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TwitchOAuthResponseResource {
    @NotNull
    private String access_token;

    @NotNull
    private Long expires_in;

    @NotNull
    private String token_type;
}
