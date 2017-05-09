package hk.com.sunnyng.androidsqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunnyng on 16/4/2017.
 */

public class MoviesSQLiteHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 5;

    public static final String TABLE_NAME = "boxoffice";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_CAST = "moviecast";
    public static final String COLUMN_STORY= "moviestory";
    public static final String COLUMN_GROSS = "gross";

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_TITLE + " text not null, "
                    + COLUMN_DIRECTOR + " text not null, "
                    + COLUMN_CAST + " text not null, "
                    + COLUMN_STORY + " text not null, "
                    + COLUMN_GROSS + " text not null"
                    + ");";

    private static final String DATABASE_CREATE_2 =
            "CREATE TABLE " + "director"
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_TITLE + " text not null, "
                    + COLUMN_DIRECTOR + " text not null"
                    + ");";


    public MoviesSQLiteHelper(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " VALUES("
                + "1, "
                + "'Logan', "
                + "'James Mangold', "
                + "'Hugh Jackman, Patrick Stewart, Dafne Keen', "
                + "'In the near future, a weary Logan cares for an ailing Professor X somewhere on the Mexican border.', "
                + "'$200M'"
                + " )"
        );
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " VALUES("
                + "2, "
                + "'Get out', "
                + "'Jordan Peele', "
                + "'Daniel Kaluuya, Allison Williams, Bradley Whitford', "
                + "'A young African-American man visits his Caucasian girlfriend ...', "
                + "'$150M'"
                + " )"
        );
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " VALUES("
                + "3, "
                + "'Hacksaw Ridge', "
                + "'Mel Gibson', "
                + "'Andrew Garfield, Sam Worthington, Luke Bracey', "
                + "'WWII American Army Medic Desmond T. Doss, who served during the Battle of Okinawa, refuses to kill people, and becomes the first man in American history to receive the Medal of Honor without firing a shot.', "
                + "'$100M'"
                + " )"
        );
        db.execSQL("INSERT INTO " + TABLE_NAME
                + " VALUES("
                + "4, "
                + "'La La Land', "
                + "'Damien Chazelle', "
                + "'Ryan Gosling, Emma Stone, Rosemarie DeWitt', "
                + "'A jazz pianist falls for an aspiring actress in Los Angeles.', "
                + "'$80M'"
                + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
