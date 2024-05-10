package org.example.testing1.movies.data;

import org.example.testing1.movies.model.Genre;
import org.example.testing1.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {
    private MovieRepositoryImpl movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa"); //conexion a la bd h2 en memoria
        ScriptUtils.executeSqlScript(dataSource.getConnection(),new ClassPathResource("sql-scripts/test-data.sql")); //carga script para crear la bd con sql
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new MovieRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> movies = movieRepository.findAll();

        assertEquals(Arrays.asList(
                new Movie("Dark Knight", 152, Genre.ACTION),
                new Movie("Memento", 113, Genre.THRILLER),
                new Movie("Matrix", 136, Genre.ACTION)
        ),movies);
    }

    @Test
    public void load_movie_by_id() {
        Movie movie = movieRepository.findById(2);

        assertEquals(new Movie("Memento", 113, Genre.THRILLER),movie);
    }
    @Test
    public void insert_a_movie(){
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieRepository.saveOrUpdate(movie);
        Movie movieFromDb = movieRepository.findById(4);
        assertEquals(movie,movieFromDb);
    }
    @Test
    public void load_movies_by_name(){
        Collection<Movie> movies = movieRepository.findByName("dar");
        assertEquals(List.of(
                new Movie("Dark Knight", 152, Genre.ACTION)), movies);
    }
    @After
    //cada vez que se ejecute un test, borra la bd
    public void tearDown() throws Exception {
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }
}