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

    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // create tables here
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creates the Users table
        String createUsersTable ="CREATE TABLE " + USERS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL, "
                + FULLNAME + " TEXT NOT NULL, " + DOB + " TEXT NOT NULL, " + USER_PHONE_NO + " INTEGER NOT NULL, " + USER_ROLE + " TEXT NOT NULL, " + USER_ADDRESS + " TEXT NOT NULL, " + USER_PROFILE_IMAGE + " BLOB);";

        db.execSQL(createUsersTable);

        String createOrgUsersTable ="CREATE TABLE " + ORGUSER_TABLE + " (" + ORG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORG_EMAILADDRESS + " TEXT NOT NULL, " + ORG_CONTACT_NO + " TEXT NOT NULL, " + ORG_CONTACT_NAME + " TEXT NOT NULL, "
                + ORG_ADDRESS + " TEXT NOT NULL, " + ORG_NAME + " TEXT NOT NULL, " + ORG_PASSWORD + " TEXT NOT NULL, " + ORG_VERIFIED + " INTEGER NOT NULL);";

        db.execSQL(createOrgUsersTable);

        String createCategoryTable = "CREATE TABLE " + CATEGORY_TABLE + " (" + CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " TEXT NOT NULL, " + CATEGORY_DESC + " TEXT NOT NULL, " + CATEGORY_IMAGE + " BLOB NOT NULL);";

        db.execSQL(createCategoryTable);


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

        // if it inserts successfully, insert == 1 if not insert == -1
        // success = positive
        // fail = negative
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
        if(db != null){
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

}
