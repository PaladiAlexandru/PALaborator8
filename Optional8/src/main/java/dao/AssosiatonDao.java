package dao;

import connection.DatabaseConnection;
import model.Assosiation;
import model.Assosiation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssosiatonDao implements Dao<Assosiation> {
    private static final Connection connection = DatabaseConnection.getInstance();
    @Override
    public Assosiation get(int id) {
        String query = "SELECT * FROM laborator8.assosiation where id=?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            Assosiation assosiation = new Assosiation();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                assosiation.setId(rs.getInt("id"));
                assosiation.setGenre_id(rs.getInt("genre_id"));
                assosiation.setMovie_id(rs.getInt("movie_id"));
                assosiation.setActors_ids(rs.getString("actors_ids"));
                assosiation.setDirector_id(rs.getInt("directors_ids"));

            }
            if (check)
                return assosiation;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Assosiation> getAll() {
        String query = "SELECT * FROM laborator8.assosiation";
        PreparedStatement ps;
        List<Assosiation> assosiations = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);


            Assosiation assosiation = new Assosiation();
            ResultSet rs = ps.executeQuery();

            boolean check = false;

            while (rs.next()) {
                check = true;
                assosiation.setId(rs.getInt("id"));
                assosiation.setGenre_id(rs.getInt("genre_id"));
                assosiation.setMovie_id(rs.getInt("movie_id"));

                assosiation.setActors_ids(rs.getString("actors_ids"));
                assosiation.setDirector_id(rs.getInt("director_id"));

                assosiations.add(assosiation);
            }
            if (check)
                return assosiations;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void save(Assosiation assosiation) {
        String query = "INSERT INTO laborator8.assosiation(id,genre_id, movie_id,director_id, actors_ids)" +
                " VALUES(?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, assosiation.getId());
            ps.setInt(2, assosiation.getGenre_id());
            ps.setInt(3, assosiation.getMovie_id());
            ps.setInt(4, assosiation.getDirectors_ids());

            ps.setString(5, assosiation.getActors_ids());


            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Assosiation assosiation) {
        String query = "UPDATE laborator8.movies set "
                + "genre_id=? "
                + "movie_id=? "
                + "actors_ids=? "
                + "director_id=? "
                + "WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,assosiation.getGenre_id());
            ps.setInt(2,assosiation.getMovie_id());
            ps.setString(3,assosiation.getActors_ids());
            ps.setInt(4,assosiation.getDirectors_ids());
            ps.setInt(5,assosiation.getId());

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Assosiation assosiation) {
        String query = "DELETE FROM laborator8.assosiation WHERE id=?";

        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,assosiation.getId());

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
