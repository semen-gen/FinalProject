package by.itacademy.service;

import by.itacademy.model.Movie;
import by.itacademy.view.MovieConsole;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class MovieService {

  private final JDBCService DB;
  private final Scanner SCANNER;
  private Map<Integer, Movie> movies;

  public MovieService(Scanner scanner) {
    DB = JDBCService.getInstance();
    SCANNER = scanner;
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

  public Movie enterId() {
    int id;

    while (SCANNER.hasNext()) {
      if (SCANNER.hasNextInt()) {
        id = SCANNER.nextInt();
        if (checkMovie(id)) {
          return getMovieById(id);
        } else {
          System.out.println("Такого фильма не существует");
        }
      } else {
        System.out.println("Вы ввели неверные данные " + SCANNER.next());
      }
    }

    return null;
  }

  private Movie getMovieById(int id) {
    return getMovies().get(id);
  }

  public void editMovie(Movie movie, MovieConsole mc) {
    exit:
    {
      while (SCANNER.hasNext()) {
        if (SCANNER.hasNextInt()) {
          switch (SCANNER.nextInt()) {
            case 1:
              changeName(movie);
              mc.editMovieMenu(movie);
              break;
            case 2:
              changeDate(movie);
              mc.editMovieMenu(movie);
              break;
            case 3:
              changePrice(movie);
              mc.editMovieMenu(movie);
              break;
            case 4:
              System.out.println("4. Выйти и сохранить изменения");
              break exit;
            case 5:
              System.out.println("5. Выйти без сохранения");
              break exit;
            default:
              System.out.println("Такого пункта не существует");
          }
        } else {
          System.out.println("Вы ввели неверные данные " + SCANNER.next());
        }
      }
    }
  }

  private void changePrice(Movie movie) {
    boolean next = true;
    SCANNER.skip("\\n");
    do {
      System.out.println("Текущая цена билета: " + movie.getPrice());
      System.out.println("Введите новую цену");

      try {
        movie.setPrice(Integer.parseInt(SCANNER.nextLine()));
        next = false;
      } catch (NumberFormatException e) {
        System.out.println("Вы ввели неверное значение.");
        System.out.println("Нужно указать только цифру без пробелов");
      }
    }
    while (next);
  }

  private void changeDate(Movie movie) {
    System.out.println("Текущее дата и время: " + movie.getFormattedDate());
    System.out.println("Введите новые дату и время для фильма");
    SCANNER.skip("\\n");

    try {
      LocalDateTime f = LocalDateTime.parse(SCANNER.nextLine(), movie.getFormatter());
      movie.setFilmDate(f);
    } catch (DateTimeParseException e) {
      System.out.println("Вы ввели дату в неправильном формате");
      changeDate(movie);
    }
  }

  private void changeName(Movie movie) {
    System.out.println("Текущее название: " + movie.getName());
    System.out.println("Введите новое название для фильма");
    SCANNER.skip("\\n");
    movie.setName(SCANNER.nextLine());
  }
}
