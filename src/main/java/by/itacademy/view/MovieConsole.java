package by.itacademy.view;

import by.itacademy.model.Movie;
import by.itacademy.service.MovieService;

public class MovieConsole {

  private final MovieService MS;

  public MovieConsole(MovieService ms) {
    MS = ms;
  }

  public void editMovieMenu(Movie movie) {
    System.out.println("Фильм " + movie);
    System.out.println("1. Изменить название");
    System.out.println("2. Изменить дату и время");
    System.out.println("3. Изменить цену билета");
    System.out.println("4. Выйти и сохранить изменения");
    System.out.println("5. Выйти без сохранения");
    MS.editMovie(movie, this);
  }

  public void editMovieMenu() {
    System.out.println("Укажите id для редактирования");
    Movie movie = MS.enterId();
    editMovieMenu(movie);
  }
}
