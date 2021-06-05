package com.example.fyp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.fyp.ObjectClass.Category;
import com.example.fyp.ObjectClass.OrgUsers;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.ObjectClass.UsersGoal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "FYP.db";
    public static final int DB_VERSION = 1;

    // for Users table
    public static final String USERS_TABLE = "Users";
    public static final String ID = "user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FULLNAME = "fullname";
    public static final String DOB = "dob";
    public static final String USER_PHONE_NO = "phoneno";
    public static final String USER_ROLE = "UserRole";
    public static final String USER_ADDRESS = "address";
    public static final String USER_PROFILE_IMAGE = "image";
    public static final String USER_ABOUT = "about";

    //For OrgUsers
    public static final String ORGUSER_TABLE = "OrgUsers";
    public static final String ORG_ID = "org_id";
    public static final String ORG_EMAILADDRESS = "email_address";
    public static final String ORG_CONTACT_NO = "ContactNo";
    public static final String ORG_CONTACT_NAME = "contact_name";
    public static final String ORG_ADDRESS = "address";
    public static final String ORG_NAME = "org_name";
    public static final String ORG_PASSWORD = "password";
    public static final String ORG_VERIFIED = "verified";

    //Category Table
    public static final String CATEGORY_TABLE = "Category";
    public static final String CAT_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";
    public static final String CATEGORY_DESC = "category_desc";
    public static final String CATEGORY_IMAGE = "category_image";

    //User Goal Table
    public static final String USERGOAL_TABLE = "UserGoalTable";
    public static final String GOAL_ID = "goal_id";
    public static final String GOALTYPE_ID = "goal_type_id";
    public static final String USER_ID = "user_id";
    public static final String GOAL_NAME = "goal_name";
    public static final String GOAL_DESC = "goal_desc";
    public static final String CREATED = "datetime_created";
    public static final String COMPLETED = "datetime_completed";
    public static final String ACCOMPLISHED = "accomplished";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // create tables here
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creates the Users table
        String createUsersTable ="CREATE TABLE " + USERS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL, "
                + FULLNAME + " TEXT NOT NULL, " + DOB + " TEXT NOT NULL, " + USER_PHONE_NO + " INTEGER NOT NULL, " + USER_ROLE + " TEXT NOT NULL, " + USER_ADDRESS + " TEXT NOT NULL, " + USER_ABOUT + " TEXT, "+ USER_PROFILE_IMAGE + " BLOB);";

        String createOrgUsersTable ="CREATE TABLE " + ORGUSER_TABLE + " (" + ORG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORG_EMAILADDRESS + " TEXT NOT NULL, " + ORG_CONTACT_NO + " TEXT NOT NULL, " + ORG_CONTACT_NAME + " TEXT NOT NULL, "
                + ORG_ADDRESS + " TEXT NOT NULL, " + ORG_NAME + " TEXT NOT NULL, " + ORG_PASSWORD + " TEXT NOT NULL, " + ORG_VERIFIED + " INTEGER NOT NULL);";

        String createCategoryTable = "CREATE TABLE " + CATEGORY_TABLE + " (" + CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " TEXT NOT NULL, " + CATEGORY_DESC + " TEXT NOT NULL, " + CATEGORY_IMAGE + " BLOB NOT NULL);";

        String createUsersGoalTable = "CREATE TABLE " + USERGOAL_TABLE + " (" + GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GOALTYPE_ID + " INTEGER NOT NULL, "
                + USER_ID + " INTEGER NOT NULL, " + GOAL_NAME + " TEXT NOT NULL, " + GOAL_DESC + " TEXT NOT NULL, " + CREATED + " TEXT NOT NULL, "
                + COMPLETED + " TEXT, " + ACCOMPLISHED + " INTEGER DEFAULT 0, FOREIGN KEY (" + GOALTYPE_ID +") REFERENCES " + CATEGORY_TABLE + " (" + CAT_ID + "), FOREIGN KEY (" + USER_ID + ") REFERENCES " + USERS_TABLE + "(" + USER_ID + "));";

        db.execSQL(createUsersTable);
        db.execSQL(createOrgUsersTable);
        db.execSQL(createCategoryTable);
        db.execSQL(createUsersGoalTable);


    }

    // if there is a better version bla bla
    // prevent shit from happening
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + USERS_TABLE);
        db.execSQL("DROP TABLE " + ORGUSER_TABLE);
        db.execSQL("DROP TABLE " + CATEGORY_TABLE);
        onCreate(db);
    }

    // adding a row to the table
    // adding the senior to the user table
    public boolean addUser(Users User) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, User.getUsername());
        cv.put(PASSWORD, User.getPassword());
        cv.put(FULLNAME, User.getFull_name());
        cv.put(DOB, User.getDob());
        cv.put(USER_PHONE_NO, User.getPhoneNo());
        cv.put(USER_ROLE, User.getUserRole());
        cv.put(USER_ADDRESS, User.getAddress());
        cv.put(USER_PROFILE_IMAGE, User.getImage());
        cv.put(USER_ABOUT, User.getAbout());

        // if it inserts successfully, insert == 1 if not insert == -1
        // success = positive
        // fail = negative
        long insert = database.insert(USERS_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    public boolean addTemporaryAdmin() {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, "testadmin");
        cv.put(PASSWORD, "Admin123!");
        cv.put(FULLNAME, "alyssa noob");
        cv.put(DOB, "12/5/2021");
        cv.put(USER_PHONE_NO, 81112354);
        cv.put(USER_ROLE, "Admin");
        cv.put(USER_ADDRESS, "WHC");
        cv.putNull(USER_PROFILE_IMAGE);
        long insert = database.insert(USERS_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    public boolean allowRegister(OrgUsers orgUsers){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORG_EMAILADDRESS, orgUsers.getEmail_address());
        cv.put(ORG_VERIFIED, orgUsers.getApproved());
        cv.put(ORG_CONTACT_NO, orgUsers.getContactNo());
        cv.put(ORG_CONTACT_NAME, orgUsers.getContact_name());
        cv.put(ORG_ADDRESS, orgUsers.getAddress());
        cv.put(ORG_NAME, orgUsers.getOrg_name());
        cv.put(ORG_PASSWORD, orgUsers.getPassword());

        long insert = database.insert(ORGUSER_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }



    // add profile picture image to users
    public void addImage(byte[] image, String user) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(USER_PROFILE_IMAGE, image);
        database.update(USERS_TABLE, cv, "username ='" + user + "'", null);
    }

    public int updateAdmin(String username, String password, int phoneNumb) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PASSWORD, password);
        cv.put(USER_PHONE_NO, phoneNumb);
        int update = database.update(USERS_TABLE, cv, "username=?", new String[]{username});
        database.close();
        return update;
    }

    //test
    // deletes admin by username and where userRole = admin
    // if return > 0 == true; (delete successful)
    // if return == 0 == false; (delete unsuccessful)
    public boolean deleteAdmin(String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(USERS_TABLE, "username = '" + username + "' AND UserRole = 'Admin'", null) > 0;
    }



    public int checkAdminExists(String u) {
        int adminExists = 0;
        String sql = "SELECT COUNT(*) FROM Users WHERE username = '" + u + "' AND UserRole = 'Admin'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            adminExists = cursor.getInt(0);
        }
        cursor.close();
        return adminExists;
    }
    public Cursor retrieveCurrentAdmin(String u) {
        String sql = "SELECT fullname, dob, phoneno FROM Users WHERE username ='" + u + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if(db != null){
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    public int updateSenior(String username, String address, int phoneNumb , String about ) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_PHONE_NO, phoneNumb);
        cv.put(USER_ADDRESS, address);
        cv.put(USER_ABOUT, about);
        int update = database.update(USERS_TABLE, cv, "username=?", new String[]{username});
        database.close();
        return update;

    }


    // retrieves image.
    public Bitmap retrieveImage(String u) {
        String sql = "SELECT image FROM Users WHERE username='" + u + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()){
            byte[] imgByte = cursor.getBlob(0);
            cursor.close();
            if (imgByte != null) {
                return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            }
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return null;
    }



    // checks for username duplicates in database
    public int checkUsernameDuplicate(String u) {
        int usernameCount = 0;
        String sql = "SELECT COUNT(username) FROM Users WHERE username = '"+ u +"'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            usernameCount = cursor.getInt(0);
        }
        cursor.close();
        return usernameCount;
    }

    //checks for email dplicates in database
    public int checkEmailDuplicate(String e) {
        int emailCount = 0;
        String sql = "SELECT COUNT(email_address) FROM OrgUsers WHERE email_address ='" + e +"'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            emailCount = cursor.getInt(0);
        }
        cursor.close();
        return emailCount;
    }

    // remove the rows from user table
    public boolean removeAllRows() {
        SQLiteDatabase database = this.getWritableDatabase();
        int delete = database.delete(USERS_TABLE, null, null);
        database.close();

        if (delete == -1) return false;
        else return true;

    }

    // validate login by checking if username and password matches
    public int validateLogin(String u, String p) {
        int rowCount = 0;
        String sql = "SELECT COUNT() FROM Users WHERE username = '"+ u +"' AND password = '"+ p + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    // valid org login
    public int validateOrgLogin(String e, String p) {
        int rowCount = 0;
        String sql = "SELECT COUNT() FROM OrgUsers WHERE email_address ='" + e +"' AND password = '" + p + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    //validate if organisation's login = email
    public int checkOrgLogin(String email, String password){
        int rowCount = 0;
        String sql = "SELECT COUNT() FROM OrgUsers WHERE email_address = '"+ email +"' AND password = '"+ password +"'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    // checks for the type of user. user / admin / organisation
    public String checkAccountType(String u) {
        String userType ="";
        String sql = "SELECT UserRole from Users WHERE username = '"+ u +"'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            userType = c.getString(0);
        }
        return userType;
    }

    public String getUserFullName(String u) {
        String userFullName ="";
        String sql = "SELECT fullname FROM Users WHERE username ='" + u + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            userFullName = c.getString(0);
        }
        return userFullName;
    }

    // obtain username
    public String getUserName(String un) {
        String userName ="";
        String sql = "SELECT username FROM Users WHERE username ='" + un + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            userName = c.getString(0);
        }
        return userName;
    }

    //obtain DOB
    public String getDateOfBirth(String dob) {
        String getDOB ="";
        String sql = "SELECT dob FROM Users WHERE username ='" + dob + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            getDOB = c.getString(0);
        }
        return getDOB;
    }

    //obtain phone number
    public String getPhoneNumber(String pn) {
        String getpn ="";
        String sql = "SELECT phoneno FROM Users WHERE username ='" + pn + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            getpn = c.getString(0);
        }
        return getpn;
    }

    //obtain Address
    public String getUserAddress(String a) {
        String getadd ="";
        String sql = "SELECT address FROM Users WHERE username ='" + a + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            getadd = c.getString(0);
        }
        return getadd;
    }

    //obtain about
    public String getUserAbout(String ub) {
        String getabout ="";
        String sql = "SELECT about FROM Users WHERE username ='" + ub + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            getabout = c.getString(0);
        }
        return getabout;
    }

    // gets org name, org contact name, contact number from database
    public Cursor readOrgData() {
        String sql = "SELECT org_name, contact_name, ContactNo FROM " + ORGUSER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readUnapprovedOrgData() {
        String sql = "SELECT org_name FROM " + ORGUSER_TABLE + " WHERE verified = 0";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readOrgData(String name) {
        String sql = "SELECT org_name, contact_name, ContactNo, email_address, address FROM " + ORGUSER_TABLE + " WHERE org_name = '" + name + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }


    public boolean approveOrg(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORG_VERIFIED, 1);
        long result =db.update(ORGUSER_TABLE, cv, "org_name=?", new String[]{name});
        if (result == -1){
            return false;
        }
        else return true;
    }

    // add category to the table
    public boolean addCategory(Category c) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME, c.getCategory_name());
        cv.put(CATEGORY_DESC, c.getCategory_desc());
        cv.put(CATEGORY_IMAGE, c.getImage());
        long insert = database.insert(CATEGORY_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    // add category to the table
    public boolean addProfileP(Users addProfilePicture ) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USERNAME, addProfilePicture.getUsername());
        cv.put(USER_PROFILE_IMAGE, addProfilePicture.getImage());
        long insert = database.insert(USERS_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    // check for category name
    public int checkCategoryDuplicate(String c) {
        int categoryCount = 0;
        String sql = "SELECT COUNT(category_name) FROM Category WHERE category_name ='" + c + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            categoryCount = cursor.getInt(0);
        }
        cursor.close();
        return categoryCount;
    }




    // retrieves the type of category to be shown in the choosing goal page.
    public Cursor retrieveCategory() {
        String sql = "SELECT category_name, category_desc, category_image FROM Category";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    // retrieves user_id from users table for isnerting goals
    public int getUserID(String name) {
        int id = 0;
        String sql = "SELECT user_id FROM Users WHERE username ='" + name + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    public int getCategoryID(String categoryName) {
        int id = 0;
        String sql = "SELECT category_id FROM Category WHERE category_name ='" + categoryName + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    public boolean addUserGoal(UsersGoal goal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Singapore"));
        cv.put(GOALTYPE_ID, goal.getGoal_type_id());
        cv.put(USER_ID, goal.getUser_id());
        cv.put(GOAL_NAME, goal.getGoal_name());
        cv.put(GOAL_DESC, goal.getGoal_desc());
        cv.put(CREATED, sdf.format(new Date()));
        long insert = database.insert(USERGOAL_TABLE, null, cv);
        database.close();
        if (insert == -1) {
            return false;
        }
        else return true;
    }
    // retrieves category_id from category table for inserting goals

    public Cursor getUsersGoals() {
        String sql = "SELECT u.fullname, c.category_name, ug.goal_name, ug.accomplished FROM UserGoalTable ug " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "INNER JOIN Category c ON ug.goal_type_id = c.category_id;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public int CheckDuplicateGoal(String username, String input) {
        int goalCount = 0;
        String sql = "SELECT COUNT(ug.goal_name) FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE u.username = '" + username + "' AND ug.goal_name = '" + input + "';";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            goalCount = cursor.getInt(0);

        }
        return goalCount;
    }

    public Cursor trackingGoals(String username) {
        String sql = "SELECT ug.goal_name, ug.goal_desc FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE ug.accomplished = 0 AND u.username = '" + username + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    public int TotalGoals(String username) {
        int goalCount = 0;
        String sql = "SELECT COUNT(*) FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE u.username = '" + username + "';";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            goalCount = cursor.getInt(0);
        }
        cursor.close();
        return goalCount;

    }

    public Cursor getGoals(String username, String goalName) {
        String sql = "SELECT ug.goal_desc, ug.datetime_created FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE u.username ='" + username + "' AND ug.goal_name = '" + goalName + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    public boolean deleteGoal(int user_id, String goalName) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(USERGOAL_TABLE, "user_id ='" + user_id + "' AND goal_name = '" + goalName + "'", null) > 0;
    }

    public int updateGoal(String user_id, String goalName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACCOMPLISHED, 1);
        int update = database.update(USERGOAL_TABLE, cv, "user_id=? AND goal_name=?", new String[]{user_id, goalName});
        database.close();
        return update;
    }





}
