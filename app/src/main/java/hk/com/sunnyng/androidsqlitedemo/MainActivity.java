package hk.com.sunnyng.androidsqlitedemo;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {


    private MoviesSQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private String[] columnsToSelect;
    private Cursor myCursor;
    private SimpleCursorAdapter dbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        columnsToSelect = new String[] {
                                MoviesSQLiteHelper.COLUMN_ID,
                                MoviesSQLiteHelper.COLUMN_TITLE,
                                MoviesSQLiteHelper.COLUMN_DIRECTOR,
                                MoviesSQLiteHelper.COLUMN_GROSS
        };

        sqlHelper = new MoviesSQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();

        myCursor = mydb.query(MoviesSQLiteHelper.TABLE_NAME, columnsToSelect, null, null, null, null, null);

        String columnsToDisplay[] = {
                                MoviesSQLiteHelper.COLUMN_TITLE,
                                MoviesSQLiteHelper.COLUMN_DIRECTOR,
                                MoviesSQLiteHelper.COLUMN_GROSS
        };

        int mappingToViews[] = {
                R.id.movieTitle,
                R.id.movieDirector,
                R.id.movieGross
        };

        dbAdapter = new SimpleCursorAdapter(this, R.layout.row, myCursor, columnsToDisplay, mappingToViews, 0);

        setListAdapter(dbAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Intent movieDetailsIntent = new Intent(this, MovieDetails.class);

        movieDetailsIntent.putExtra("movieId", id);

        startActivity(movieDetailsIntent);

    }
}
