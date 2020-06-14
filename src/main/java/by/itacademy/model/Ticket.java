package by.itacademy.model;

public class Ticket {

  private final int ID;
  private final int MOVIE_ID;
  private final TicketSeat TICKET_SEAT;
  private Integer user_id;


  public Ticket(int id, int movie_id, TicketSeat ticket_seat) {
    this(id, movie_id, ticket_seat, null);
  }

  public Ticket(int id, int movie_id, TicketSeat ticket_seat, Integer user_id) {
    ID = id;
    MOVIE_ID = movie_id;
    TICKET_SEAT = ticket_seat;
    this.user_id = user_id;
  }

  public int getMOVIE_ID() {
    return MOVIE_ID;
  }

  public TicketSeat getTICKET_SEAT() {
    return TICKET_SEAT;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return ID + ". " + "ряд " + TICKET_SEAT.getRow() + " место " + TICKET_SEAT.getPlace();
  }
}
