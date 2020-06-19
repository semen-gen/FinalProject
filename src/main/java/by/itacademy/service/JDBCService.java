package by.itacademy.service;

import by.itacademy.model.Movie;
import by.itacademy.model.Ticket;
import by.itacademy.model.TicketSeat;
import by.itacademy.model.User;
import by.itacademy.model.UserType;
import by.itacademy.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        UserType rType;

        rID = resultSet.getInt("ID");
        rLogin = resultSet.getString("login");
        rPass = resultSet.getString("pass");
        rType = UserType.getById(resultSet.getInt("type_id"));
        user = new User(rID, rLogin, rPass, rType);
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

  public User addUser(String login, String pass, UserType type) {
    User user = null;
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query =
        "INSERT INTO p_user VALUES ("
            + "DEFAULT, "
            + "\"" + login + "\", "
            + "\"" + pass + "\", "
            + type.getId()
            + " );";
    dbHelper.openStatement(connection);
    int id = dbHelper.insert(query);

    dbHelper.closeStatement();
    dbHelper.closeConnection(connection);

    if (id > 0) {
      user = new User(id, login, pass, type);
    }

    return user;
  }

  public Map<Integer, Movie> getAllMovies() {
    Map<Integer, Movie> map = new HashMap<>();
    DBHelper db = DBHelper.getInstance();
    Connection connection = db.openConnection();

    String query = "SELECT * FROM p_movie";
    db.openPreparedStatement(connection, query);
    ResultSet resultSet = db.openPreparedResultSet();

    try {
      while (resultSet.next()) {
        map.put(
            resultSet.getInt("ID"),
            new Movie(
                resultSet.getInt("ID"),
                resultSet.getString("name"),
                resultSet.getTimestamp("film_date").toLocalDateTime(),
                resultSet.getInt("price")
            )
        );
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      db.closePreparedResultSet(resultSet);
      db.closePreparedStatement();
      db.closeConnection(connection);
    }

    return map;
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

  public List<Ticket> availableTickets(int id) {
    List<Ticket> result = new ArrayList<>();
    DBHelper db = DBHelper.getInstance();
    Connection connection = db.openConnection();

    String query = "SELECT t.ID, t.movie_id, t.ticket_seat_id, ts.row, ts.place "
        + "FROM p_ticket AS t "
        + "LEFT JOIN p_ticket_seat AS ts "
        + "ON t.ticket_seat_id = ts.ID "
        + "WHERE movie_id = " + id + " AND user_id is null;";
    db.openPreparedStatement(connection, query);
    ResultSet resultSet = db.openPreparedResultSet();

    try {
      while (resultSet.next()) {
        result.add(
            new Ticket(
                resultSet.getInt("ID"),
                resultSet.getInt("movie_id"),
                new TicketSeat(
                    resultSet.getInt("ticket_seat_id"),
                    resultSet.getInt("row"),
                    resultSet.getInt("place")
                )
            )
        );
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {
      db.closePreparedResultSet(resultSet);
      db.closePreparedStatement();
      db.closeConnection(connection);
    }

    return result;
  }

  public List<Integer> getTicketsById(List<Integer> ids) {
    List<Integer> result = new ArrayList<>();
    DBHelper db = DBHelper.getInstance();
    Connection connection = db.openConnection();

    String query = "SELECT ID FROM p_ticket WHERE ID IN (" + ids.stream().map(Object::toString)
        .collect(Collectors.joining(",")) + ") AND user_id IS NULL";
    db.openPreparedStatement(connection, query);

    ResultSet resultSet = db.openPreparedResultSet();
    try {

      while (resultSet.next()) {
        result.add(resultSet.getInt("ID"));
      }

    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {

      db.closePreparedResultSet(resultSet);
      db.closePreparedStatement();
      db.closeConnection(connection);
    }

    return result;
  }

  public void updateTickets(List<Integer> ids, int userId) {
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query = "UPDATE p_ticket SET user_id = " + userId + " WHERE ID IN (" + ids.stream()
        .map(Object::toString)
        .collect(Collectors.joining(",")) + ");";
    dbHelper.openStatement(connection);
    dbHelper.update(query);

    dbHelper.closeStatement();
    dbHelper.closeConnection(connection);
  }

  public List<Ticket> userTickets(User currentUser) {
    List<Ticket> result = new ArrayList<>();
    DBHelper db = DBHelper.getInstance();
    Connection connection = db.openConnection();

    String query = "SELECT t.ID, t.movie_id, t.ticket_seat_id, ts.row, ts.place "
        + "FROM p_ticket AS t "
        + "LEFT JOIN p_ticket_seat AS ts "
        + "ON t.ticket_seat_id = ts.ID "
        + "WHERE user_id = " + currentUser.getID() + ";";
    db.openPreparedStatement(connection, query);

    ResultSet resultSet = db.openPreparedResultSet();
    try {

      while (resultSet.next()) {
        result.add(
            new Ticket(
                resultSet.getInt("ID"),
                resultSet.getInt("movie_id"),
                new TicketSeat(
                    resultSet.getInt("ticket_seat_id"),
                    resultSet.getInt("row"),
                    resultSet.getInt("place")
                )
            )
        );
      }

    } catch (SQLException exception) {
      exception.printStackTrace();
    } finally {

      db.closePreparedResultSet(resultSet);
      db.closePreparedStatement();
      db.closeConnection(connection);
    }

    return result;
  }

  public void unbindTickets(User currentUser) {
    DBHelper dbHelper = DBHelper.getInstance();
    Connection connection = dbHelper.openConnection();

    String query =
        "UPDATE p_ticket SET user_id = null WHERE user_id = " + currentUser.getID() + " ;";
    dbHelper.openStatement(connection);
    dbHelper.update(query);

    dbHelper.closeStatement();
    dbHelper.closeConnection(connection);
  }
}
