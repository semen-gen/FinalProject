package by.itacademy.model;

public class User {

  private final int ID;
  private String login;
  private String pass;


  public User(String login, String pass) {
    this(0, login, pass);
  }

  public User(int id, String login, String pass) {
    ID = id;
    this.login = login;
    this.pass = pass;
  }

  public String getLogin() {
    return login;
  }

  public int getID() {
    return ID;
  }

  public String getPass() {
    return pass;
  }


  @Override
  public String toString() {
    return "Пользователь: " + ID + " " + login;
  }
}
