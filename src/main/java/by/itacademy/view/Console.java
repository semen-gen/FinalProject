package by.itacademy.view;

import by.itacademy.model.User;
import by.itacademy.service.ConsoleService;

public class Console {

  private final ConsoleService CS;

  public Console() {
    CS = new ConsoleService(this);
  }

  public void start() {
    printWelcomeMenu();
  }

  private void printWelcomeMenu() {
    System.out.println("Авторизуйтесь или зарегистрируйтесь");
    System.out.println("1. Авторизация");
    System.out.println("2. Регистрация");
    System.out.println("3. Выйти");
    CS.welcomeMenu();
  }

  public void printAdminMenu() {
    System.out.println("===============");
    System.out.println("Меню администратора");
    System.out.println("1. Спиок фильмов");
    System.out.println("2. Купить билеты");
    System.out.println("3. Вернуть все билеты");
    System.out.println("4. Твои билеты");
    System.out.println("5. Разлогиниться");
    System.out.println("6. Выйти");
    CS.cinemaMenu();
  }

  public void printManagerMenu() {
    System.out.println("===============");
    System.out.println("Меню менеджера");
    System.out.println("1. Спиок фильмов");
    System.out.println("2. Купить билеты");
    System.out.println("3. Вернуть все билеты");
    System.out.println("4. Твои билеты");
    System.out.println("5. Разлогиниться");
    System.out.println("6. Выйти");
    CS.cinemaMenu();
  }

  public void printCinemaMenu() {
    System.out.println("===============");
    System.out.println("Меню кинотеатра");
    System.out.println("1. Спиок фильмов");
    System.out.println("2. Купить билеты");
    System.out.println("3. Вернуть все билеты");
    System.out.println("4. Твои билеты");
    System.out.println("5. Разлогиниться");
    System.out.println("6. Выйти");
    CS.cinemaMenu();
  }

  public void printMenu(User user) {
    System.out.println("===============");
    System.out.println("Здравствуйте, " + user.getLogin());
    switch (user.getType()) {
      case ADMIN:
        printAdminMenu();
        break;
      case MANAGER:
        printManagerMenu();
        break;
      case USER:
        printCinemaMenu();
        break;
    }
  }

  public void printPurchaseDialog() {
    System.out.println("Укажите id выбранных билетов через пробел");
    CS.purchaseDialog();
  }
}
