package com.gamingworld.app.gamingworld.shared.inbound.game.service;

import com.gamingworld.app.gamingworld.shared.inbound.externalapi.domain.model.entity.ExternalAPI;
import com.gamingworld.app.gamingworld.shared.inbound.externalapi.domain.persistence.ExternalAPIRepository;
import com.gamingworld.app.gamingworld.shared.inbound.externalapi.mapping.ExternalAPIMapper;
import com.gamingworld.app.gamingworld.shared.inbound.externalapi.mapping.TwitchOAuthResponseMapper;
import com.gamingworld.app.gamingworld.shared.inbound.externalapi.resource.TwitchOAuthResponseResource;
import com.gamingworld.app.gamingworld.shared.inbound.game.domain.model.entity.Game;
import com.gamingworld.app.gamingworld.shared.inbound.game.domain.service.GameService;
import com.gamingworld.app.gamingworld.shared.inbound.game.mapping.GameMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private static final String TWITCH_API_CREDENTIALS_URL = "https://id.twitch.tv/oauth2/token?client_id=8en9cck6wbdrkinl4i0oahhxf3ali1&client_secret=jh7gihohaly38ds1e0v98xcnntn7wr&grant_type=client_credentials";
    private static final String IGDB_GAMES_ENDPOINT = "https://api.igdb.com/v4/games";
    private static final String TWITCH_TOP_GAMES_URL = "https://api.twitch.tv/helix/games/top";

    @Autowired
    private ExternalAPIRepository externalAPIRepository;

    @Autowired
    private TwitchOAuthResponseMapper twitchOAuthResponseMapper;

    @Autowired
    private ExternalAPIMapper externalAPIMapper;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public List<Game> getRandomList(Integer limit) {
        String result = makeRequestToIGDB("fields name, cover.url; sort date desc; limit " + limit + ";");

        return gameMapper.toModelList(result);
    }

    @Override
    public Optional<Game> findById(Long id) {
        String result = makeRequestToIGDB("fields name, cover.url; where id=" + id + ";");

        result = result.substring(1, result.length() - 1); // We get response as an Array, so we need to remove those brackets

        Game gameRetrieved = gameMapper.toModel(result);

        return Optional.ofNullable(gameRetrieved);
    }

    @Override
    public List<Game> findByName(String name, Integer limit)
    {
        if (name == null || name.isEmpty())
        {
            return List.of();
        }

        String result = makeRequestToIGDB("fields name, cover.url; search \"" + name + "\"; limit " + limit + ";");

        return gameMapper.toModelList(result);
    }


    @Override
    public String getTopTwitchGames(Integer limit) {

        ExternalAPI credentials = getIGDBCredentials();

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpPost = new HttpGet(TWITCH_TOP_GAMES_URL + "?first=" + limit);

            httpPost.setHeader("Authorization", "Bearer " + credentials.getToken());
            httpPost.setHeader("Client-ID", "8en9cck6wbdrkinl4i0oahhxf3ali1");

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                return getResponseBodyFromRequest(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error at request made to external API Twitch.";
        }
    }

    public void getNewIGDBCredentials() {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(TWITCH_API_CREDENTIALS_URL);

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println("Response from Twitch OAuth API was: " + response.getCode());

                String result = getResponseBodyFromRequest(response);

                TwitchOAuthResponseResource twitchResponse = twitchOAuthResponseMapper.toResource(result);
                externalAPIRepository.save(externalAPIMapper.toModel(twitchResponse));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponseBodyFromRequest(CloseableHttpResponse response)
    {
        StringBuilder result = new StringBuilder();

        try (BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private String makeRequestToIGDB(String requestBody)
    {
        ExternalAPI credentials = getIGDBCredentials();

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(IGDB_GAMES_ENDPOINT);

            StringEntity requestEntity = new StringEntity(
                    requestBody,
                    ContentType.APPLICATION_JSON);

            httpPost.setEntity(requestEntity);
            httpPost.setHeader("Authorization", "Bearer " + credentials.getToken());
            httpPost.setHeader("Client-ID", "8en9cck6wbdrkinl4i0oahhxf3ali1");

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                return getResponseBodyFromRequest(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error at request made to external API IGDB.";
        }
    }

    private ExternalAPI getIGDBCredentials()
    {
        ExternalAPI credentials = externalAPIRepository.findByExternalAPIName("TWITCH_AUTH").size() != 0
                ? externalAPIRepository.findByExternalAPIName("TWITCH_AUTH").get(0) : null;

        if (credentials == null)
        {
            getNewIGDBCredentials();
        }
        else
        {
            Date today = new Date();

            if (today.getTime() >= credentials.getExpirationDate().getTime())
            {
                getNewIGDBCredentials();
                credentials = externalAPIRepository.findByExternalAPIName("TWITCH_AUTH").get(0);
            }
        }

        return credentials;
    }
}
