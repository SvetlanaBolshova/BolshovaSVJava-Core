package BolshovaSVdz8;

import java.io.IOException;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException;

    void getSavedWeather() throws IOException;
}