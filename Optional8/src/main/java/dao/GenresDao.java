package dao;

import connection.DatabaseConnection;
import model.Actor;
import model.Genre;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenresDao implements Dao<Genre>{
    private static final Connection connection = DatabaseConnection.getInstance();

    @Override
    public Genre get(int id) {
        String query = "SELECT * FROM laborator8.movies where id=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            Genre genre = new Genre();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
            }
            if (check)
                return genre;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Genre> getAll() {
        String query = "SELECT * FROM laborator8.genres";
        PreparedStatement ps;
        List<Genre> genres = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);


            Genre genre = new Genre();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                genre.setId(rs.getInt("id"));
                genre.setName(rs.getString("name"));
                genres.add(genre);
            }
            if (check)
                return genres;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void save(Genre genre) {
        String query = "INSERT INTO laborator8.genres(id, name) VALUES(?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, genre.getId());
            ps.setString(2, genre.getName());


            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Genre genre) {
        String query = "UPDATE laborator8.movies set "
                + "name=? "
                + "WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,genre.getName());
            ps.setInt(2,genre.getId());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Genre genre) {
        String query = "DELETE FROM laborator8.genres WHERE id=?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,genre.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Verifica daca exista genul respectiv
     * @param genre genul pe care vrem să-l cautam
     * @return id-ul genului daca exista sau -1 in caz contrar
     */
    public int exists(Genre genre){
        String query = "SELECT id FROM laborator8.genres WHERE name=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,genre.getName());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("id");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
