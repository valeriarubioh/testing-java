package org.example.testing1.movies.data;

import org.example.testing1.movies.model.Genre;
import org.example.testing1.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;

public class MovieRepositoryImpl implements MovieRepository {
    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {

        return jdbcTemplate.queryForObject("select * from movies where id = ?", movieMapper , id); // queryForObject returns result with a single row
    }

    @Override
    public Collection<Movie> findAll() {

        return jdbcTemplate.query("select * from movies",movieMapper); //hace una query a la BD y transforma cada pelicula a un objeto Movie
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("insert into movies (name,minutes,genre) values (?,?,?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString()); //hace actualizaciones a la bd
    }
    @Override
    public Collection <Movie> findByName(String name){
        return jdbcTemplate.query("select * from movies where lower(name) like ? ", movieMapper, name.toLowerCase()+'%');
    }
    private static RowMapper<Movie> movieMapper = (rs, rowNum) -> //transformacion de cada Row pelicula a un objeto Movie
        new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("minutes"),
            Genre.valueOf(rs.getString("genre"))
        );
}
