package by.itacademy.view;

import by.itacademy.service.ConsoleService;

public class Console {

  private final ConsoleService CS;

  public Console() {
    CS = new ConsoleService();
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
}
