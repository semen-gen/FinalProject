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
    exit();
  }

  public void cinemaMenu() {
    exit:
    {
      while (SCANNER.hasNext()) {
        if (SCANNER.hasNextInt()) {
          switch (SCANNER.nextInt()) {
            case 1:
              System.out.println("1. Спиок фильмов");
              break;
            case 2:
              System.out.println("2. Купить билеты");
              break;
            case 3:
              System.out.println("3. Вернуть билет");
              break;
            case 4:
              System.out.println("4. Твои билеты");
              break;
            case 5:
              break exit;
            default:
              System.out.println("Такого пункта не существует");
          }
        } else {
          System.out.println("Вы ввели неверные данные " + SCANNER.next());
        }
      }
    }
    exit();
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
    } else {
      CONSOLE.printCinemaMenu();
    }
  }

  private void exit() {
    SCANNER.close();
//    CS.saveChange(currentUser);
    System.exit(0);
  }
}
