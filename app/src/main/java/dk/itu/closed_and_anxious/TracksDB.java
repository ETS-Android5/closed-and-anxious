package dk.itu.closed_and_anxious;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import dk.itu.closed_and_anxious.database.TrackBaseHelper;
import dk.itu.closed_and_anxious.database.TrackCursorWrapper;
import dk.itu.closed_and_anxious.database.TrackDBSchema;

public class TracksDB extends ViewModel { // ViewModel - instanciated in activity it is kept and will live as long as the activity does
    private static SQLiteDatabase trackDatab;

    /**
     * Method for initializing the database.
     *
     * @param context
     */
    public void initialize(Context context){
        if (trackDatab == null) {
            trackDatab = new TrackBaseHelper(context.getApplicationContext()).getWritableDatabase();
            if (getValues().size() == 0)  {
                fillItemsDB();
            }
        }
    }

    /**
     * Method for adding tracks to the database.
     *
     * @param k the key of a track as resource id
     * @param n the name of a track
     * @param d the description of a track
     * @param c the category of a track
     * @param i the image of a track as a resource id
     */
    public void addTrack(int k, String n, String d, String c, int i){
        Track newTrack = new Track(k, n, d, c, i);
        ContentValues values = getContentValues(newTrack);
        trackDatab.insert(TrackDBSchema.TrackTable.TABLENAME, null, values);
    }

    /**
     * Method for populating the database.
     * Invoked in the initialize method, if the database is not already populated.
     */
    public void fillItemsDB() {
        addTrack(1800000, "Gently Does It", "An atmospheric, uplifting soundscape. Credit: Ketsa", "Noise", 700112);
        System.out.println("PRINTED");
    }

    /**
     * Helper method for getting all track objects (rows) from the database.
     * Invoked in initialize method, to check if the database is populated.
     *
     * @return an ArrayList containing all the track objects from the database.
     */
    public ArrayList<Track> getValues() {
        ArrayList<Track> tracks = new ArrayList<Track>();
        TrackCursorWrapper cursor = queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tracks.add(cursor.getTrack());
            //System.out.println("💥💥💥💥💥💥💥💥💥💥" + tracks.get(0));
            cursor.moveToNext();
        }
        cursor.close();
        return tracks;
    }

    /**
     * Helper method for getValues to send a query to the database.
     * The query requests all the information (columns) for one track in the database.
     *
     * @param whereClause is set to null.
     * @param whereArgs is set to null.
     * @return a new TrackCursorWrapper object based on the result set from the query contained by the cursor.
     */

    static private TrackCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = trackDatab.query(
                TrackDBSchema.TrackTable.TABLENAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new TrackCursorWrapper(cursor);
    }

    /**
     * Database helper Method for setting up a track object to be stored in the database as a row.
     * Used in addTrack method, a ContentValues object stores the track values for later insertion in the database.
     *
     * @param track
     * @return a ContentValues object with the values of the track.
     */
    private static ContentValues getContentValues(Track track) {
        ContentValues values =  new ContentValues();
        values.put(TrackDBSchema.TrackTable.Columns.TRACKEY, track.getKey());
        values.put(TrackDBSchema.TrackTable.Columns.TRACKNAME, track.getdName());
        values.put(TrackDBSchema.TrackTable.Columns.TRACKDESC, track.getDescription());
        values.put(TrackDBSchema.TrackTable.Columns.TRACKCAT, track.getCategory());
        values.put(TrackDBSchema.TrackTable.Columns.TRACKIMG, track.getdName());
        return values;
    }

}
