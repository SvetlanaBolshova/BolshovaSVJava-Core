package BolshovaSVdz8;


import BolshovaSVdz8.entity.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherModel implements WeatherModel {
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/295212

        private static final String PROTOKOL = "http";
        private static final String BASE_HOST = "dataservice.accuweather.com";
        private static final String FORECASTS = "forecasts";
        private static final String V1 = "v1";
        private static final String DAILY = "daily";
        private static final String ONE_DAY = "1day";
        private static final String API_KEY = "HSPtMjFUWO1otjVni60VVuDva3oW3Tmn";
        private static final String API_KEY_QUERY_PARAM = "apikey";
        private static final String LOCATIONS = "locations";
        private static final String CITIES = "cities";
        private static final String AUTOCOMPLETE = "autocomplete";

        private static final OkHttpClient okHttpClient = new OkHttpClient();
        private static final ObjectMapper objectMapper = new ObjectMapper();

        public void getWeather(String selectedCity, Period period) throws IOException {
            if (period == Period.NOW) {
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(CURRENT_CONDITIONS)
                        .addPathSegment(VERSION)
                        .addPathSegment(getCityKey(selectedCity))
                        .addQueryParameter("apikey", API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                String responseString = response.body().string();
                String weatherText = objectMapper.readTree(responseString).get(0).at("/WeatherText").asText();
                Integer degrees = objectMapper.readTree(responseString).get(0).at("/Temperature/Metric/Value").asInt();
                String date = objectMapper.readTree(responseString).get(0).at("/LocalObservationDateTime").asText().split("T")[0];
                Weather weather = new Weather(selectedCity, weatherText, degrees, date);
                System.out.println(weather);
                DataBaseRepository dataBaseRepository = new DataBaseRepository();
                dataBaseRepository.saveWeather(weather);
                //TODO: сделать красивый вывод в консоль
            }

            if (period == Period.FIVE_DAYS) {
                // TODO*: реализовать вывод прогноза погоды на 5 дней

                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVEDAY)
                        .addPathSegment(getCityKey(selectedCity))
                        .addQueryParameter("apikey", API_KEY)
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                String responseString = response.body().string();

                String subStr;
                Integer degrees;
                String date;

                subStr = objectMapper.readTree(responseString).path("Headline").path("Text").asText();
                String weatherText = subStr.length() > 29 ? subStr.substring(0, 29) : subStr;


                for (int i = 0; i < 5; i++) {
                    degrees = objectMapper.readTree(responseString).path("DailyForecasts").get(i).path("Temperature").path("Minimum").path("Value").asInt();
                    date = objectMapper.readTree(responseString).path("DailyForecasts").get(i).path("Date").asText().split("T")[0];
                    System.out.println(selectedCity + " | " + weatherText + " | " + degrees + " | " + date);
                    Weather weather = new Weather(selectedCity, weatherText, degrees, date);
                    DataBaseRepository dataBaseRepository = new DataBaseRepository();
                    dataBaseRepository.saveWeather(weather);
                }
                //TODO: сделать красивый вывод в консоль
            }
        }


        public void getSavedWeather() throws IOException {

            DataBaseRepository dataBaseRepository = new DataBaseRepository();
            dataBaseRepository.getSavedWeather();

            //TODO: сделать красивый вывод в консоль

        }

        public String getCityKey(String city) throws IOException {
            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(BASE_HOST)
                    .addPathSegments(LOCATIONS)
                    .addPathSegments(VERSION)
                    .addPathSegments(CITIES)
                    .addPathSegments(AUTOCOMPLETE)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("q", city)
                    .build();

            Request request = new Request.Builder()
                    .url(httpUrl)
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            String responseBody = response.body().string();

            String cityKey = objectMapper.readTree(responseBody).get(0).at("/Key").asText();
            return cityKey;
        }
    }