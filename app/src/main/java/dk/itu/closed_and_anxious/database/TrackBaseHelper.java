package dk.itu.closed_and_anxious.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TrackBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "meditationDB.db"; // our db we create from our file

    public TrackBaseHelper(Context context) {
        super(context,DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TRACKDB", "started");
        // create the database
            db.execSQL("CREATE TABLE 'Tracks' (" +
                    "'tkey' INT," +
                    "'tname' VARCHAR," +
                    "'tdesc' VARCHAR," +
                    "'tcat' VARCHAR," +
                    "'timage' INT" +
                    ");"
            );

        Log.i("TRACKDB", "insertion");
           // db.execSQL("INSERT INTO Tracks (tkey, tname, tdesc, tcat, timage) VALUES (1800000, 'Gently Does It', 'An atmospheric, uplifting soundscape. Credit: Ketsa', 'Noise', 700112)");
        //name	description (incl. Credit)	image-ID	key (audio-id)	category
        //Gently Does It	An atmospheric, uplifting soundscape. Credit: Ketsa	700112	1800000	Noise


        // The cursor goes through each row in the Table and logs them out
        Cursor cursor= db.query("Tracks",null, null, null, null, null, null);
        Log.i("TRACKDB", "CURSOR STARTS DOING MAGIC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("TRACKDB", "key: " +cursor.getInt(0)+", name: "+cursor.getString(1)+", des: "+cursor.getString(2)+", cat: "+cursor.getString(3)+", image: "+cursor.getInt(4));
            cursor.moveToNext();
        }
        cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
