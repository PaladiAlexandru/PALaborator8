package utils;

import dao.*;
import model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportData {


    public static void loadData(){
        Movie movie = new Movie();
        Assosiation assosiation = new Assosiation();
        Actor actor = new Actor();
        Director director = new Director();
        Genre genre = new Genre();

        MoviesDao moviesDao = new MoviesDao();
        AssosiatonDao assosiatonDao =new AssosiatonDao();
        DirectorDao directorDao = new DirectorDao();
        ActorDao actorDao = new ActorDao();
        GenresDao genresDao = new GenresDao();


        String line = "";
        String spliter = ",";
        int movieId=1;
        int directorId=1;
        int actorId=1;
        int genreId=1;
        int assosiationId=1;
        int foundGenre;
        int foundActor;
        int foundDirector;
        StringBuilder actorsIds = new StringBuilder();
        try
        {

            BufferedReader br = new BufferedReader(new FileReader("IMDb movies.csv"));
            line= br.readLine(); // Scăpăm de prima linie
            while ((line = br.readLine()) != null)
            {
                actorsIds.delete(0,actorsIds.length());
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                String[] genres = data[5].split(",");
                for(String genreS: genres) {
                    genre.setName(genreS);
                    if((foundGenre = genresDao.exists(genre)) != -1){
                        genre.setId(foundGenre);
                    }else{
                        genre.setId(genreId);
                        genresDao.save(genre);
                        genreId++;
                    }

                }



                String[] actors = data[12].split(",");
                for(String actorS: actors){
                    actor.setName(actorS);
                    if((foundActor = actorDao.exists(actor)) != -1){
                        actor.setId(foundActor);
                    }else{
                        actor.setId(actorId);
                        actorDao.save(actor);
                        actorId++;
                    }
                    actorsIds.append(actorId+",");


                }
                director.setName(data[9]);
                if((foundDirector = directorDao.exists(director)) != -1){
                    actor.setId(foundDirector);
                }else{
                    director.setId(directorId);
                    directorDao.save(director);
                    directorId++;
                }




                movie.setId(movieId++);
                movie.setScore(Integer.parseInt(data[15]));
                movie.setDuration(Integer.parseInt(data[6]));
                movie.setTitle(data[1]);
                movie.setRelease_date(data[4]);

                moviesDao.save(movie);


                assosiation.setId(assosiationId++);
                assosiation.setDirector_id(director.getId());
                assosiation.setActors_ids(actorsIds.toString());
                assosiation.setMovie_id(movie.getId());
                assosiation.setGenre_id(genre.getId());

                assosiatonDao.save(assosiation);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
