package hk.com.sunnyng.androidsqlitedemo;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {


    private MoviesSQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private String[] columnsToSelect;
    private Cursor myCursor;
    private SimpleCursorAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle bundle = this.getIntent().getExtras();
        long movieId = bundle.getLong("movieId");

        columnsToSelect = new String[] {
                MoviesSQLiteHelper.COLUMN_ID,
                MoviesSQLiteHelper.COLUMN_TITLE,
                MoviesSQLiteHelper.COLUMN_DIRECTOR,
                MoviesSQLiteHelper.COLUMN_CAST,
                MoviesSQLiteHelper.COLUMN_STORY,
                MoviesSQLiteHelper.COLUMN_GROSS
        };
        sqlHelper = new MoviesSQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();

        String where = "_id = ?";
        String[] args = {String.valueOf(movieId)};

        myCursor = mydb.query(MoviesSQLiteHelper.TABLE_NAME,
                columnsToSelect, where, args, null, null, null
        );

        TextView tvMovieTitle = (TextView) findViewById(R.id.movieTitle);
        TextView tvMovieGross = (TextView) findViewById(R.id.movieGross);
        TextView tvMovieDirector = (TextView) findViewById(R.id.movieDirector);
        TextView tvMovieCast = (TextView) findViewById(R.id.movieCast);
        TextView tvMovieStory = (TextView) findViewById(R.id.movieStory);
        ImageView moviePoster = (ImageView) findViewById(R.id.moviePoster);

        myCursor.moveToFirst();
        if (!myCursor.isAfterLast()) {
            // Set activity title
            setTitle(myCursor.getString(
                    myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_TITLE)
            ));

            // Set poster
            Resources res = getResources();
            String posterResourceName  = "poster_" + movieId;
            int resId = res.getIdentifier(posterResourceName, "drawable", getPackageName());
            moviePoster.setImageResource(resId);

            // Set movie title
            tvMovieTitle.setText(
                    myCursor.getString(
                            myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_TITLE)
                    )
            );

            // Set movie gross
            tvMovieGross.setText(
                    myCursor.getString(
                            myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_GROSS)
                    )
            );

            // Set movie director
            tvMovieDirector.setText(
                    myCursor.getString(
                            myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_DIRECTOR)
                    )
            );

            // Set movie cast
            tvMovieCast.setText(
                    myCursor.getString(
                            myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_CAST)
                    )
            );

            // Set movie story
            tvMovieStory.setText(
                    myCursor.getString(
                            myCursor.getColumnIndex(MoviesSQLiteHelper.COLUMN_STORY)
                    )
            );
        }
    }
}
