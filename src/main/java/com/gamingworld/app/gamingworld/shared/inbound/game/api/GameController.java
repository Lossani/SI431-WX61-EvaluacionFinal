package com.gamingworld.app.gamingworld.shared.inbound.game.api;

import com.gamingworld.app.gamingworld.shared.inbound.game.domain.model.entity.Game;
import com.gamingworld.app.gamingworld.shared.inbound.game.domain.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/games")
@Tag(name = "Game Controller", description = "Gateway to external Game Database for game listing.")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @GetMapping(path = "")
    @Operation(summary = "Retrieves a random list of 10 games.", description = "Retrieves a random list of 10 games.")
    public List<Game> getRandomList() {
        return gameService.getRandomList(10);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Retrieves a specific game by its ID.", description = "Retrieves a specific game by its ID.")
    public Optional<Game> findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @GetMapping(path = "", params = {"find", "limit"})
    @Operation(summary = "Retrieves a list of games matching the given name.", description = "Retrieves a list of games matching the given name.")
    public List<Game> findByName(@RequestParam("find") String name, @RequestParam("limit") Integer limit) {
        if (limit == null)
            limit = 10;

        return gameService.findByName(name, limit);
    }

    @GetMapping(path = "/top", params = {"limit"})
    @Operation(summary = "Retrieves top games featured in Twitch, with a specified limit count.", description = "Retrieves top games featured in Twitch, with a specified limit count.")
    public String findTopGames(@RequestParam("limit") Integer limit) {
        return gameService.getTopTwitchGames(limit);
    }

    @GetMapping(path = "/top")
    @Operation(summary = "Retrieves top games featured in Twitch.", description = "Retrieves top games featured in Twitch.")
    public String findTopGames() {
        return gameService.getTopTwitchGames(5);
    }
}
