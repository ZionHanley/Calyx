package com.calyx.datamodels;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class modelWeather {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    // Add your OpenWeatherMap API key here
    private static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void displayWeatherByZipCode(String zipCode, String countryCode) {
        try {
            // Build the API URL with zip code and country code
            String urlString = String.format("%s?zip=%s,%s&appid=%s&units=metric", 
                    BASE_URL, zipCode, countryCode, API_KEY);
            
            // Fetch the weather data
            String jsonResponse = fetchApiResponse(urlString);
            if (jsonResponse == null) {
                System.out.println("Error: Could not connect to API");
                return;
            }

            // Parse the JSON response
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            
            // Extract main weather data
            JSONObject main = (JSONObject) jsonObject.get("main");
            JSONObject wind = (JSONObject) jsonObject.get("wind");
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);

            // Extract and display the weather information
            double temperature = ((Number) main.get("temp")).doubleValue();
            double feelsLike = ((Number) main.get("feels_like")).doubleValue();
            long humidity = (long) main.get("humidity");
            double windSpeed = ((Number) wind.get("speed")).doubleValue();
            String description = (String) weather.get("description");
            String cityName = (String) jsonObject.get("name");

            System.out.println("\nWeather in " + cityName + ":");
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Feels like: " + feelsLike + "°C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind Speed: " + windSpeed + " m/s");
            System.out.println("Conditions: " + description);
            
        } catch (IOException | InterruptedException | URISyntaxException | ParseException e) {
            Logger.getLogger(modelWeather.class.getName()).log(Level.SEVERE, "Error fetching weather data", e);
        }
    }

    private static String fetchApiResponse(String urlString) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new IOException("API request failed with status code: " + response.statusCode());
        }
        
        return response.body();
    }
}