package org.example.testing1.movies.service;

import org.example.testing1.movies.data.MovieRepository;
import org.example.testing1.movies.model.Genre;
import org.example.testing1.movies.model.Movie;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getGenre().equals(genre)).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByLength(int length) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getMinutes() <= length).collect(Collectors.toList());
    }
    public Collection<Movie> findMoviesByName(String name) {

        return movieRepository.findAll().stream()
                .filter(movie -> movie.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
    public Collection<Movie> findMoviesByTemplate(Movie template) {
        if (template.getMinutes() < 0) {
            throw new IllegalArgumentException("duration must be greater or equal than zero.");
        }
        List<Predicate<Movie>> filters = new ArrayList<>();
        if (template.getName() != null) {
            filters.add(movie -> movie.getName().toLowerCase().contains(template.getName().toLowerCase().trim()));
        }
        //with minutes as a primitive int since it cannot have a null value
        /*Optional.ofNullable(template.getMinutes())
                .ifPresent(minutes -> filters.add(movie -> movie.getMinutes() <= minutes));*/
        if(template.getMinutes() != null){
            filters.add(movie -> movie.getMinutes() <= template.getMinutes());
        }

        if (template.getGenre() != null) {
            filters.add(movie -> movie.getGenre().equals(template.getGenre()));
        }
        return movieRepository.findAll().stream()
                .filter(movie -> filters.stream().allMatch(filter -> filter.test(movie)))
                .collect(Collectors.toList());
    }
}
