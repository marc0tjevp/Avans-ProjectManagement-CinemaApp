package nl.marcovp.avans.cavanz.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import nl.marcovp.avans.cavanz.Domain.Hall;
import nl.marcovp.avans.cavanz.Domain.Movie;
import nl.marcovp.avans.cavanz.Domain.Showing;

/**
 * Created by Sander on 3/29/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private final String TAG = getClass().getSimpleName();

    private static final String DB_NAME = "Cavanz";
    private static final int DB_V = 1;


    private static final String CUSTOMER_TABLE_NAME = "Customer";
    private static final String CUSTOMER_COLUMN_CUSTOMERID = "CustomerID";
    private static final String CUSTOMER_COLUMN_NAME = "Name";
    private static final String CUSTOMER_COLUMN_EMAIL = "Email";

    private static final String HALL_TABLE_NAME = "Hall";
    private static final String HALL_COLUMN_HALL_CODE = "HallCode";
    private static final String HALL_COLUMN_SEAT_ROWS_AMOUNT = "SeatRowsAmount";
    private static final String HALL_COLUMN_SEAT_PER_ROW = "SeatPerRow";

    private static final String MOVIE_TABLE_NAME = "Movie";
    private static final String MOVIE_COLUMN_MOVIE_ID = "MovieID";
    private static final String MOVIE_COLUMN_TITLE = "Title";
    private static final String MOVIE_COLUMN_RATING = "Rating";
    private static final String MOVIE_COLUMN_SUMMARY = "Summary";
    private static final String MOVIE_COLUMN_LANGAUGE = "Language";
    private static final String MOVIE_COLUMN_IMG_URL = "ImageURL";
    private static final String MOVIE_COLUMN_VIDEO_URL = "VideoURL";
    private static final String MOVIE_COLUMN_RELEASE_DATE = "ReleaseDate";

    private static final String ORDER_TABLE_NAME = "Order";
    private static final String ORDER_COLUMN_ORDER_ID = "OrderID";
    private static final String ORDER_COLUMN_CUSTOMER_ID = "CustomerID";
    private static final String ORDER_COLUMN_TICKET_ID = "TicketID";

    private static final String SHOWING_TABLE_NAME = "Showing";
    private static final String SHOWING_COLUMN_SHOW_ID = "ShowID";
    private static final String SHOWING_COLUMN_DATE = "Date";
    private static final String SHOWING_COLUMN_STARTING_TIME = "StartingTime";
    private static final String SHOWING_COLUMN_ENDING_TIME = "EndingTime";
    private static final String SHOWING_COLUMN_MOVIE_ID = "MovieID";
    private static final String SHOWING_COLUMN_HALL_CODE = "HallCode";

    private static final String TICKET_TABLE_NAME = "Ticket";
    private static final String TICKET_COLUMN_TICKET_ID = "TicketID";
    private static final String TICKET_COLUMN_PRICE = "Price";
    private static final String TICKET_COLUMN_ROW_NUMBER = "RowNumber";
    private static final String TICKET_COLUMN_SEAT_NUMBER = "SeatNumber";
    private static final String TICKET_COLUMN_SHOW_ID = "ShowID";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_V);
        Log.d(TAG, "SQLiteHelper: constructor called");


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("CREATE TABLE " + CUSTOMER_TABLE_NAME + " " +
                "( `" + CUSTOMER_COLUMN_CUSTOMERID + "` INTEGER, `" +
                CUSTOMER_COLUMN_NAME + "` TEXT NOT NULL, `" +
                CUSTOMER_COLUMN_EMAIL + "` TEXT NOT NULL, " +
                "PRIMARY KEY(`" + CUSTOMER_COLUMN_CUSTOMERID + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE `" + HALL_TABLE_NAME + "` ( `" +
                HALL_COLUMN_HALL_CODE + "` INTEGER, `" +
                HALL_COLUMN_SEAT_ROWS_AMOUNT + "` INTEGER NOT NULL, `" +
                HALL_COLUMN_SEAT_PER_ROW + "` INTEGER NOT NULL, " +
                "PRIMARY KEY(`" + HALL_COLUMN_HALL_CODE + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE " + MOVIE_TABLE_NAME + " ( `" +
                MOVIE_COLUMN_MOVIE_ID + "` INTEGER PRIMARY KEY, `" +
                MOVIE_COLUMN_TITLE + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_RATING + "` NUMERIC NOT NULL, `" +
                MOVIE_COLUMN_RELEASE_DATE + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_SUMMARY + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_LANGAUGE + "` TEXT NOT NULL, `"
                + MOVIE_COLUMN_IMG_URL + "` BLOB NOT NULL, `" +
                MOVIE_COLUMN_VIDEO_URL + "` TEXT  " + ");");

        sqLiteDatabase.execSQL("CREATE TABLE '" + ORDER_TABLE_NAME + "' ( `" +
                ORDER_COLUMN_ORDER_ID + "` INTEGER, `" +
                ORDER_COLUMN_CUSTOMER_ID + "` INTEGER NOT NULL, `" +
                ORDER_COLUMN_TICKET_ID + "` INTEGER NOT NULL, " +
                "FOREIGN KEY(`" + ORDER_COLUMN_CUSTOMER_ID + "`) REFERENCES `" +
                CUSTOMER_TABLE_NAME + "`(`" + CUSTOMER_COLUMN_CUSTOMERID + "`), " +
                "PRIMARY KEY(`" + ORDER_COLUMN_ORDER_ID + "`), " +
                "FOREIGN KEY(`" + ORDER_COLUMN_TICKET_ID + "`) " +
                "REFERENCES `" + TICKET_TABLE_NAME + "`(`" + TICKET_COLUMN_TICKET_ID + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE " + SHOWING_TABLE_NAME + " " +
                "( `" + SHOWING_COLUMN_SHOW_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `" +
                SHOWING_COLUMN_DATE + "` TEXT NOT NULL, `" +
                SHOWING_COLUMN_STARTING_TIME + "` TEXT NOT NULL, `" +
                SHOWING_COLUMN_ENDING_TIME + "` TEXT NOT NULL, `" +
                SHOWING_COLUMN_MOVIE_ID + "` TEXT NOT NULL, `" +
                SHOWING_COLUMN_HALL_CODE + "` TEXT NOT NULL, " +
                //    "PRIMARY KEY(`" + SHOWING_COLUMN_SHOW_ID + "`), " +
                "FOREIGN KEY(`" + SHOWING_COLUMN_HALL_CODE + "`) " +
                "REFERENCES `" + HALL_TABLE_NAME + "`(`" + HALL_COLUMN_HALL_CODE + "`), " +
                "FOREIGN KEY(`" + MOVIE_COLUMN_MOVIE_ID + "`) " +
                "REFERENCES `" + MOVIE_TABLE_NAME + "`(`" + MOVIE_COLUMN_MOVIE_ID + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE " + TICKET_TABLE_NAME + " " +
                "( `" + TICKET_COLUMN_TICKET_ID + "` INTEGER, `" +
                TICKET_COLUMN_PRICE + "` NUMERIC NOT NULL, `" +
                TICKET_COLUMN_ROW_NUMBER + "` INTEGER NOT NULL, `" +
                TICKET_COLUMN_SEAT_NUMBER + "` INTEGER NOT NULL, `" +
                TICKET_COLUMN_SHOW_ID + "` INTEGER NOT NULL, FOREIGN KEY(`" +
                TICKET_COLUMN_SHOW_ID + "`) REFERENCES `" +
                SHOWING_TABLE_NAME + "`(`" + SHOWING_COLUMN_SHOW_ID + "`), " +
                "PRIMARY KEY(`" + TICKET_COLUMN_TICKET_ID + "`) )" +
                "");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + CUSTOMER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + HALL_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + SHOWING_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + ORDER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + TICKET_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE " + MOVIE_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }


    public ArrayList<Movie> getAllMovies() {
        Log.d(TAG, "getAllMovies: called");
        ArrayList<Movie> array_list = new ArrayList<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + MOVIE_TABLE_NAME + ";", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            Movie movie = new Movie();

            //////////Putting values in the movie

            movie.setId(res.getInt(res.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
            movie.setTitle(res.getString(res.getColumnIndex(MOVIE_COLUMN_TITLE)));
            movie.setSummary(res.getString(res.getColumnIndex(MOVIE_COLUMN_SUMMARY)));
            movie.setReleaseDate(res.getString(res.getColumnIndex(MOVIE_COLUMN_RELEASE_DATE)));
            movie.setRating(res.getDouble(res.getColumnIndex(MOVIE_COLUMN_RATING)));
            movie.setLanguage(res.getString(res.getColumnIndex(MOVIE_COLUMN_LANGAUGE)));
            movie.setImageUrl(res.getString(res.getColumnIndex(MOVIE_COLUMN_IMG_URL)));
            movie.setVideoUrl(res.getString(res.getColumnIndex(MOVIE_COLUMN_VIDEO_URL)));


            array_list.add(movie);
            res.moveToNext();

        }
        ///////////array containing all movies in db
        close();
        return array_list;
    }


    public Movie getMovie(String movieID) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + MOVIE_TABLE_NAME + " WHERE " + SHOWING_COLUMN_MOVIE_ID + " = " + movieID + ";", null);
        res.moveToFirst();


        Movie movie = new Movie();

        //////////Putting values in the movie

        movie.setId(res.getInt(res.getColumnIndex(MOVIE_COLUMN_MOVIE_ID)));
        movie.setTitle(res.getString(res.getColumnIndex(MOVIE_COLUMN_TITLE)));
        movie.setSummary(res.getString(res.getColumnIndex(MOVIE_COLUMN_SUMMARY)));
        movie.setReleaseDate(res.getString(res.getColumnIndex(MOVIE_COLUMN_RELEASE_DATE)));
        movie.setRating(res.getDouble(res.getColumnIndex(MOVIE_COLUMN_RATING)));
        movie.setLanguage(res.getString(res.getColumnIndex(MOVIE_COLUMN_LANGAUGE)));
        movie.setImageUrl(res.getString(res.getColumnIndex(MOVIE_COLUMN_IMG_URL)));
        movie.setVideoUrl(res.getString(res.getColumnIndex(MOVIE_COLUMN_VIDEO_URL)));


        ///////////array containing all movies in db
        close();
        return movie;
    }

    public Hall getHall(String hallCode) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + HALL_TABLE_NAME + " WHERE " + HALL_COLUMN_HALL_CODE + " = " + hallCode + ";", null);
        res.moveToFirst();


        Hall hall = new Hall();

        //////////Putting values in the hall

        hall.setHallNumber(res.getString(res.getColumnIndex(HALL_COLUMN_HALL_CODE)));
        hall.setSeatPerRow(res.getInt(res.getColumnIndex(HALL_COLUMN_SEAT_PER_ROW)));
        hall.setRowAmount(res.getInt(res.getColumnIndex(HALL_COLUMN_SEAT_ROWS_AMOUNT)));


        close();
        return hall;
    }


    public ArrayList<Showing> getAllShowings() {
        Log.d(TAG, "getAllShowings: called");
        ArrayList<Showing> array_list = new ArrayList<Showing>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SHOWING_TABLE_NAME + ";", null);
        res.moveToFirst();


        while (res.isAfterLast() == false) {

            Showing showing = new Showing();


            //////////Putting values in the showing

            showing.setShowID(res.getInt(res.getColumnIndex(SHOWING_COLUMN_SHOW_ID)));
            showing.setMovie(getMovie(res.getString(res.getColumnIndex(SHOWING_COLUMN_MOVIE_ID))));
            showing.setHall(getHall(res.getString(res.getColumnIndex(SHOWING_COLUMN_HALL_CODE))));
            showing.setStartingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_STARTING_TIME)));
            showing.setEndingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_ENDING_TIME)));
            showing.setDate(res.getString(res.getColumnIndex(SHOWING_COLUMN_DATE)));

            array_list.add(showing);
            res.moveToNext();

        }
        ///////////array containing all movies in db
        close();
        return array_list;
    }

    public ArrayList<Showing> getAllShowingsFor(int MovieID) {
        Log.d(TAG, "getAllShowings: called");
        ArrayList<Showing> array_list = new ArrayList<Showing>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SHOWING_TABLE_NAME + " WHERE " + SHOWING_COLUMN_MOVIE_ID + " = '" + MovieID + "';" , null);
        res.moveToFirst();


        while (res.isAfterLast() == false) {

            Showing showing = new Showing();


            //////////Putting values in the showing

            showing.setShowID(res.getInt(res.getColumnIndex(SHOWING_COLUMN_SHOW_ID)));
            showing.setMovie(getMovie(res.getString(res.getColumnIndex(SHOWING_COLUMN_MOVIE_ID))));
            showing.setHall(getHall(res.getString(res.getColumnIndex(SHOWING_COLUMN_HALL_CODE))));
            showing.setStartingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_STARTING_TIME)));
            showing.setEndingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_ENDING_TIME)));
            showing.setDate(res.getString(res.getColumnIndex(SHOWING_COLUMN_DATE)));

            array_list.add(showing);
            res.moveToNext();

        }
        ///////////array containing all movies in db
        close();
        return array_list;
    }

    public Showing getShowing(String showID) {
        Log.d(TAG, "getAllShowings: called");


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + SHOWING_TABLE_NAME + " WHERE " + SHOWING_COLUMN_SHOW_ID + " = '" + showID + "';" , null);
        res.moveToFirst();




            Showing showing = new Showing();


            //////////Putting values in the showing

            showing.setShowID(res.getInt(res.getColumnIndex(SHOWING_COLUMN_SHOW_ID)));
            showing.setMovie(getMovie(res.getString(res.getColumnIndex(SHOWING_COLUMN_MOVIE_ID))));
            showing.setHall(getHall(res.getString(res.getColumnIndex(SHOWING_COLUMN_HALL_CODE))));
            showing.setStartingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_STARTING_TIME)));
            showing.setEndingTime(res.getString(res.getColumnIndex(SHOWING_COLUMN_ENDING_TIME)));
            showing.setDate(res.getString(res.getColumnIndex(SHOWING_COLUMN_DATE)));



        ///////////array containing all movies in db
        close();
        return showing;
    }

    public boolean insertMovie(Movie movie) {
        Log.d(TAG, "insertMovie: called");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MOVIE_COLUMN_MOVIE_ID, movie.getId());
        contentValues.put(MOVIE_COLUMN_IMG_URL, movie.getImageUrl());
        contentValues.put(MOVIE_COLUMN_VIDEO_URL, movie.getVideoUrl());
        contentValues.put(MOVIE_COLUMN_TITLE, movie.getTitle());
        contentValues.put(MOVIE_COLUMN_SUMMARY, movie.getSummary());
        contentValues.put(MOVIE_COLUMN_RATING, movie.getRating());
        contentValues.put(MOVIE_COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(MOVIE_COLUMN_LANGAUGE, movie.getLanguage());


        Log.d(TAG, "insertMovie: " + movie.getTitle() + " ID:(" + movie.getId() + ")");


        db.insert(MOVIE_TABLE_NAME,
                null,
                contentValues);
        close();
        return true;
    }


    public boolean insertShowing(Showing showing) {
        Log.d(TAG, "insertShowing: called");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SHOWING_COLUMN_DATE, showing.getDate());
        contentValues.put(SHOWING_COLUMN_ENDING_TIME, showing.getEndingTime());
        contentValues.put(SHOWING_COLUMN_STARTING_TIME, showing.getStartingTime());
        contentValues.put(SHOWING_COLUMN_HALL_CODE, showing.getHall().getHallNumber());
        contentValues.put(SHOWING_COLUMN_MOVIE_ID, showing.getMovie().getId());


        Log.d(TAG, "insertShowing: " + showing.getMovie().getTitle() + " ID:("+showing.getMovie().getId()+") " + " FOR " + showing.getDate() + " IN " + showing.getHall().getHallNumber() + " BETWEEN " + showing.getStartingTime() + " AND " + showing.getEndingTime());


        db.insert(SHOWING_TABLE_NAME,
                null,
                contentValues);
        close();
        return true;
    }


    public boolean insertHall(Hall hall) {
        Log.d(TAG, "insertHall: called");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HALL_COLUMN_HALL_CODE, hall.getHallNumber());
        contentValues.put(HALL_COLUMN_SEAT_PER_ROW, hall.getSeatPerRow());
        contentValues.put(HALL_COLUMN_SEAT_ROWS_AMOUNT, hall.getRowAmount());


        Log.d(TAG, "insertHALL: " + hall.getHallNumber());


        db.insert(HALL_TABLE_NAME,
                null,
                contentValues);
        close();
        return true;
    }


    public boolean createTestData() {
        Log.d(TAG, "createTestData: CALLED");

        ArrayList<Movie> movies = getAllMovies();

        Cursor res = getReadableDatabase().
                rawQuery("SELECT * FROM " + HALL_TABLE_NAME + ";"
                        , null);
        res.moveToFirst();

        try {
            if (res.isNull(0)) ;
        } catch (CursorIndexOutOfBoundsException c) {
            Log.d(TAG, "createTestData: NO TEST DATA AVAILABLE CONFIRMED");


            /////////////////if no data, create testdata here

            //////////////////////////////hall////////////////////////////////
            Log.d(TAG, "createTestData: CREATING HALL TEST DATA");
            SQLiteDatabase db = getWritableDatabase();
            ArrayList<Hall> halls = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Hall hall = new Hall();
                hall.setHallNumber("" + (i+1));
                hall.setRowAmount(12);
                hall.setSeatPerRow(12);
                insertHall(hall);

                halls.add(hall);

            }


            ////////////////////////////SHOWING//////////////////////////////
            Log.d(TAG, "createTestData: CREATING SHOWING TEST DATA");
            db = getWritableDatabase();

            String year = "2018";
            String month = "04";
            String day = (new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())).substring(8, 10);
            Log.d(TAG, "createTestData: date" + year + "-" + month + "-" + day);

            for (Movie movie : movies
                    ) {

                ///////////////create 30 showings per movie, spread over 10 days////////////////
                for (int i = 0; i < 30; i++) {
                    Showing showing = new Showing();


                    showing.setHall(halls.get(new Random().nextInt(5)));
                    showing.setMovie(movie);

                    String dateDay;
                    if ((Integer.parseInt(day) + (i / 3)) < 10) {
                        dateDay = "0" + (Integer.parseInt(day) + (i / 3));
                    } else {
                        dateDay = "" + (Integer.parseInt(day) + (i / 3));
                    }
                    showing.setDate(year + "-" + month + "-" + dateDay);
                    showing.setStartingTime((12 + (i % 3)) + ":00");
                    showing.setEndingTime((14 + (i % 3)) + ":30");

                    insertShowing(showing);

                }


            }

            return true;
        }


        Log.d(TAG, "createTestData: TESTDATA IS ALREADY AVAIABLE. ABORTING");
        close();
        return false;


    }


}
