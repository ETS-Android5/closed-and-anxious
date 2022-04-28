package dk.itu.closed_and_anxious.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import dk.itu.closed_and_anxious.Track;

/**
 * Schema of our database
 */
public class TrackDBSchema {
    // we call refer to the table as tracks
    /**
     * Our schema consist of a track table class, which contains a column class with headers equivalent to each attribute of the track
     */
    public static final class TrackTable {
        // name of table
        public static final String TABLENAME = "Tracks";

        public static final class Columns {
            // header for each track attribute
            public static final String TRACKEY = "tkey";
            public static final String TRACKNAME= "tname";
            public static final String TRACKDESC= "tdesc";
            public static final String TRACKCAT= "tcat";
            public static final String TRACKIMG= "timage";
        }
    }

}
