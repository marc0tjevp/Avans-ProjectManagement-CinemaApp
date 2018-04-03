package nl.marcovp.avans.cavanz.Util;

import android.os.AsyncTask;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Data.OnDataSetAvail;
import nl.marcovp.avans.cavanz.Data.SQLiteHelper;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;

/**
 * Created by Sander on 4/3/2018.
 */

public class GetDataSetAsync extends AsyncTask{
    private OnDataSetAvail onDataSetAvail;

    private ArrayList<Showing> showings;
    public GetDataSetAsync(OnDataSetAvail onDataSetAvail) {
        this.onDataSetAvail = onDataSetAvail;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        SQLiteHelper db = new SQLiteHelper(onDataSetAvail.getContext());

        Movie movie = (Movie) objects[0];

       showings = db.getAllShowingsFor(movie.getId());
       ArrayList<String> dates = new ArrayList<>();

        for (Showing showing: showings
             ) {
            if (!dates.contains(showing.getDate())){
                dates.add(showing.getDate());
            }

        }



        return dates;

    }
    @Override
    protected void onPostExecute(Object p) {
        super.onPostExecute(p);

        ArrayList<String> o = (ArrayList<String>) p;

        onDataSetAvail.onDataSetAvail( o, showings);
    }


}
