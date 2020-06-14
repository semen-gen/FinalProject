package by.itacademy.service;

import by.itacademy.model.Ticket;
import java.util.List;

public class TicetService {

  private final JDBCService DB;

  public TicetService() {
    DB = JDBCService.getInstance();
  }

  public List<Ticket> getAvailableTickets(int id) {
    return DB.availableTickets(id);
  }
}