package by.itacademy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String DATABASE_URL = "jdbc:mysql://localhost/project?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  static final String USER = "root";
  static final String PASSWORD = "";

  private static DBHelper instance;

  Connection connection;
  PreparedStatement preparedStatement;
  Statement statement;
  ResultSet resultSet;

  public static DBHelper getInstance() {
    if (instance == null) {
      instance = new DBHelper();
    }

    return instance;
  }

  public Connection openConnection() {
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public PreparedStatement openPreparedStatement(Connection connection, String query) {
    try {
      preparedStatement = connection.prepareStatement(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return preparedStatement;
  }

  public void closePreparedStatement() {
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public ResultSet openPreparedResultSet() {
    try {
      resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public void closePreparedResultSet(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void openStatement(Connection connection) {
    try {
      statement = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public void closeStatement() {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public int insert(String query) {
    int id = 0;

    try {
      statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      ResultSet rs = statement.getGeneratedKeys();
      rs.next();
      id = rs.getInt(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return id;
  }

  public void update(String query) {
    try {
      statement.executeUpdate(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
