package com.example.weather;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WeatherAppController {

    @FXML
    private TextField cityInput;

    @FXML
    private TextArea resultArea;

    @FXML
    private void handleFetchWeather() {
        String city = cityInput.getText().trim();
        if (!city.isEmpty()) {
            new WeatherService(resultArea).fetchWeather(city);
        } else {
            resultArea.setText("Please enter a city name.");
        }
    }
}
