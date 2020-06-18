package by.itacademy.model;

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
}
