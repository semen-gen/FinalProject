package by.itacademy.service;

import by.itacademy.model.Movie;
import java.util.List;

public class MovieService {

  private final JDBCService DB;
  private List<Movie> movies;

  public MovieService() {
    DB = JDBCService.getInstance();
  }

  public List<Movie> getMovies() {
    if (movies == null) {
      movies = DB.getAllMovies();
    }
    return movies;
  }

  public boolean checkMovie(int i) {
    return DB.isMovie(i);
  }
}
