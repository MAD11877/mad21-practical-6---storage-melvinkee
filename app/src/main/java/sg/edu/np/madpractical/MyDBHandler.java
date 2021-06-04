package sg.edu.np.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MyDBHandler extends SQLiteOpenHelper {
    public static ArrayList<User> userList = new ArrayList<>();

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "UsersDB.db";
    public static String USERS = "Users";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_ID = "Id";
    public static String COLUMN_FOLLOWED = "Followed";
    private static String TAB = "MAD Practical";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + USERS + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " TEXT," + COLUMN_FOLLOWED
                + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);

        Log.v(TAB, "Created Database");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS;
        Cursor cursor = db.rawQuery(query, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            User queryData = new User();
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            queryData.setFollowed(cursor.getInt(3) == 1);
            userList.add(queryData);

            cursor.moveToNext();
        }
        db.close();
        return userList;
    }

    public void updateUser(User user){
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_NAME + "=\"" + user.getName().toString() + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        User queryData = new User();

        if (cursor.moveToFirst())
        {
            queryData.setFollowed(user.isFollowed());
            cursor.close();
        }
        else
        {
            queryData = null;
        }
        db.close();
    }

    /*private int randomGen(){
        Random ran = new Random();
        int otp = ran.nextInt();
        return otp;
    }

    private boolean randomBool(){
        Random ran = new Random();
        int upperbound = 2;
        int randomBool = ran.nextInt(upperbound);
        if (randomBool == 0) {
            return false;
        }
        else if (randomBool == 1){
            return true;
        }
        else {
            return true;
        }
    }

    private User objCreator(){
        User newUser = new User();
        int newID = randomGen();
        String newDesc = String.valueOf(randomGen());
        boolean newStatus = randomBool();
        newUser.setName("Name" + String.valueOf(newID));
        newUser.setId(newID);
        newUser.setDescription("Description " + newDesc);
        newUser.setFollowed(newStatus);
        return newUser;
    }

     */
}
