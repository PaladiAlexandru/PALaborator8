package dao;

import connection.DatabaseConnection;
import model.Actor;
import model.Actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDao implements Dao<Actor>{
    private static final Connection connection = DatabaseConnection.getInstance();
    @Override
    public Actor get(int id) {
        String query = "SELECT * FROM laborator8.actors where id=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            Actor actor = new Actor();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                actor.setId(rs.getInt("id"));
                actor.setName(rs.getString("name"));
            }
            if (check)
                return actor;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Actor> getAll() {
        String query = "SELECT * FROM laborator8.actors";
        PreparedStatement ps;
        List<Actor> actors = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);


            Actor actor = new Actor();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                actor.setId(rs.getInt("id"));
                actor.setName(rs.getString("name"));
                actors.add(actor);
            }
            if (check)
                return actors;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void save(Actor actor) {
        String query = "INSERT INTO laborator8.actors(id, name) VALUES(?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, actor.getId());
            ps.setString(2, actor.getName());


            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Actor actor) {
        String query = "UPDATE laborator8.movies set "
                + "name=? "
                + "WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,actor.getName());
            ps.setInt(2,actor.getId());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Actor actor) {
        String query = "DELETE FROM laborator8.actors WHERE id=?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,actor.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * Verifica daca exista actorul respectiv
     * @param actor actorul pe care vrem să-l cautam
     * @return id-ul actorului daca exista sau -1 in caz contrar
     */
    public int exists(Actor actor){
        String query = "SELECT id FROM laborator8.actors WHERE name=?";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,actor.getName());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("id");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
