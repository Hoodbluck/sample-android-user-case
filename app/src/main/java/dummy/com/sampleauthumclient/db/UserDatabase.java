package dummy.com.sampleauthumclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dummy.com.sampleauthumclient.models.User;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
public class UserDatabase extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Userdatabase";

    private static final String TABLE_User = "User";

    // Table Columns names
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_EMAIL_ADDRESS = "email_address";

    private Context mContext;

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        createUsers(db);
    }

    private void createUsers(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_User
                + "("
                + KEY_FIRST_NAME +            " TEXT,"
                + KEY_LAST_NAME +          " TEXT,"
                + KEY_EMAIL_ADDRESS +   " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    private void createSystems(SQLiteDatabase db){}

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    // Drop database after emailing it out
    public void dropUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    /**
     * Adds a new game list to the database. Any games previousely
     * int database will be removed.
     * @param user
     */
    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_FIRST_NAME, user.getFirstName());
            values.put(KEY_LAST_NAME, user.getLastName());
            values.put(KEY_EMAIL_ADDRESS, user.getEmail());

            // Inserting Row
            db.insert(TABLE_User, null, values);
        }catch (Exception e) {
            //NOP
        }
        finally {
            db.close(); // Closing database connection
        }
    }

    private boolean isUserTableEmpty(SQLiteDatabase db) {
        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM User", null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a all games for a given system
     *
     * @return
     */
    public User getAllGamesForSystem(String email) {
        // Select All Query
        //TODO create a sanitized query

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_User, new String[]{"*"}, KEY_EMAIL_ADDRESS + "=?", new String[] {email}, null, null, null, null);
        // looping through all rows and adding to list
        User user = null;
        try{
            if (cursor.moveToFirst()) {
                user = new User();
                user.setFirstName(cursor.getString(0));
                user.setLastName(cursor.getString(1));
                user.setEmail(cursor.getString(2));
            }

        }finally{
            cursor.close();
            db.close();
        }

        return user;
    }
}
