package com.example.weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import javafx.scene.control.TextArea;

public class WeatherService {

    private static final String API_KEY = "5354919fd2f702177e0bf46036158b74"; // Sua chave de API do OpenWeatherMap
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private TextArea resultArea;

    public WeatherService(TextArea resultArea) {
        this.resultArea = resultArea;
    }

    public void fetchWeather(String city) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::parseAndDisplay)
                .exceptionally(e -> {
                    resultArea.setText("Error: " + e.getMessage());
                    return null;
                });
    }

    private void parseAndDisplay(String response) {
        Gson gson = new Gson();
        WeatherResponse weatherResponse = gson.fromJson(response, WeatherResponse.class);
        if (weatherResponse != null) {
            resultArea.setText("City: " + weatherResponse.getName() +
                    "\nTemperature: " + weatherResponse.getMain().getTemp() + " °C" +
                    "\nWeather: " + weatherResponse.getWeather().get(0).getDescription());
        } else {
            resultArea.setText("Error: Unable to fetch weather data.");
        }
    }
}
