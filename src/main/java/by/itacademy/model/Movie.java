package by.itacademy.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movie {

  private final DateTimeFormatter DF;
  private final int ID;
  private String name;
  private LocalDateTime filmDate;
  private int price;

  {
    DF = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");
  }

  public Movie(String name, LocalDateTime filmDate, int price) {
    this(0, name, filmDate, price);
  }

  public Movie(int id, String name, LocalDateTime filmDate, int price) {
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

  public LocalDateTime getDate() {
    return filmDate;
  }

  public String getFormattedDate() {
    return filmDate.format(DF);
  }

  public DateTimeFormatter getFormatter() {
    return DF;
  }

  public int getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFilmDate(LocalDateTime filmDate) {
    this.filmDate = filmDate;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return ID + ". " + name + " показ: " + filmDate.format(DF) + " цена: " + price;
  }

}
