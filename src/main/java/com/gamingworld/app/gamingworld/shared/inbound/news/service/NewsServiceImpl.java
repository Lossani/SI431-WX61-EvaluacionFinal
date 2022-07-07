package com.gamingworld.app.gamingworld.shared.inbound.news.service;

import com.gamingworld.app.gamingworld.shared.inbound.news.domain.service.NewsService;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class NewsServiceImpl implements NewsService {
    private static final String NEWS_API_URL = "https://newsapi.org/v2/everything?q=";
    private static final String NEWS_API_TOKEN = "&language=es&apiKey=30a01aa6438d4782906f35bb2f136a91";

    @Override
    public String findByTheme(String theme) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpPost = new HttpGet(NEWS_API_URL + theme + NEWS_API_TOKEN);

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                return getResponseBodyFromRequest(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error trying to retrieve news from external API.";
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
}
