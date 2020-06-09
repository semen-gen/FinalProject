package by.itacademy.service;

import java.util.Scanner;

public class ConsoleService {

  private final Scanner SCANNER;

  public ConsoleService() {

    SCANNER = new Scanner(System.in);
  }

  public void welcomeMenu() {
    exit:
    {
      while (SCANNER.hasNext()) {
        if (SCANNER.hasNextInt()) {
          switch (SCANNER.nextInt()) {
            case 1:
              System.out.println("Первый пункт");
              break;
            case 2:
              System.out.println("Второй пункт");
              break;
            case 3:
              break exit;
            default:
              System.out.println("Такого пункта не существует");
          }
        } else {
          System.out.println("Вы ввели неверные данные " + SCANNER.next());
        }
      }
    }
  }
}
