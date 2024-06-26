package org.example.testing1.movies.model;

import java.util.Objects;

public class Movie {
    private Integer id;
    private String name;
    private Integer minutes;
    private Genre genre;

    public Movie(String name, Integer minutes, Genre genre) { //llama al otro constructor, id null cuando no se registra
        this(null, name, minutes, genre);
    }

    public Movie(Integer id, String name, Integer minutes, Genre genre) {
        this.id = id;
        this.name = name;
        this.minutes = minutes;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(minutes, movie.minutes) && Objects.equals(name, movie.name) && genre == movie.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name, minutes, genre);
    }

}
