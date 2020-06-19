package by.itacademy.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Movie {

  private final int ID;
  private String name;
  private Date filmDate;
  private int price;

  public Movie(String name, Date filmDate, int price) {
    this(0, name, filmDate, price);
  }

  public Movie(int id, String name, Date filmDate, int price) {
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

  public Date getFilmDate() {
    return filmDate;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return ID + ". " + name + " показ: " + filmDate + " цена: " + price;
  }
}
