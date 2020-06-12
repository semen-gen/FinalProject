package by.itacademy.service;

import by.itacademy.model.User;

public class UserService {

  private final JDBCService DB;
  private User currentUser;

  public UserService() {
    DB = new JDBCService();
  }

  public User userAuth(String login, String pass) {
    return currentUser = DB.getUserByAuth(login, pass);
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public boolean isUserLogin(String login) {
    return DB.isSetLogin(login);
  }

  public User userRegister(String login, String pass) {
    return currentUser = DB.addUser(login, pass);
  }
}
