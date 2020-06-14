package by.itacademy.service;

import by.itacademy.model.Movie;
import java.util.Map;

public class MovieService {

  private final JDBCService DB;
  private Map<Integer, Movie> movies;

  public MovieService() {
    DB = JDBCService.getInstance();
  }

  public Map<Integer, Movie> getMovies() {
    if (movies == null) {
      movies = DB.getAllMovies();
    }
    return movies;
  }

  public boolean checkMovie(int i) {
    return DB.isMovie(i);
  }
}
