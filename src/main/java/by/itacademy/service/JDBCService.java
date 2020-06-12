package by.itacademy.service;


import by.itacademy.model.User;
import by.itacademy.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCService {

  public User getUserByAuth(String login, String pass) {
    User user = null;
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query = "SELECT * FROM p_user  WHERE login=? AND pass=?;";
    PreparedStatement preparedStatement = dbHelper.openPreparedStatement(connection, query);

    try {
      preparedStatement.setString(1, login);
      preparedStatement.setString(2, pass);

      ResultSet resultSet = dbHelper.openPreparedResultSet();

      if (resultSet.next()) {
        String rLogin, rPass;
        int rID;
        rID = resultSet.getInt("id");
        rLogin = resultSet.getString("login");
        rPass = resultSet.getString("pass");
        user = new User(rID, rLogin, rPass);
      }

      dbHelper.closePreparedResultSet(resultSet);
      dbHelper.closePreparedStatement();
      dbHelper.closeConnection(connection);

    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return user;
  }

  public boolean isSetLogin(String login) {
    boolean result = true;
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query = "SELECT DISTINCT login FROM p_user  WHERE login=? ;";
    PreparedStatement preparedStatement = dbHelper.openPreparedStatement(connection, query);

    try {
      preparedStatement.setString(1, login);
      ResultSet resultSet = dbHelper.openPreparedResultSet();

      result = resultSet.next();

      dbHelper.closePreparedResultSet(resultSet);
      dbHelper.closePreparedStatement();
      dbHelper.closeConnection(connection);

    } catch (SQLException exception) {
      exception.printStackTrace();
    }

    return result;
  }

  public User addUser(String login, String pass) {
    User user = null;
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query = "INSERT INTO p_user VALUES (DEFAULT, \"" + login + "\",\"" + pass + "\" );";
    dbHelper.openStatement(connection);
    int id = dbHelper.insert(query);

    dbHelper.closeStatement();
    dbHelper.closeConnection(connection);

    if (id > 0) {
      user = new User(id, login, pass);
    }

    return user;
  }

//  private static Ticket getTickets() {
//    return null;
//  }

//  public List<User> getAllUsers() throws SQLException {
//    DBHelper dbHelper = DBHelper.getInstance();
//
//    Connection connection = dbHelper.openConnection();
//    PreparedStatement preparedStatement = dbHelper
//        .openStatement(connection, "SELECT * FROM p_user");
//    ResultSet resultSet = dbHelper.openResultSet(preparedStatement);
//
//    int id = 0;
//    String login = "";
//    String password = "";
//    UserType userType = null;
//
//    List<User> users = new ArrayList<>();
//
//    while (resultSet.next()) {
//      id = resultSet.getInt("id");
//      login = resultSet.getString("login");
//      password = resultSet.getString("pass");
////      userType = UserType.valueOf(resultSet.getString("role").toUpperCase());
//      users.add(new User(id, login, password));
//    }
//
//    dbHelper.closeResultSet(resultSet);
//    dbHelper.closeStatement(preparedStatement);
//    dbHelper.closeConnection(connection);
//
//    return users;
//  }

//  public static User addUser(User user) throws SQLException {
//    DBHelper dbHelper = DBHelper.getInstance();
//
//    Connection connection = dbHelper.openConnection();
//    PreparedStatement preparedStatement = dbHelper
//        .openStatement(connection, "INSERT INTO users VALUES (?, ?, ?, ?);");
//
//    preparedStatement.setInt(1, 6);
//    preparedStatement.setString(2, user.getLogin());
//    preparedStatement.setString(3, user.getPass());
////    preparedStatement.setString(4, user.getUserType().toString());
//    preparedStatement.execute();
//
//    dbHelper.closeStatement(preparedStatement);
//    dbHelper.closeConnection(connection);
//    return user;
//  }
}
