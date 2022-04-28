package dk.itu.closed_and_anxious.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import dk.itu.closed_and_anxious.Track;

// wraps and modifies the cursor. delegates all calls to the actual cursor object. The primary use for this class is to extend a cursor while overriding only a subset of its methods.
public class TrackCursorWrapper extends CursorWrapper {

    public TrackCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    // getting methods
    public Track getTrack() {
        // get the different attributes of a key from the table established above
        int tkey = getInt(getColumnIndex(TrackDBSchema.TrackTable.Columns.TRACKEY));
        String tname = getString(getColumnIndex(TrackDBSchema.TrackTable.Columns.TRACKNAME));
        String tdesc = getString(getColumnIndex(TrackDBSchema.TrackTable.Columns.TRACKDESC));
        String tcat = getString(getColumnIndex(TrackDBSchema.TrackTable.Columns.TRACKCAT));
        int timage = getInt(getColumnIndex(TrackDBSchema.TrackTable.Columns.TRACKIMG));

        return new Track(tkey, tname, tdesc, tcat, timage);
    }

}
