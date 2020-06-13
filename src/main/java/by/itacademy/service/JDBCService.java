package by.itacademy.service;


import by.itacademy.model.Movie;
import by.itacademy.model.User;
import by.itacademy.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JDBCService {

  private static JDBCService instance;

  private JDBCService() {

  }

  public static JDBCService getInstance() {
    if (instance == null) {
      instance = new JDBCService();
    }

    return instance;
  }

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
        rID = resultSet.getInt("ID");
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

  public List<Movie> getAllMovies() {
    List<Movie> list = new ArrayList<>();
    DBHelper db = DBHelper.getInstance();
    Connection connection = db.openConnection();

    String query = "SELECT * FROM p_movie WHERE film_date > now()";
    db.openPreparedStatement(connection, query);
    ResultSet resultSet = db.openPreparedResultSet();

    try {
      while (resultSet.next()) {
        list.add(new Movie(
            resultSet.getInt("ID"),
            resultSet.getString("name"),
            resultSet.getDate("film_date"),
            resultSet.getInt("price")
        ));
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      db.closePreparedResultSet(resultSet);
      db.closePreparedStatement();
      db.closeConnection(connection);
    }

    return list;
  }

  public boolean isMovie(int i) {
    boolean result = false;
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query = "SELECT ID FROM p_movie  WHERE ID=? ;";
    PreparedStatement preparedStatement = dbHelper.openPreparedStatement(connection, query);

    try {
      preparedStatement.setString(1, String.valueOf(i));
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
}
