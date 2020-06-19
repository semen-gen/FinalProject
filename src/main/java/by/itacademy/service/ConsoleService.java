package by.itacademy.service;

import by.itacademy.exeptions.WrongProductID;
import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.User;
import by.itacademy.model.UserType;
import by.itacademy.view.Console;
import by.itacademy.view.MovieConsole;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleService {

  private final Scanner SCANNER;
  private final Console CONSOLE;
  private final UserService US;
  private final MovieService MS;
  private final TicetService TS;
  private final MovieConsole MC;

  public ConsoleService(Console console) {
    CONSOLE = console;
    SCANNER = new Scanner(System.in);

    US = new UserService();
    MS = new MovieService(SCANNER, this);
    TS = new TicetService();

    MC = new MovieConsole(MS);
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
              CONSOLE.printCinemaMenu();
              break;
            case 2:
              System.out.println("Выберите фильм");
              getTickets();
              CONSOLE.printPurchaseDialog();
              break;
            case 3:
              TS.returnAllTickets(US.getCurrentUser());
              System.out.println("Все Ваши билеты возвращены");
              CONSOLE.printCinemaMenu();
              break;
            case 4:
              printUserTickets();
              CONSOLE.printCinemaMenu();
              break;
            case 5:
              signOut();
              break;
            case 6:
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

  public void managerMenu() {
    exit:
    {
      while (SCANNER.hasNext()) {
        if (SCANNER.hasNextInt()) {
          switch (SCANNER.nextInt()) {
            case 1:
              printMovies(MS.getMovies());
              CONSOLE.printManagerMenu();
              break;
            case 2:
              System.out.println("2. Редактировать фильм");
              MC.editMovieMenu();
              break;
            case 3:
              System.out.println("3. Список билетов");
              break;
            case 4:
              System.out.println("4. Список пользователей");
              break;
            case 5:
              System.out.println("5. Спиок билетов пользователя");
              break;
            case 6:
              System.out.println("6. Купить билеты для пользователя");
              break;
            case 7:
              System.out.println("7. Вернуть билеты для пользователя");
              break;
            case 8:
              signOut();
              break;
            case 9:
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

  private void signOut() {
    US.deleteCurrentUser();
    CONSOLE.start();
  }

  public void purchaseDialog() {
    SCANNER.skip("\\n");
    while (SCANNER.hasNext()) {
      String result = SCANNER.nextLine();
      checkIDForString(result);
      List<Integer> ids = checkIdForBad(result);
      if (ids.size() > 0) {
        TS.addUserToTickets(ids, US.getCurrentUser().getID());
        System.out.println("Билеты преобретены");
        CONSOLE.printCinemaMenu();
      }
    }
  }

  private void printUserTickets() {
    Map<Integer, Movie> map = MS.getMovies();
    List<Ticket> userTickets = TS.getUserTickets(US.getCurrentUser());
    if (userTickets.size() > 0) {
      for (Ticket item : userTickets) {
        System.out.println(item + " - " + map.get(item.getMOVIE_ID()).getName());
      }
    } else {
      System.out.println("У Вас нет билетов");
    }
  }

  private void checkIDForString(String result) {
    Pattern notDecimals = Pattern
        .compile("\\b[^\\d\\s]\\w+\\b|\\b\\w+[^\\d\\s]\\b|\\b\\d+[^\\d\\s]+\\d+\\b");
    Matcher matcher = notDecimals.matcher(result);
    while (matcher.find()) {
      try {
        throw new WrongProductID(matcher.group());
      } catch (WrongProductID wrongProductID) {
        wrongProductID.printMessage();
      }
    }
  }

  private List<Integer> checkIdForBad(String result) {
    Pattern decimals = Pattern.compile("(\\b\\d+\\b)+");
    Matcher matcher = decimals.matcher(result);
    List<Integer> ids = new ArrayList<>();

    while (matcher.find()) {
      ids.add(Integer.valueOf(matcher.group()));
    }
    List<Integer> existIds = TS.checkTickets(ids);

    if (ids.size() != existIds.size()) {
      for (Integer item : ids) {
        if (!existIds.contains(item)) {
          try {
            throw new WrongProductID(item);
          } catch (WrongProductID wrongProductID) {
            wrongProductID.printMessage();
          }
        }
      }
    }
    return existIds;
  }

  private void getTickets() {
    int id;
    while (SCANNER.hasNext()) {
      if (SCANNER.hasNextInt()) {
        id = SCANNER.nextInt();
        if (MS.checkMovie(id)) {
          printAvailableTickets(TS.getAvailableTickets(id), id);
          break;
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
    if (!movies.isEmpty()) {
      for (Map.Entry<Integer, Movie> item : movies.entrySet()) {
        System.out.println(item.getValue());
      }
    } else {
      System.out.println("Фильмы отсутствуют! \nПриходите завтра!!!");
    }
  }

  private void registration() {
    String login, pass;

    System.out.println("Введите логин");
    login = SCANNER.next();
    checkLogin(US.isUserLogin(login));

    System.out.println("Введите пароль");
    pass = SCANNER.next();

    User user = US.userRegister(login, pass, UserType.USER);

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
      CONSOLE.printMenu(user);
    }
  }

  private void exit() {
    SCANNER.close();
    System.exit(0);
  }

  public void userMenu() {
    switch (US.getCurrentUser().getType()) {
      case ADMIN:
        CONSOLE.printAdminMenu();
        break;
      case MANAGER:
        CONSOLE.printManagerMenu();
        break;
      case USER:
        CONSOLE.printCinemaMenu();
        break;
    }
  }
}
