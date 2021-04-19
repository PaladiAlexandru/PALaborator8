package main;
import connection.DatabaseConnection;
import dao.AssosiationDao;
import dao.GenresDao;
import dao.MoviesDao;
import model.Assosiation;
import model.Genre;
import model.Movie;

import java.net.ConnectException;
import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie(1,"Terminator","12-04-2021",60,1);
        Genre genre = new Genre(2,"Action");
        Assosiation assosiation = new Assosiation(1,1,2);

        MoviesDao moviesDao = new MoviesDao();
        GenresDao genresDao = new GenresDao();
        AssosiationDao assosiationDao = new AssosiationDao();

        genresDao.save(genre);
        moviesDao.save(movie);
        assosiationDao.save(assosiation);
    }
}
