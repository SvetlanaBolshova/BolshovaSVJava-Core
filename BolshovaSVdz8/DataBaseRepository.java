package BolshovaSVdz8;

     import BolshovaSVdz8.entity.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class DataBaseRepository {
        private static final String DB_URL = "jdbc:sqlite:geekbrains.db";
        String insertWeatherRequest = "insert into weather (city_name, weather_text, degrees) values (?, ?, ?)";

        static {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //cityName, weatherText, degrees
        public void saveWeather(Weather weather) {
            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                PreparedStatement preparedStatement = connection.prepareStatement(insertWeatherRequest);
                preparedStatement.setString(1, weather.getCityName());
                preparedStatement.setString(2, weather.getWeatherText());
                preparedStatement.setInt(3, weather.getDegrees());
                preparedStatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        public void getSavedWeather() {
            //TODO: написать метод, который достанет из базы все записи о погоде и выведет пользователю

            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectWeatherRequest);
                System.out.println("-------------------------------------------------------------------------------------");
                System.out.println("City                          Weather                       Degrees                       Date");
                System.out.println("-------------------------------------------------------------------------------------");
                while (resultSet.next()) {
                    System.out.printf("%-30s", resultSet.getString("city_name"));
                    System.out.printf("%-30s", resultSet.getString("weather_text"));
                    System.out.printf("%-30s", resultSet.getInt("degrees"));
                    System.out.printf("%-30s", resultSet.getString("date"));
                    System.out.println();
                }
                System.out.println();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }