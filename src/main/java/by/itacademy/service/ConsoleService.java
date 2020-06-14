package by.itacademy.service;

import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;
import by.itacademy.view.Console;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleService {

  private final Scanner SCANNER;
  private final Console CONSOLE;
  private final UserService US;
  private final MovieService MS;
  private final TicetService TS;

  public ConsoleService(Console console) {
    CONSOLE = console;
    SCANNER = new Scanner(System.in);
    US = new UserService();
    MS = new MovieService();
    TS = new TicetService();
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
              registration();
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
              printMovies(MS.getMovies());
              getTickets();
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

  private void getTickets() {
    int id;
    while (SCANNER.hasNext()) {
      if (SCANNER.hasNextInt()) {
        id = SCANNER.nextInt();
        if (MS.checkMovie(id)) {
          printAvailableTickets(TS.getAvailableTickets(id), id);
          CONSOLE.printCinemaMenu();
        } else {
          System.out.println("Вы ввели неправильный id фильма");
        }
      } else {
        System.out.println("Вы ввели неверные данные " + SCANNER.next());
      }
    }
  }

  private void printAvailableTickets(List<Ticket> availableTickets, int movie_id) {
    Map<Integer, Movie> map = MS.getMovies();
    System.out.println("Билеты на фильм " + map.get(movie_id).getName());
    for (Ticket item : availableTickets) {
      System.out.println(item);
    }
  }


  private void printMovies(Map<Integer, Movie> movies) {
    System.out.println("Выберите фильм");
    for (Map.Entry<Integer, Movie> item : movies.entrySet()) {
      System.out.println(item.getValue());
    }
  }

  private void registration() {
    String login, pass;

    System.out.println("Введите логин");
    login = SCANNER.next();
    checkLogin(US.isUserLogin(login));

    System.out.println("Введите пароль");
    pass = SCANNER.next();

    User user = US.userRegister(login, pass);

    checkUser(user, "Не удалось зарегистрироваться!");
  }

  private void checkLogin(boolean isUser) {
    if (isUser) {
      System.out.println("Этот логин уже занят!");
      registration();
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
    checkUser(user, "Неверный логин или парорль");
  }

  private void checkUser(User user, String errorMessage) {
    if (user == null) {
      System.out.println(errorMessage);
      System.out.println();
      CONSOLE.start();
    } else {
      CONSOLE.printCinemaMenu(user.getLogin());
    }
  }

  private void exit() {
    SCANNER.close();
    System.exit(0);
  }
}
