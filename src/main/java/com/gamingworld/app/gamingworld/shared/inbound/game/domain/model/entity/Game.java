package com.gamingworld.app.gamingworld.shared.inbound.game.domain.model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Game{

    @NonNull private Long id;

    @NonNull private String name;

    private GameCover cover;
}

