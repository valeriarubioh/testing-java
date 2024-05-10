package org.example.testing1.movies.data;

import org.example.testing1.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {
    Movie findById(long id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);

    Collection <Movie> findByName(String name);
}
