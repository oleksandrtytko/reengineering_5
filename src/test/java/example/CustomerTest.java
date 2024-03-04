package example;

import org.junit.Test;

import java.util.List;

import static example.Movie.MovieType.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

  public static final Movie REGULAR_MOVIE = new Movie("regular", REGULAR);

  public static final Movie NEW_RELEASE_MOVIE = new Movie("new release", NEW_RELEASE);

  public static final Movie MOVIE_FOR_CHILDREN = new Movie("children", CHILDRENS);

  public static final String CUSTOMER_NAME = "customerName";

  @Test
  public void whenRegularFilmRentedFor20Days_ShouldReturn29DollarsAnd1Point() {
    String expected = "Rental Record for customerName\n" +
        "\tregular\t29.0\n" +
        "Amount owed is 29.0\n" +
        "You earned 1 frequent renter points";

    assertEquals(expected, Customer.getStatement(CUSTOMER_NAME, List.of(new Rental(REGULAR_MOVIE, 20))));
  }

  @Test
  public void whenNewReleaseFilmRentedFor25Days_ShouldReturn75DollarsAnd2Points() {
    String expected = "Rental Record for customerName\n" +
        "\tnew release\t75.0\n" +
        "Amount owed is 75.0\n" +
        "You earned 2 frequent renter points";

    assertEquals(expected, Customer.getStatement(CUSTOMER_NAME, List.of(new Rental(NEW_RELEASE_MOVIE, 25))));
  }

  @Test
  public void whenMovieForChildrenFilmRentedFor5Days_ShouldReturn4_5DollarsAnd1Point() {
    String expected = "Rental Record for customerName\n" +
        "\tchildren\t4.5\n" +
        "Amount owed is 4.5\n" +
        "You earned 1 frequent renter points";

    assertEquals(expected, Customer.getStatement(CUSTOMER_NAME, List.of(new Rental(MOVIE_FOR_CHILDREN, 5))));
  }

  @Test
  public void whenMovieForChildrenFilmRentedFor50DaysAndNewReleaseFilmRentedFor25Days_ShouldReturn147DollarsAnd3Point() {
    String expected = "Rental Record for customerName\n" +
        "\tchildren\t72.0\n" +
        "\tnew release\t75.0\n" +
        "Amount owed is 147.0\n" +
        "You earned 3 frequent renter points";

    assertEquals(expected, Customer.getStatement(CUSTOMER_NAME, List.of(new Rental(MOVIE_FOR_CHILDREN, 50), new Rental(NEW_RELEASE_MOVIE, 25))));
  }

  @Test
  public void whenMovieForChildrenFilmRentedFor1DayAndNewReleaseFilmRentedFor3DaysAndRegularFilmRentedFor10Days_ShouldReturn24_5DollarsAnd4Point() {
    String expected = "Rental Record for customerName\n" +
        "\tchildren\t1.5\n" +
        "\tnew release\t9.0\n" +
        "\tregular\t14.0\n" +
        "Amount owed is 24.5\n" +
        "You earned 4 frequent renter points";

    assertEquals(expected, Customer.getStatement(CUSTOMER_NAME, List.of(new Rental(MOVIE_FOR_CHILDREN, 1),
        new Rental(NEW_RELEASE_MOVIE, 3),
        new Rental(REGULAR_MOVIE, 10))));
  }
}