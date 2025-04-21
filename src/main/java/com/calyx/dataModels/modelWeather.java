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

private String Key = "e9a43c91e1aaddc1ad19e6c067696789";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static JSONObject getLocationData(String city) throws IOException, ParseException, URISyntaxException, InterruptedException {
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        
        String jsonResponse = fetchApiResponse(urlString);
        if (jsonResponse == null) {
            throw new IOException("Failed to fetch location data");
        }

        JSONParser parser = new JSONParser();
        JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);
        JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
        
        if (locationData == null || locationData.isEmpty()) {
            throw new IOException("No location data found");
        }
        
        return (JSONObject) locationData.get(0);
    }

    public static void displayWeatherData(double latitude, double longitude) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                    "&longitude=" + longitude + "&current=temperature_2m,relative_humidity_2m,wind_speed_10m";
            
            String jsonResponse = fetchApiResponse(url);
            if (jsonResponse == null) {
                System.out.println("Error: Could not connect to API");
                return;
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeatherJson = (JSONObject) jsonObject.get("current");

            String time = (String) currentWeatherJson.get("time");
            double temperature = ((Number) currentWeatherJson.get("temperature_2m")).doubleValue();
            long relativeHumidity = (long) currentWeatherJson.get("relative_humidity_2m");
            double windSpeed = ((Number) currentWeatherJson.get("wind_speed_10m")).doubleValue();

            System.out.println("Current Time: " + time);
            System.out.println("Current Temperature (C): " + temperature);
            System.out.println("Relative Humidity: " + relativeHumidity + "%");
            System.out.println("Wind Speed: " + windSpeed + " km/h");
            
        } catch (Exception e) {
            Logger.getLogger(ModelWeather.class.getName()).log(Level.SEVERE, "Error fetching weather data", e);
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