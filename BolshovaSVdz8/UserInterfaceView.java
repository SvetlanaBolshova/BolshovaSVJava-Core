package BolshovaSVdz8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите опцию: 1 - получить текущую погоду, 2 - получить " +
                    "прогноз погоды на 5 дней" + ", 3 - получить все данные из БД" + ", 4 - выйти из приложения");
            //TODO: добавить опцию получения погоды из базы данных
            String command = scanner.nextLine();

            // exit from app if command == 4
            if (command.equals("4")) {
                System.exit(0);
            }

            System.out.println("Введите название города: ");
            String city = scanner.nextLine();

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

