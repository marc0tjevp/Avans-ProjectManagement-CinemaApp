package nl.marcovp.avans.cavanz.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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


    public SQLiteHelper(Context context, int version) {
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
                MOVIE_COLUMN_MOVIE_ID + "` INTEGER, `" +
                MOVIE_COLUMN_TITLE + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_RATING + "` NUMERIC NOT NULL, `" +
                MOVIE_COLUMN_RELEASE_DATE + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_SUMMARY + "` TEXT NOT NULL, `" +
                MOVIE_COLUMN_LANGAUGE + "` TEXT NOT NULL, `"
                + MOVIE_COLUMN_IMG_URL + "` BLOB NOT NULL, `" +
                MOVIE_COLUMN_VIDEO_URL + "` TEXT NOT NULL, " +
                "PRIMARY KEY(`" + MOVIE_COLUMN_MOVIE_ID + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE '" + ORDER_TABLE_NAME + "' ( `" +
                ORDER_COLUMN_ORDER_ID + "` INTEGER, `" +
                ORDER_COLUMN_CUSTOMER_ID + "` INTEGER NOT NULL, `" +
                ORDER_COLUMN_TICKET_ID + "` INTEGER NOT NULL, " +
                "FOREIGN KEY(`" + ORDER_COLUMN_CUSTOMER_ID + "`) REFERENCES `" +
                CUSTOMER_TABLE_NAME + "`(`" + CUSTOMER_COLUMN_CUSTOMERID + "`), " +
                "PRIMARY KEY(`" + ORDER_COLUMN_ORDER_ID + "`), " +
                "FOREIGN KEY(`" + ORDER_COLUMN_TICKET_ID + "`) " +
                "REFERENCES `" + TICKET_TABLE_NAME + "`(`" + TICKET_COLUMN_TICKET_ID + "`) )");

        sqLiteDatabase.execSQL("CREATE TABLE "+SHOWING_TABLE_NAME+" " +
                "( `"+SHOWING_COLUMN_SHOW_ID+"` INTEGER, `"+
                SHOWING_COLUMN_DATE+"` INTEGER NOT NULL, `"+
                SHOWING_COLUMN_STARTING_TIME+"` INTEGER NOT NULL, `"+
                SHOWING_COLUMN_ENDING_TIME+"` INTEGER NOT NULL, `"+
                SHOWING_COLUMN_MOVIE_ID+"` INTEGER NOT NULL, `"+
                SHOWING_COLUMN_HALL_CODE+"` INTEGER NOT NULL, " +
                "PRIMARY KEY(`"+SHOWING_COLUMN_SHOW_ID+"`), " +
                "FOREIGN KEY(`"+SHOWING_COLUMN_HALL_CODE+"`) " +
                "REFERENCES `"+HALL_TABLE_NAME+"`(`"+HALL_COLUMN_HALL_CODE+"`), " +
                "FOREIGN KEY(`"+MOVIE_COLUMN_MOVIE_ID+"`) " +
                "REFERENCES `"+MOVIE_TABLE_NAME+"`(`"+MOVIE_COLUMN_MOVIE_ID+"`) )");

        sqLiteDatabase.execSQL("CREATE TABLE "+TICKET_TABLE_NAME+" " +
                "( `"+TICKET_COLUMN_TICKET_ID+"` INTEGER, `"+
                TICKET_COLUMN_PRICE+"` NUMERIC NOT NULL, `"+
                TICKET_COLUMN_ROW_NUMBER+"` INTEGER NOT NULL, `"+
                TICKET_COLUMN_SEAT_NUMBER+"` INTEGER NOT NULL, `"+
                TICKET_COLUMN_SHOW_ID+"` INTEGER NOT NULL, FOREIGN KEY(`"+
                TICKET_COLUMN_SHOW_ID+"`) REFERENCES `"+
                SHOWING_TABLE_NAME+"`(`"+SHOWING_COLUMN_SHOW_ID+"`), " +
                "PRIMARY KEY(`"+TICKET_COLUMN_TICKET_ID+"`) )" +
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


}
