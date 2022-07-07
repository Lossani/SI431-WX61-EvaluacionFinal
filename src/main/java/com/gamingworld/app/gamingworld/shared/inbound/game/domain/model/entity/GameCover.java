package com.gamingworld.app.gamingworld.shared.inbound.game.domain.model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class GameCover {
    @NonNull private Long id;

    @NonNull private String url;
}
