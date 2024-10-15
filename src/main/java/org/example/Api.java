package org.example;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class Api {
    private static final String apiKey = "17a5b9c2bf1a054ff4401ce5";
    private static final String baseCurrency = "USD";
    private String url;

    public Api() {
        this.url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
    }

    public Optional<JSONObject> getExchangeRates() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                return Optional.of(jsonResponse.getJSONObject("conversion_rates"));
            } else {
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
