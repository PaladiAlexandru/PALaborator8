package dao;

import connection.DatabaseConnection;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class MoviesDao implements Dao<Movie> {

    private static final Connection connection = DatabaseConnection.getInstance();

    @Override
    public Movie get(int id) {
        String query = "SELECT * FROM laborator8.movies where id=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            Movie movie = new Movie();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                movie.setId(rs.getInt("id"));
                movie.setDuration(rs.getInt("duration"));
                movie.setRelease_date(rs.getString("release_date"));
                movie.setTitle(rs.getString("title"));
                movie.setScore(rs.getInt("score"));
            }
            if (check)
                return movie;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Movie> getAll() {
        String query = "SELECT * FROM laborator8.movies";
        PreparedStatement ps;
        List<Movie> movies = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);


            Movie movie = new Movie();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                movie.setId(rs.getInt("id"));
                movie.setDuration(rs.getInt("duration"));
                movie.setRelease_date(rs.getString("release_date"));
                movie.setTitle(rs.getString("title"));
                movie.setScore(rs.getInt("score"));
                movies.add(movie);
            }
            if (check)
                return movies;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void save(Movie movie) {
        String query = "INSERT INTO laborator8.movies(id, title,release_date, duration, score) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, movie.getId());
            ps.setString(2, movie.getTitle());
            ps.setString(3, movie.getRelease_date() );
            ps.setInt(4, movie.getDuration());
            ps.setInt(5, movie.getScore());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Movie movie) {
        String query = "UPDATE laborator8.movies set "
                + "title=? "
                + "release_date=? "
                + "duration=? "
                + "score=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,movie.getTitle());
            ps.setString(2, movie.getRelease_date());
            ps.setInt(3,movie.getDuration());
            ps.setInt(4,movie.getScore());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Movie movie) {
        String query = "DELETE FROM laborator8.movies WHERE id=?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,movie.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
