package com.example.organizeupv3.SQL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.organizeupv3.models.Group;
import com.example.organizeupv3.models.Place;
import com.example.organizeupv3.models.Plan;
import com.example.organizeupv3.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "organizeup.db";

    //Tables Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ANSWERS = "answers";
    private static final String TABLE_GROUPS = "groups";
    private static final String TABLE_GROUP_MEMBERS = "groupmembers";
    private static final String TABLE_PLACES = "places";
    private static final String TABLE_PLANS = "plans";
    private static final String TABLE_RESERVATIONS = "reservations";

    //Table Users columns
    private static final String COLUMN_USER_ID = "userID";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";

    //Table Answers columns
    private static final String COLUMN_PLAN_ID = "planID";
    private static final String COLUMN_GROUP_ID = "groupID";
    private static final String COLUMN_MEMBER_ID = "memberID";
    private static final String COLUMN_ANSWER = "answer";

    //Table Groups columns
    private static final String COLUMN_GROUP_NAME = "groupname";
    private static final String COLUMN_CREATOR_ID = "creatorID";

    //Table group members columns
    //Already above

    //Table places columns
    private static final String COLUMN_PLACE_ID = "placeID";
    private static final String COLUMN_OWNER_ID = "ownerID";
    private static final String COLUMN_PLACE_NAME = "placename";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_OPEN_TIME = "opentime";
    private static final String COLUMN_CLOSE_TIME = "closetime";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_LOCATION = "location";

    //Table plans columns
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MESSAGE = "message";

    //Table reservation columns
    private static final String COLUMN_RESERVATION_ID = "reservationID";
    private static final String COLUMN_NUM_PEOPLE = "numpeople";
    private static final String COLUMN_APPROVAL = "approval";

    //Create users table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_EMAIL + " TEXT," + COLUMN_FIRSTNAME + " TEXT," +
            COLUMN_LASTNAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT)";

    //Create Answers table sql query
    private String CREATE_ANSWERS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "(" +
            COLUMN_PLAN_ID + " INTEGER, " +
            COLUMN_GROUP_ID + " INTEGER, " +
            COLUMN_MEMBER_ID + " INTEGER," +
            COLUMN_ANSWER + " TEXT," +
            " PRIMARY KEY (" + COLUMN_PLAN_ID + ", " + COLUMN_GROUP_ID + ", " + COLUMN_MEMBER_ID + ")," +
            "FOREIGN KEY (" + COLUMN_PLAN_ID + ") REFERENCES " + TABLE_PLANS + "(" + COLUMN_PLAN_ID + ")," +
            "FOREIGN KEY (" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + ")," +
            "FOREIGN KEY (" + COLUMN_MEMBER_ID + ") REFERENCES " + TABLE_GROUP_MEMBERS + "(" + COLUMN_MEMBER_ID + "))";

    //Create Groups table sql query
    private String CREATE_GROUPS_TABLE = "CREATE TABLE " + TABLE_GROUPS + "(" +
            COLUMN_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_GROUP_NAME + " TEXT, " +
            COLUMN_CREATOR_ID + " INTEGER," +
            "FOREIGN KEY (" + COLUMN_CREATOR_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";

    //Create GroupMembers table sql query
    private String CREATE_GROUP_MEMBERS_TABLE = "CREATE TABLE " + TABLE_GROUP_MEMBERS + "(" +
            COLUMN_GROUP_ID + " INT," +
            COLUMN_MEMBER_ID + " INT," +
            "FOREIGN KEY (" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + ")," +
            "FOREIGN KEY (" + COLUMN_MEMBER_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_USER_ID + "))";

    //Create Places table sql query
    private String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES + "(" +
            COLUMN_PLACE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_OWNER_ID + " INTEGER," +
            COLUMN_PLACE_NAME + " TEXT," +
            COLUMN_CAPACITY + " INTEGER," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_OPEN_TIME + " TEXT," +
            COLUMN_CLOSE_TIME + " TEXT," +
            COLUMN_PHONE + " INTEGER," +
            COLUMN_TYPE + " TEXT," +
            COLUMN_LOCATION + " TEXT," +
            "FOREIGN KEY (" + COLUMN_OWNER_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_USER_ID + "))";

    //Create Plan table sql query
    private String CREATE_PLANS_TABLE = "CREATE TABLE " + TABLE_PLANS + "(" +
            COLUMN_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_GROUP_ID + " INTEGER," +
            COLUMN_PLACE_ID + " INTEGER," +
            COLUMN_TIME + " TEXT," +
            COLUMN_DATE + " TEXT," +
            COLUMN_MESSAGE + " TEXT," +
            "FOREIGN KEY (" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + ")," +
            "FOREIGN KEY (" + COLUMN_PLACE_ID + ") REFERENCES " + TABLE_PLACES + "(" + COLUMN_PLACE_ID + "))";

    //Create reservations table sql query
    private String CREATE_RESERVATIONS_TABLE = "CREATE TABLE " + TABLE_RESERVATIONS + "(" +
            COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_GROUP_ID + " INTEGER, " +
            COLUMN_PLACE_ID + " INTEGER, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_TIME + " TEXT, " +
            COLUMN_NUM_PEOPLE + " INTEGER," +
            COLUMN_MESSAGE + " TEXT, " +
            COLUMN_APPROVAL + " TEXT, " +
            "FOREIGN KEY (" + COLUMN_GROUP_ID + ") REFERENCES " + TABLE_GROUPS + "(" + COLUMN_GROUP_ID + ")," +
            "FOREIGN KEY (" + COLUMN_PLACE_ID + ") REFERENCES " + TABLE_PLACES + "(" + COLUMN_PLACE_ID + "))";

    //Drop users table sql query
    private String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS;

    //Drop answers table sql query
    private String DROP_ANSWERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_ANSWERS;

    //Drop groups table sql query
    private String DROP_GROUPS_TABLE = "DROP TABLE IF EXISTS " + TABLE_GROUPS;

    //Drop groupmembers table sql query
    private String DROP_GROUP_MEMBERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_GROUP_MEMBERS;

    //Drop places table sql query
    private String DROP_PLACES_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLACES;

    //Drop plans table sql query
    private String DROP_PLANS_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLANS;

    //Drop reservations table sql query
    private String DROP_RESERVATIONS_TABLE = "DROP TABLE IF EXISTS " + TABLE_RESERVATIONS;


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ANSWERS_TABLE);
        db.execSQL(CREATE_GROUPS_TABLE);
        db.execSQL(CREATE_GROUP_MEMBERS_TABLE);
        db.execSQL(CREATE_PLACES_TABLE);
        db.execSQL(CREATE_PLANS_TABLE);
        db.execSQL(CREATE_RESERVATIONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Users table if exists
        db.execSQL(DROP_USERS_TABLE);
        db.execSQL(DROP_ANSWERS_TABLE);
        db.execSQL(DROP_GROUPS_TABLE);
        db.execSQL(DROP_GROUP_MEMBERS_TABLE);
        db.execSQL(DROP_PLACES_TABLE);
        db.execSQL(DROP_PLANS_TABLE);
        db.execSQL(DROP_RESERVATIONS_TABLE);
        //Create tables again
        onCreate(db);
    }

    //Add user record
    public boolean addUser(User user) {
        //Prepare writeable database
        SQLiteDatabase db = this.getWritableDatabase();
        //Put values
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_FIRSTNAME, user.getFirstname());
        values.put(COLUMN_LASTNAME, user.getLastname());
        values.put(COLUMN_PASSWORD, user.getPassword());
        //Insert row in database
        db.insert(TABLE_USERS, null, values);
        db.close();
        return true;
    }

    //Fetch user data and return a list of user records
    @SuppressLint("Range")
    public List<User> getAllUser() {
        //Array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_EMAIL,
                COLUMN_FIRSTNAME,
                COLUMN_LASTNAME,
                COLUMN_PASSWORD,
        };
        //Sort with user lastname
        String sortOrder = COLUMN_LASTNAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Run the query to get user data
        //Query equal to select * from users order by
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        //Go through all rows and add them to the list
        if (cursor.moveToFirst()) {
            do {
                //Initialize user model and set its values with the row user
                User user = new User();
                user.setUserID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setFirstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
                user.setLastname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                //Add user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //Return the list
        return userList;
    }

    //Update user record
    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_FIRSTNAME, user.getFirstname());
        values.put(COLUMN_LASTNAME, user.getLastname());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.update(TABLE_USERS, values, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getUserID())});
        db.close();

        return true;
    }

    //Delete user record
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USERS, COLUMN_USER_ID + " = ?", new String[]{String.valueOf(user.getUserID())});
        db.close();
    }

    //Check if user email already exists in db
    public boolean checkUserEmail(String email) {
        String[] columns = {COLUMN_USER_ID};

        SQLiteDatabase db = this.getReadableDatabase();

        //Selection criteria
        String selection = COLUMN_EMAIL + " = ?";
        //Selection arguement
        String[] selectionArgs = {email};

        //Query user table
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //Check if user login credentials are correct
    public boolean checkUserLogin(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    //Get user details if sucessfull login
    public User getUserDetails(String email) {
        User user = new User();
        //Array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_FIRSTNAME,
                COLUMN_LASTNAME,
                COLUMN_EMAIL,
        };

        SQLiteDatabase db = this.getReadableDatabase();
        //Selection criteria
        String selection = COLUMN_EMAIL + " =?";
        //Selection arguements
        String[] selectionArgs = {email};
        //Query user table with conditions
        Cursor c = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    @SuppressLint("Range") int uID = c.getInt(c.getColumnIndex(COLUMN_USER_ID));
                    @SuppressLint("Range") String fn = c.getString(c.getColumnIndex(COLUMN_FIRSTNAME));
                    @SuppressLint("Range") String ln = c.getString(c.getColumnIndex(COLUMN_LASTNAME));
                    @SuppressLint("Range") String em = c.getString(c.getColumnIndex(COLUMN_EMAIL));
                    user.setUserID(uID);
                    user.setFirstname(fn);
                    user.setLastname(ln);
                    user.setEmail(em);
                } while (c.moveToNext());
            }
        }
        return user;
    }

    //Add group details to database
    public boolean addGroup(Group group) {
        //Prepare writeable db
        SQLiteDatabase db = this.getWritableDatabase();

        //Put values
        ContentValues values = new ContentValues();
        values.put(COLUMN_CREATOR_ID, group.getCreatorID());
        values.put(COLUMN_GROUP_NAME, group.getGroupName());
        //Insert row

        db.insert(TABLE_GROUPS, null, values);
        db.close();
        return true;
    }

    //Add place details to database
    public boolean addPlace(Place place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OWNER_ID, place.getOwnerID());
        values.put(COLUMN_PLACE_NAME, place.getPlaceName());
        values.put(COLUMN_CAPACITY, place.getCapacity());
        values.put(COLUMN_ADDRESS, place.getAddress());
        values.put(COLUMN_OPEN_TIME, place.getOpenTime());
        values.put(COLUMN_CLOSE_TIME, place.getCloseTime());
        values.put(COLUMN_PHONE, place.getPhone());
        values.put(COLUMN_TYPE, place.getType());

        db.insert(TABLE_PLACES, null, values);
        db.close();
        return true;
    }

    /**
     * Getting all labels
     * returns list of labels
     */
    public List<String> getAllLabelsGroups() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GROUPS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));//adding 1st column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return list;
    }

    /**
     * Getting all labels
     * returns list of labels
     */
    public List<String> getAllLabelsPlaces() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLACES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));//adding 1st column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning labels
        return list;
    }

    //Fetch user data and return a list of user records
    @SuppressLint("Range")
    public List<Plan> getAllPlans() {
        //Array of columns to fetch
        String[] columns = {
                COLUMN_PLAN_ID,
                COLUMN_GROUP_ID ,
                COLUMN_PLACE_ID ,
                COLUMN_TIME ,
                COLUMN_DATE,
                COLUMN_MESSAGE
        };
        //Sort with user lastname
        String sortOrder = COLUMN_PLAN_ID + " ASC";
        List<Plan> planList = new ArrayList<Plan>();

        SQLiteDatabase db = this.getReadableDatabase();

        //Run the query to get user data
        //Query equal to select * from users order by
        Cursor cursor = db.query(TABLE_PLANS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        //Go through all rows and add them to the list
        if (cursor.moveToFirst()) {
            do {
                //Initialize user model and set its values with the row user
                Plan plan = new Plan();
                plan.setplanID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PLAN_ID))));
                plan.setGroupID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_GROUP_ID))));
                plan.setPlaceID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PLACE_ID))));
                plan.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                plan.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                plan.setMessage(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE)));
                //Add user record to list
                planList.add(plan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //Return the list
        return planList;
    }

    public boolean addPlan(Plan plan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUP_ID, plan.getGroupID());
        values.put(COLUMN_PLACE_ID, plan.getPlaceID());
        values.put(COLUMN_TIME, plan.getTime());
        values.put(COLUMN_DATE, plan.getDate());
        values.put(COLUMN_MESSAGE, plan.getMessage());

        db.insert(TABLE_PLANS, null, values);
        db.close();
        return true;
    }

}
