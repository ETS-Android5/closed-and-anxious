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

    public void initialize(Context context){
        if (trackDatab == null) {
            trackDatab = new TrackBaseHelper(context.getApplicationContext()).getWritableDatabase();
            if (getValues().size() == 0) fillItemsDB();
        }
    }

    public void addTrack(int k, String n, String d, String c, int i){
        Track newTrack = new Track(k, n, d, c, i);
        ContentValues values = getContentValues(newTrack);
        trackDatab.insert(TrackDBSchema.TrackTable.TABLENAME, null, values);
    }


    public void fillItemsDB() {
        addTrack(1800000, "Gently Does It", "An atmospheric, uplifting soundscape. Credit: Ketsa", "Noise", 700112);
        System.out.println("PRINTED");
    }


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

    // Database helper methods to convert between Items and database rows
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
