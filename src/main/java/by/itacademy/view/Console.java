package by.itacademy.view;

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

  public void printCinemaMenu(String login) {
    System.out.println("Здравствуйте, "+ login);
    System.out.println("Меню кинотевтра");
    System.out.println("1. Спиок фильмов");
    System.out.println("2. Купить билеты");
    System.out.println("3. Вернуть билет");
    System.out.println("4. Твои билеты");
    System.out.println("5. Выйти");
    CS.cinemaMenu();
  }
}
