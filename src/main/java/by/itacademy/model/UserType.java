package by.itacademy.model;

import java.util.Arrays;
import java.util.stream.Collector;

public enum UserType {

  ADMIN(1),
  MANAGER(2),
  USER(3);

  int id;

  UserType(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static UserType getById(int id) {
    return Arrays.stream(values()).filter(e -> e.getId() == id).findFirst().get();
  }
}
