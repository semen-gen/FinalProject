package by.itacademy.model;

public class User {

  private final int ID;
  private String login;
  private String pass;
  private UserType type;


  public User(String login, String pass) {
    this(0, login, pass, UserType.USER);
  }

  public User(int id, String login, String pass, UserType type) {
    ID = id;
    this.login = login;
    this.pass = pass;
    this.type = type;
    System.out.println(this);
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
