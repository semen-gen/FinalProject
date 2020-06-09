package by.itacademy.model;

public class Ticket {

  private final int MOVIE_ID;
  private final int TICKET_SEAT_ID;
  private int user_id;

  public Ticket(int movie_id, int ticket_seat_id, int user_id) {
    MOVIE_ID = movie_id;
    TICKET_SEAT_ID = ticket_seat_id;
    this.user_id = user_id;
  }

  public int getMOVIE_ID() {
    return MOVIE_ID;
  }

  public int getTICKET_SEAT_ID() {
    return TICKET_SEAT_ID;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
}
