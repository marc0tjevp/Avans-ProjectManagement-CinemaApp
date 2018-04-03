package nl.marcovp.avans.cavanz.Data;

import android.content.Context;

import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Showing;

/**
 * Created by Sander on 4/3/2018.
 */

public interface OnDataSetAvail {
    public void onDataSetAvail(ArrayList<String> dates, ArrayList<Showing> dataSet);
    public Context getContext();
}
