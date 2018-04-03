package nl.marcovp.avans.cavanz.Data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import nl.marcovp.avans.cavanz.Domain.Movie;

/**
 * Created by Sander on 3/29/2018.
 */

public class ApiHelper extends AsyncTask<Void, String, String> {
    private final String TAG = getClass().getSimpleName();
    private final String API_URL = "https://api.themoviedb.org/3/discover/movie?api_key=97af64173524ed8d6fe980c28dd5a2cb&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    private OnMovieSetAvailable listener;

    public ApiHelper(OnMovieSetAvailable onMovieSetAvailable) {
        this.listener = onMovieSetAvailable;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(Void... voids) {
        String response = getMoviesJSONAsString();

        Log.d(TAG, "doInBackground: received String: length: " + response.length());
        return response;

    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: called; String recieved length: " + s.length());

        ArrayList<Movie> movies = new ArrayList<>();

        String responseString =  s;
        if (responseString.equals("") || responseString == null){
            Log.d(TAG, "onPostExecute: empty response string");
            return;
        }


        try{
            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            ////////////////Loop through array and make movie objects for returnset.//////////

            for (int i = 0; i < jsonArray.length(); i++){

                Movie movie = new Movie();

                JSONObject movieJSONObject = jsonArray.getJSONObject(i);

                movie.setTitle(movieJSONObject.getString("title"));
                movie.setImageUrl("http://image.tmdb.org/t/p/w500" + movieJSONObject.getString("poster_path"));
                movie.setLanguage(movieJSONObject.getString("original_language"));
                movie.setRating(movieJSONObject.getDouble("vote_average"));
                movie.setSummary(movieJSONObject.getString("overview"));
                movie.setReleaseDate(movieJSONObject.getString("release_date"));

                Log.d(TAG, "onPostExecute: movie added! Title: " +movie.getTitle() + "  imageUrl: " + movie.getImageUrl() + " Rating: " + movie.getRating() + " ID: " + movie.getId());
                movies.add(movie);

            }

            Log.d(TAG, "OnMovieSetAvailable: returning dataset");
            listener.OnMovieSetAvailable(movies);
            return;




        } catch (JSONException j){
            Log.d(TAG, "onPostExecute: JSON Exception");
            j.getLocalizedMessage();
        }





    }


    public String getMoviesJSONAsString() {
        Log.d(TAG, "getMovies: called");
        try {

            /////////////////////////SETUP CONNECTION/////////////////
            URLConnection urlConnection = new URL(API_URL).openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");

            ////////////////////////GET DATA/////////////////////////
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200){
                Log.d(TAG, "getMovies: succesfull get request: code:" + httpURLConnection.getResponseCode());

                InputStream inputStream = httpURLConnection.getInputStream();

                String response = convertIStoString(inputStream);

                return response;
            }else {
                Log.d(TAG, "getMovies: error in get request: code: " + httpURLConnection.getResponseCode());
            }


        } catch (MalformedURLException m) {
            m.getLocalizedMessage();
        } catch (IOException io) {
            io.getLocalizedMessage();
        }


        return null;
    }


    private String convertIStoString(InputStream inputStream) throws IOException{
        Log.d(TAG, "convertIStoString: called");

        ///////////////////CONVERT InputStream to String////////////////////////

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String nextline;
        StringBuilder sb = new StringBuilder();

        while ((nextline = bufferedReader.readLine()) != null) {
            sb.append(nextline);
        }


        bufferedReader.close();

        return sb.toString();



    }
}

