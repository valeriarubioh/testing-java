package org.example.testing1.movies.service;

import org.example.testing1.movies.data.MovieRepository;
import org.example.testing1.movies.model.Genre;
import org.example.testing1.movies.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieServiceShould {
    private MovieService movieService;

    @Before
    public void setUp() throws Exception {

        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);

        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                        new Movie(4, "Super 8", 112, Genre.THRILLER),
                        new Movie(5, "Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION)
                )
        );

        movieService = new MovieService(movieRepository);
    }

    @Test
    public void return_movies_by_genre() {

        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertEquals(Arrays.asList(3, 6), getMovieIds(movies));
    }

    @Test
    public void return_movies_by_length() {

        Collection<Movie> movies = movieService.findMoviesByLength(119);
        assertEquals(Arrays.asList(2, 3, 4, 5, 6), getMovieIds(movies));
    }
    @Test
    public void return_movies_by_name(){
        Collection<Movie> movies = movieService.findMoviesByName("DARK");
        //assertEquals(Arrays.asList(1), getMovieIds(movies));
        assertEquals(List.of(
                new Movie(1,"Dark Knight", 152, Genre.ACTION)), movies);
    }
    @Test
    public void templateWhenUsingGenreAndMinutes() {
        Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, null, 180, Genre.ACTION));
        assertEquals(Arrays.asList(1, 7), getMovieIds(movies));
    }
    @Test
    public void templateWhenUsingNegativeMinutes() {
        assertThrows(
                IllegalArgumentException.class,
                () -> movieService.findMoviesByTemplate(new Movie(null, null, -15, Genre.ACTION))
        );
    }
    @Test
    public void templateWhenUsingNameAndMinutes() {
        Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, "Matrix", 136, null));
        assertEquals(Collections.singletonList(7), getMovieIds(movies));
    }
    @Test
    public void templateWhenUsingMinutes() {
        Collection<Movie> movies = movieService.findMoviesByTemplate(new Movie(null, null, 114, null));
        assertEquals(Arrays.asList(2,4,5,6), getMovieIds(movies));
    }
    private List<Integer> getMovieIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }

}