package com.gamingworld.app.gamingworld.shared.inbound.news.api;

import com.gamingworld.app.gamingworld.shared.inbound.news.domain.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/news")
@Tag(name = "News Controller", description = "Gateway to external news API for gaming related news.")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping(path = "", params = {"theme"})
    @Operation(summary = "Retrieves a full list of news by their subject.", description = "Retrieves a full list of news by their subject.")
    public String findByName(@RequestParam("theme") String theme) {
        return newsService.findByTheme(theme);
    }

    @GetMapping(path = "")
    @Operation(summary = "Retrieves a full list of news by Gaming subject.", description = "Retrieves a full list of news by Gaming subject.")
    public String findByName() {
        return newsService.findByTheme("Gaming");
    }
}
