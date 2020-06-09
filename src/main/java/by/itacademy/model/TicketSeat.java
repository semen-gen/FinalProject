package by.itacademy.model;

public class TicketSeat {

  private final int ID;
  private int row;
  private int place;

  public TicketSeat(int id, int row, int place) {
    ID = id;
    this.row = row;
    this.place = place;
  }

  public int getID() {
    return ID;
  }

  public int getRow() {
    return row;
  }

  public int getPlace() {
    return place;
  }
}
