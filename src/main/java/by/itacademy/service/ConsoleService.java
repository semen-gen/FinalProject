package by.itacademy.service;

import by.itacademy.model.User;
import by.itacademy.view.Console;
import java.util.Scanner;

public class ConsoleService {

  private final Scanner SCANNER;
  private final Console CONSOLE;
  private final UserService US;

  public ConsoleService(Console console) {

    CONSOLE = console;
    SCANNER = new Scanner(System.in);
    US = new UserService();
  }

  public void welcomeMenu() {
    exit:
    {
      while (SCANNER.hasNext()) {
        if (SCANNER.hasNextInt()) {
          switch (SCANNER.nextInt()) {
            case 1:
              auth();
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

  private void auth() {

    String login, pass;
    System.out.println("Введите логин");
    login = SCANNER.next();
    System.out.println("Введите пароль");
    pass = SCANNER.next();

    checkUser(US.userAuth(login, pass));
  }

  private void checkUser(User user) {
    if (user == null) {
      System.out.println("Неверный логин или парорль");
      System.out.println();
      CONSOLE.start();
    }
  }
}
