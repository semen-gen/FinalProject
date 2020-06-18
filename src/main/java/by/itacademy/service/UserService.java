package by.itacademy.service;

import by.itacademy.model.User;
import by.itacademy.model.UserType;

public class UserService {

  private final JDBCService DB;
  private User currentUser;

  public UserService() {
    DB = JDBCService.getInstance();
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

  public User userRegister(String login, String pass, UserType type) {
    return currentUser = DB.addUser(login, pass, type);
  }

  public void deleteCurrentUser() {
    currentUser = null;
  }
}
