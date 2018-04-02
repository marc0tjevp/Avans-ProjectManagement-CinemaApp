package nl.marcovp.avans.cavanz.Domain;

import java.io.Serializable;

/**
 * Created by Sander on 3/29/2018.
 */

public class Showing implements Serializable {
    private final String TAG = getClass().getSimpleName();

    private Movie movie;
    private String date;
    private Hall hall;
    private String starttime;
    private String endtime;

    public Showing(Movie movie, String date, Hall hall, String starttime, String endtime) {
        this.movie = movie;
        this.date = date;
        this.hall = hall;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public Showing() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
