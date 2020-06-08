package by.itacademy.model;

import java.util.Calendar;

public class Movie {

  private final int ID;
  private String name;
  private Calendar filmDate;
  private int price;

  public Movie(String name, Calendar filmDate, int price) {
    this(0, name, filmDate, price);
  }

  public Movie(int id, String name, Calendar filmDate, int price) {
    ID = id;
    this.name = name;
    this.filmDate = filmDate;
    this.price = price;
  }

  public int getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public Calendar getFilmDate() {
    return filmDate;
  }

  public int getPrice() {
    return price;
  }
}
