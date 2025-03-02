import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BackendforWeather {
    public static String main(String city){
        try {
            String API_KEY = "019c53bf179d1c470a23891718666205"; // Replace with your API key
            String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

            
            //Create URL
            String urlString = BASE_URL + city + "&units=metric&appid=" + API_KEY;
            URL url = new URL(urlString);

            //HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            // Read the response from the API
            StringBuilder result = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();


            //JSON response
            JSONParser response = new JSONParser();
            JSONObject json = (JSONObject) response.parse(result.toString());
            
            // Getting temperature
            JSONObject main = (JSONObject) json.get("main");
            JSONObject wind = (JSONObject) json.get("wind");
            
            JSONArray weather = (JSONArray) json.get("weather");
            JSONObject weatherObject = (JSONObject) weather.get(0);
            String weatherDescription = (String) weatherObject.get("description");

            double temp = (double) main.get("temp");
            double feels_like = (double) main.get("feels_like");
            double min_temp = (double) main.get("temp_min");
            double max_temp = (double) main.get("temp_max");
            double wind_speed = (double) wind.get("speed");

            String finalToPString = "Current temperature in " + city + " is: " + temp + "°C. Feels like "  + feels_like + "°C.\nWind speeds are at " + wind_speed + ".\n" + "Max temperature today: " + max_temp + "°C\nMin temperature today: " + min_temp + "°C\nWeather Description: " + weatherDescription;
            return finalToPString;

        } catch (Exception e){
            e.printStackTrace();
        }
        return city;
    }
}

