package model;

import java.util.ArrayList;
import java.util.List;

public class Assosiation {
    private int id;
    private int genre_id;
    private int movie_id;
    private int director_id;
    private String actors_ids;


    public Assosiation() {

    }

    public Assosiation(int id, int genre_id, int movie_id) {
        this.id = id;
        this.genre_id = genre_id;
        this.movie_id = movie_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getDirectors_ids() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public String getActors_ids() {
        return actors_ids;
    }

    public void setActors_ids(String actors_ids) {
        this.actors_ids = actors_ids;
    }

    @Override
    public String toString() {
        return "Assosiation{" +
                "genre_id=" + genre_id +
                ", movie_id=" + movie_id +
                ", director_id=" + director_id +
                ", actors_ids=" + actors_ids +
                '}';
    }
}
