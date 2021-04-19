package dao;

import connection.DatabaseConnection;
import model.Actor;
import model.Director;
import model.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDao implements Dao<Director> {
    private static final Connection connection = DatabaseConnection.getInstance();

    @Override
    public Director get(int id) {
        String query = "SELECT * FROM laborator8.directors where id=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            Director director = new Director();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                director.setId(rs.getInt("id"));
                director.setName(rs.getString("name"));
            }
            if (check)
                return director;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Director> getAll() {
        String query = "SELECT * FROM laborator8.directors";
        PreparedStatement ps;
        List<Director> directors = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);


            Director director = new Director();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                director.setId(rs.getInt("id"));
                director.setName(rs.getString("name"));
                directors.add(director);
            }
            if (check)
                return directors;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void save(Director director) {
        String query = "INSERT INTO laborator8.directors(id, name) VALUES(?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, director.getId());
            ps.setString(2, director.getName());


            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Director director) {
        String query = "UPDATE laborator8.movies set "
                + "name=? "
                + "WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,director.getName());
            ps.setInt(2,director.getId());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Director director) {
        String query = "DELETE FROM laborator8.directors WHERE id=?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,director.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * Verifica daca exista directorul respectiv
     * @param director directorul pe care vrem să-l cautam
     * @return id-ul directorului daca exista sau -1 in caz contrar
     */
    public int exists(Director director){
        String query = "SELECT id FROM laborator8.directors WHERE name=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,director.getName());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("id");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
