package nl.marcovp.avans.cavanz.Data;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Movie;

/**
 * Created by Sander on 3/29/2018.
 */

public interface OnMovieSetAvailable {
    public void OnMovieSetAvailable(ArrayList<Movie> movies);
}