package by.itacademy.view;

import java.util.Scanner;

public class Console {

  private final Scanner SCANNER;

  public Console() {
    SCANNER = new Scanner(System.in);
  }

  public void start() {
    printAuthMenu();
  }

  private void printAuthMenu() {
    System.out.println("Авторизуйтесь или зарегистрируйтесь");
    System.out.println("1. Авторизация");
    System.out.println("2. Регистрация");
  }
}
