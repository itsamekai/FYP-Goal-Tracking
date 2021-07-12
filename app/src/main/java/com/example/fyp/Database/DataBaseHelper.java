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
import com.example.fyp.ObjectClass.OrgServices;
import com.example.fyp.ObjectClass.OrgUsers;
import com.example.fyp.ObjectClass.Services;
import com.example.fyp.ObjectClass.UserAchievement;
import com.example.fyp.ObjectClass.UserHelp;
import com.example.fyp.ObjectClass.Users;
import com.example.fyp.ObjectClass.UsersGoal;
import com.example.fyp.ObjectClass.Achievements;

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
    public static final String REQUESTED = "requested";

    //Services
    public static final String SERVICES_TABLE = "Services";
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_NAME = "service_name";
    public static final String SERVICE_DESC = "service_desc";

    //Org Services
    public static final String ORGSERVICES_TABLE = "OrgServices";
    public static final String ORG_HAS_SERVICE_ID = "org_has_service_id";
    public static final String ORGID = "org_id";
    public static final String SERVICEID = "service_id";

    //UserHelp Table
    public static final String USERHELP_TABLE = "UserHelp";
    public static final String USERHELP_ID = "userhelp_id";
    public static final String HELPED = "helped";

    //Achievements Table
    public static final String ACHIEVEMENTS_TABLE = "AchievementsTable";
    public static final String ACHIEVEMENT_ID = "achievement_id";
    public static final String ACHIEVEMENT_NAME = "achievement_name";
    public static final String ACHIEVEMENT_DESC = "achievement_desc";
    public static final String ACHIEVEMENT_IMAGE = "achievement_img";
    public static final String ACHIEVEMENT_REQUIRED = "achievement_required";

    //UserAchievement Table
    public static final String USERACHIEVEMENT_TABLE = "UserAchievement";
    public static final String USER_ACHIEVED_ID = "user_achieved_id";
    public static final String DATETIME_ACHIEVED = "datetime_achieved";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // create tables here
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creates the Users table
        String createUsersTable = "CREATE TABLE " + USERS_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT NOT NULL, " + PASSWORD + " TEXT NOT NULL, "
                + FULLNAME + " TEXT NOT NULL, " + DOB + " TEXT NOT NULL, " + USER_PHONE_NO + " INTEGER NOT NULL, " + USER_ROLE + " TEXT NOT NULL, " + USER_ADDRESS + " TEXT NOT NULL, " + USER_ABOUT + " TEXT, " + USER_PROFILE_IMAGE + " BLOB);";

        String createOrgUsersTable = "CREATE TABLE " + ORGUSER_TABLE + " (" + ORG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORG_EMAILADDRESS + " TEXT NOT NULL, " + ORG_CONTACT_NO + " TEXT NOT NULL, " + ORG_CONTACT_NAME + " TEXT NOT NULL, "
                + ORG_ADDRESS + " TEXT NOT NULL, " + ORG_NAME + " TEXT NOT NULL, " + ORG_PASSWORD + " TEXT NOT NULL, " + ORG_VERIFIED + " INTEGER NOT NULL);";

        String createCategoryTable = "CREATE TABLE " + CATEGORY_TABLE + " (" + CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORY_NAME + " TEXT NOT NULL, " + CATEGORY_DESC + " TEXT NOT NULL, " + CATEGORY_IMAGE + " BLOB NOT NULL);";

        String createUsersGoalTable = "CREATE TABLE " + USERGOAL_TABLE + " (" + GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GOALTYPE_ID + " INTEGER NOT NULL, "
                + USER_ID + " INTEGER NOT NULL, " + GOAL_NAME + " TEXT NOT NULL, " + GOAL_DESC + " TEXT NOT NULL, " + CREATED + " TEXT NOT NULL, "
                + COMPLETED + " TEXT, " + ACCOMPLISHED + " INTEGER DEFAULT   0, " + REQUESTED + " INTEGER DEFAULT 0, FOREIGN KEY (" + GOALTYPE_ID + ") REFERENCES " + CATEGORY_TABLE + " (" + CAT_ID + "), FOREIGN KEY (" + USER_ID + ") REFERENCES " + USERS_TABLE + "(" + USER_ID + "));";

        String createServicesTable = "CREATE TABLE " + SERVICES_TABLE + " (" + SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SERVICE_NAME + " TEXT NOT NULL, " + SERVICE_DESC + " TEXT NOT NULL);";

        String createOrgServicesTable = "CREATE TABLE " + ORGSERVICES_TABLE + " (" + ORG_HAS_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORGID + " INTEGER NOT NULL, " + SERVICEID + " INTEGER NOT NULL);";

        String createUserHelpTable = "CREATE TABLE " + USERHELP_TABLE + " (" + USERHELP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SERVICE_ID + " INTEGER NOT NULL, " + ORG_ID + " INTEGER, "
                + USER_ID + " INTEGER NOT NULL, " + GOAL_ID + " INTEGER NOT NULL, " + HELPED + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY (" + SERVICE_ID + ") REFERENCES " + ORGSERVICES_TABLE + "(" + SERVICE_ID + "), " +
                "FOREIGN KEY (" + ORG_ID + ") REFERENCES " + ORGUSER_TABLE + "(" + ORG_ID + ")," +
                "FOREIGN KEY (" + USER_ID + ") REFERENCES " + USERS_TABLE + "(" + USER_ID + ")," +
                "FOREIGN KEY (" + GOAL_ID + ") REFERENCES " + USERGOAL_TABLE + "(" + GOAL_ID + "));";

        String createAchievementsTable = "CREATE TABLE " + ACHIEVEMENTS_TABLE + " (" + ACHIEVEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACHIEVEMENT_REQUIRED + " INTEGER NOT NULL, " + ACHIEVEMENT_DESC + " TEXT NOT NULL, " + ACHIEVEMENT_NAME + " TEXT NOT NULL, " + ACHIEVEMENT_IMAGE + " BLOB NOT NULL);";

        String createUserAchievementTable = "CREATE TABLE " + USERACHIEVEMENT_TABLE + " (" + USER_ACHIEVED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACHIEVEMENT_ID + " INTEGER NOT NULL, " + USER_ID + " INTEGER NOT NULL, " + DATETIME_ACHIEVED + " STRING NOT NULL, " +
                "FOREIGN KEY (" + ACHIEVEMENT_ID + ") REFERENCES " + ACHIEVEMENTS_TABLE + "(" + ACHIEVEMENT_ID + "), " +
                "FOREIGN KEY (" + USER_ID + ") REFERENCES " + USERS_TABLE + "(" + USER_ID + "));";

        db.execSQL(createUsersTable);
        db.execSQL(createOrgUsersTable);
        db.execSQL(createCategoryTable);
        db.execSQL(createUsersGoalTable);
        db.execSQL(createServicesTable);
        db.execSQL(createOrgServicesTable);
        db.execSQL(createUserHelpTable);
        db.execSQL(createAchievementsTable);
        db.execSQL(createUserAchievementTable);

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
        cv.put(FULLNAME, "admin");
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

    public boolean allowRegister(OrgUsers orgUsers) {
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
        ContentValues cv = new ContentValues();
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
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // retrieveAccomplishedGoals for CheckAccomplishedGoals.java
    public Cursor retrieveAccomplishedGoals(String u) {
        String sql = "SELECT ug.goal_name, ug.goal_desc, ug.datetime_completed " +
                "FROM UserGoalTable ug " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "WHERE ug.accomplished = 1 " +
                "AND u.username = '" + u + "';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // retrieveAchievements
    public Cursor retrieveAchievements(int u) {
        String sql = "SELECT a.achievement_name, a.achievement_desc, ua.datetime_achieved, a.achievement_img " +
                "FROM AchievementsTable a " +
                "INNER JOIN UserAchievement ua ON a.achievement_id = ua.achievement_id " +
                "WHERE ua.user_id = '" + u + "'";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // update senior address,phoneNo , about

    public int updateSenior(String username, String address, int phoneNumb, String about) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_PHONE_NO, phoneNumb);
        cv.put(USER_ADDRESS, address);
        cv.put(USER_ABOUT, about);
        int update = database.update(USERS_TABLE, cv, "username=?", new String[]{username});
        database.close();
        return update;

    }

    //update organisation email, contactNo, person-in-charge, address

    public int updateOrg(String email_address, int contactNo, String contactPerson, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(ORG_EMAILADDRESS, email_address);
        cv.put(ORG_CONTACT_NO, contactNo);
        cv.put(ORG_CONTACT_NAME, contactPerson);
        cv.put(ORG_ADDRESS, address);
        //cv.put(ORG_NAME, organisationName);
        int update = database.update(ORGUSER_TABLE, cv, "email_address=?", new String[]{email_address});
        database.close();
        return update;
    }

    //update organisation (admin) password, contactNo, person-in-charge, address
    public int updateOrgAdmin(String org_name, String password, int contactNo, String contactPerson, String address) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORG_PASSWORD, password);
        cv.put(ORG_CONTACT_NO, contactNo);
        cv.put(ORG_CONTACT_NAME, contactPerson);
        cv.put(ORG_ADDRESS, address);
        //cv.put(ORG_NAME, organisationName);
        int update = database.update(ORGUSER_TABLE, cv, "org_name=?", new String[]{org_name});
        database.close();
        return update;
    }

    // update category des
    public int updateCategory(String category_name1, String category_desc1, byte[] image2) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_DESC, category_desc1);
        cv.put(CATEGORY_IMAGE, image2);
        int update = database.update(CATEGORY_TABLE, cv, "category_name ='" + category_name1 + "'", null);
        database.close();
        return update;

    }


    // retrieves image.
    public Bitmap retrieveImage(String u) {
        String sql = "SELECT image FROM Users WHERE username='" + u + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
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

    //retrieve achievement icon
    public Bitmap retrieveAchievementImage(String u) {
        String sql = "SELECT achievement_img FROM AchievementsTable WHERE username='" + u + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.moveToFirst()) {
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
        String sql = "SELECT COUNT(username) FROM Users WHERE username = '" + u + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            usernameCount = cursor.getInt(0);
        }
        cursor.close();
        return usernameCount;
    }

    //checks for email dplicates in database
    public int checkEmailDuplicate(String e) {
        int emailCount = 0;
        String sql = "SELECT COUNT(email_address) FROM OrgUsers WHERE email_address ='" + e + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
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
        String sql = "SELECT COUNT() FROM Users WHERE username = '" + u + "' AND password = '" + p + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    // valid org login
    public int validateOrgLogin(String e, String p) {
        int rowCount = 0;
        String sql = "SELECT COUNT() FROM OrgUsers WHERE email_address ='" + e + "' AND password = '" + p + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    //validate if organisation's login = email
    public int checkOrgLogin(String email, String password) {
        int rowCount = 0;
        String sql = "SELECT COUNT() FROM OrgUsers WHERE email_address = '" + email + "' AND password = '" + password + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            rowCount = cursor.getInt(0);
        }
        cursor.close();
        return rowCount;
    }

    // checks for the type of user. user / admin / organisation
    public String checkAccountType(String u) {
        String userType = "";
        String sql = "SELECT UserRole from Users WHERE username = '" + u + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            userType = c.getString(0);
        }
        return userType;
    }

    public String getUserFullName(String u) {
        String userFullName = "";
        String sql = "SELECT fullname FROM Users WHERE username ='" + u + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            userFullName = c.getString(0);
        }
        return userFullName;
    }

    // obtain username (user)
    public String getUserName(String un) {
        String userName = "";
        String sql = "SELECT username FROM Users WHERE username ='" + un + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            userName = c.getString(0);
        }
        return userName;
    }

    //obtain DOB (user)
    public String getDateOfBirth(String dob) {
        String getDOB = "";
        String sql = "SELECT dob FROM Users WHERE username ='" + dob + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getDOB = c.getString(0);
        }
        return getDOB;
    }

    //obtain phone number (user)
    public String getPhoneNumber(String pn) {
        String getpn = "";
        String sql = "SELECT phoneno FROM Users WHERE username ='" + pn + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getpn = c.getString(0);
        }
        return getpn;
    }

    //obtain Address (user)
    public String getUserAddress(String a) {
        String getadd = "";
        String sql = "SELECT address FROM Users WHERE username ='" + a + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getadd = c.getString(0);
        }
        return getadd;
    }

    //obtain about (user)
    public String getUserAbout(String ub) {
        String getabout = "";
        String sql = "SELECT about FROM Users WHERE username ='" + ub + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getabout = c.getString(0);
        }
        return getabout;
    }


    // gets org name, org contact name, contact number from database
    public Cursor readOrgData() {
        String sql = "SELECT org_name, contact_name, ContactNo FROM " + ORGUSER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    public Cursor readUnapprovedOrgData() {
        String sql = "SELECT org_name FROM " + ORGUSER_TABLE + " WHERE verified = 0";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
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
        long result = db.update(ORGUSER_TABLE, cv, "org_name=?", new String[]{name});
        if (result == -1) {
            return false;
        } else return true;
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

    // add achievement to the table
    public boolean addAchievement(Achievements a) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACHIEVEMENT_REQUIRED, a.getRequired());
        cv.put(ACHIEVEMENT_NAME, a.getAchievement_name());
        cv.put(ACHIEVEMENT_DESC, a.getAchievement_desc());
        cv.put(ACHIEVEMENT_IMAGE, a.getAchievement_img());
        long insert = database.insert(ACHIEVEMENTS_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    // add category to the table
    public boolean addProfileP(Users addProfilePicture) {
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
        if (cursor.getCount() > 0) {
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
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    // retrieves user_id from users table for isnerting goals
    public int getUserID(String name) {
        int id = 0;
        String sql = "SELECT user_id FROM Users WHERE username ='" + name + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    public int getCategoryID(String categoryName) {
        int id = 0;
        String sql = "SELECT category_id FROM Category WHERE category_name ='" + categoryName + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
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
        } else return true;
    }
    // retrieves category_id from category table for inserting goals

    public Cursor getUsersGoals() {
        String sql = "SELECT u.fullname, c.category_name, ug.goal_name, ug.accomplished FROM UserGoalTable ug " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "INNER JOIN Category c ON ug.goal_type_id = c.category_id;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
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

    public Cursor HistoryGoals(String username) {
        String sql = "SELECT ug.goal_name, ug.goal_desc, ug.datetime_created FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE ug.accomplished = 1 AND u.username = '" + username + "';";
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
        if (cursor.getCount() > 0) {
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

    public Cursor getCompletedGoal(String username, String goalName) {
        String sql = "SELECT ug.goal_desc, ug.datetime_created, ug.datetime_completed FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE u.username ='" + username + "' AND ug.goal_name = '" + goalName + "';";
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Singapore"));
        ContentValues cv = new ContentValues();
        cv.put(ACCOMPLISHED, 1);
        cv.put(COMPLETED, sdf.format(new Date()));
        int update = database.update(USERGOAL_TABLE, cv, "user_id=? AND goal_name=?", new String[]{user_id, goalName});
        database.close();
        return update;
    }

    public Cursor getGoalsHelpPage(String username) {
        String sql = "SELECT ug.goal_name, ug.goal_desc, ug.datetime_created FROM UserGoalTable ug INNER JOIN Users u ON ug.user_id = u.user_id WHERE u.username ='" + username + "' AND ug.accomplished = '0' AND ug.requested = '0';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // This method gets the goals that the users requested help for but have not gotten help yet from the POs
    public Cursor getPendingGoals(String username) {
        String sql = "select DISTINCT ug.goal_name, ug.goal_desc, ug.datetime_created FROM UserGoalTable ug " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "INNER JOIN UserHelp uh ON u.user_id = uh.user_id " +
                "WHERE ug.user_id = (SELECT user_id FROM Users WHERE username = '" + username + "') " +
                "AND ug.goal_id IN (SELECT goal_id FROM UserHelp WHERE org_id IS NULL and helped = 0);";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // this methods gets the goals that the users have requested help for and have already gotten help from POs (PO has chose to help the user with the goal)
    public Cursor showExistingHelpGoals(String username) {
        String sql = "SELECT DISTINCT ug.goal_name, ug.goal_desc, ug.datetime_created, o.org_name, o.ContactNo  FROM UserGoalTable ug " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "INNER JOIN UserHelp uh ON u.user_id = uh.user_id " +
                "INNER JOIN OrgUsers o ON uh.org_id = o.org_id " +
                "WHERE ug.user_id = (SELECT user_id FROM Users WHERE username = '" + username + "') " +
                "AND ug.goal_id IN (SELECT goal_id FROM UserHelp WHERE org_id NOT NULL and helped = 1) " +
                "AND uh.org_id IN (SELECT o.org_id FROM OrgUsers o INNER JOIN UserHelp uh ON o.org_id = uh.org_id WHERE uh.helped = 1);";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // these id is used to get the goal
    public Cursor getRequestedGoalsID() {
        String sql = "SELECT service_id, user_id, goal_id FROM UserHelp WHERE helped = 0 AND org_id IS NULL AND service_id in (SELECT service_id FROM OrgServices where org_id = 1);";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // this method is to get all the goal information for the organisation to view
    // category_id and service_id has the same int and position
    public Cursor getGoalFromUser(int category_id, int user_id, int goal_id) {
        String sql = "SELECT c.category_name, u.fullname, u.phoneno, ug.goal_name, ug.goal_desc, ug.datetime_created FROM Category c \n" +
                "INNER JOIN UserGoalTable ug ON c.category_id = ug.goal_type_id " +
                "INNER JOIN Users u ON ug.user_id = u.user_id " +
                "WHERE c.category_id = " + category_id + " AND u.user_id = " + user_id + " AND ug.goal_id = " + goal_id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // this method is to get the goal_ids that the organisation is currently helping the user with.
    // use these IDs to retrieve the details of the goals and display it in a list
    public Cursor getGoalIDsForHelpedGoals(int orgid) {
        String sql = "SELECT goal_id, service_id, user_id FROM UserHelp WHERE helped = 1 AND org_id = " + orgid + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }


    public String getOrgName(String email) {
        String name = "";
        String sql = "SELECT email_address FROM OrgUsers WHERE email_address ='" + email + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            name = c.getString(0);
        }
        return name;
    }

    //orgnisation name

    public String getOrganisationName(String orgname) {
        String getorgname = "";
        String sql = "SELECT org_name FROM OrgUsers WHERE email_address ='" + orgname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getorgname = c.getString(0);
        }
        return getorgname;
    }

    //phone number of organisation
    public String getOrgPhoneNumber(String phoneno) {
        String getphoneno = "";
        String sql = "SELECT ContactNo FROM OrgUsers WHERE email_address ='" + phoneno + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getphoneno = c.getString(0);
        }
        return getphoneno;
    }

    //person in charge of organisation
    public String getOrgPIC(String pic) {
        String getpic = "";
        String sql = "SELECT contact_name FROM OrgUsers WHERE email_address ='" + pic + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getpic = c.getString(0);
        }
        return getpic;
    }

    //address of organisation
    public String getOrgAddress(String address) {
        String getaddress = "";
        String sql = "SELECT address FROM OrgUsers WHERE email_address ='" + address + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            getaddress = c.getString(0);
        }
        return getaddress;
    }


    public boolean addServices(Services s) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SERVICE_NAME, s.getService_name());
        cv.put(SERVICE_DESC, s.getService_desc());

        long insert = database.insert(SERVICES_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    public int updateService(String category_name, String category_desc) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SERVICE_NAME, category_name);
        cv.put(SERVICE_DESC, category_desc);
        int update = database.update(SERVICES_TABLE, cv, "service_name=?", new String[]{category_name});
        database.close();
        return update;
    }

    public boolean addUserHelp(UserHelp goal) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SERVICE_ID, goal.getService_id());
        cv.put(USER_ID, goal.getUser_id());
        cv.put(GOAL_ID, goal.getGoal_id());
        long insert = database.insert(USERHELP_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;

    }


    public int updateRequested(String user_id, String goalName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(REQUESTED, 1);
        int update = database.update(USERGOAL_TABLE, cv, "user_id=? AND goal_name=?", new String[]{user_id, goalName});
        database.close();
        return update;
    }

    // get goal id with user_id and goal name
    public int getGoalID(int user, String goalName) {
        int id = 0;
        String sql = "SELECT goal_id FROM UserGoalTable WHERE goal_name = '" + goalName + "' AND user_id = '" + user + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    // get org id with email
    public int getOrgID(String email) {
        int id = 0;
        String sql = "SELECT org_id FROM OrgUsers WHERE email_address ='" + email + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    public int getUserIDWithFullName(String fullName) {
        int id = 0;
        String sql = "SELECT user_id FROM Users WHERE fullname = '" + fullName + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    public int updateUserHelped(String user_id, String goal_id, String org_id) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(HELPED, 1);
        cv.put(ORG_ID, org_id);
        int update = database.update(USERHELP_TABLE, cv, "user_id=? AND goal_id=?", new String[]{user_id, goal_id});
        database.close();
        return update;
    }

    public String getCategoryName(String goalName) {
        String name = "";
        String sql = "SELECT c.category_name FROM Category c INNER JOIN UserGoalTable ug ON c.category_id = ug.goal_type_id WHERE c.category_id = ug.goal_type_id AND ug.goal_name ='" + goalName + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            name = c.getString(0);
        }
        return name;
    }

    // gets service id of the service name.
    public int getServiceID(String name) {
        int id = 0;
        String sql = "SELECT service_id FROM Services WHERE service_name = '" + name + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        return id;
    }

    // should be able to remove
    public int checkGoalsAccomplished(int u) {
        int accomplishedGoal = 0;
        String sql = "SELECT COUNT(*) FROM UserGoalTable WHERE user_id = '" + u + "' AND accomplished = 1";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            accomplishedGoal = c.getInt(0);
        }
        return accomplishedGoal;
    }

    // get the service name and description. displays when the org wants to create the services
    // where statement ensures that there is no duplicate (eg if the org is already helping in this service, then it will not be shown again)
    public Cursor getService(int org_id) {
        String sql = "SELECT service_name, service_desc FROM Services WHERE service_id NOT IN (select service_id from OrgServices WHERE org_id = " + org_id + ");";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // insert into orgservices table after selecitng services.
    public boolean addDedicatedServicesForOrg(OrgServices os) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ORGID, os.getOrg_id());
        cv.put(SERVICEID, os.getService_id());
        long insert = database.insert(ORGSERVICES_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;

    }

    // adds user achievement
    public boolean addUserAchievement(UserAchievement ua) {
        SQLiteDatabase database = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Singapore"));
        ContentValues cv = new ContentValues();
        cv.put(ACHIEVEMENT_ID, ua.getAchievement_id());
        cv.put(USER_ID, ua.getUser_id());
        cv.put(DATETIME_ACHIEVED, sdf.format(new Date()));

        long insert = database.insert(USERACHIEVEMENT_TABLE, null, cv);
        database.close();
        if (insert == -1) return false;
        else return true;
    }

    // get achievement id where the required is = the accomplished amt of goals
    // only 1 id can be get, so it assumes that your user accomplished goals starts from 0.
    // only 1 goal can be given out at a time
    public int getMatchingAchievement(int userid) {
        int achievementid = 0;
        String sql = "SELECT achievement_id FROM AchievementsTable WHERE achievement_required = (SELECT COUNT(accomplished) FROM UserGoalTable WHERE accomplished = 1 AND user_id = " + userid + ")";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            achievementid = c.getInt(0);
        }
        return achievementid;
    }

    public Cursor retrieveOrgDetails(int orgD) {
        String sql = "SELECT org_name, email_address, password, ContactNo, contact_name, address  FROM OrgUsers '" + orgD + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        return c;
    }

    // checks if organisation is verified.
    // returns true if verified.
    public boolean checkOrgVerified(String email) {
        String sql = "SELECT verified FROM OrgUsers WHERE email_address ='" + email + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int bool = c.getInt(0);
            if (bool == 1) return true;
        }
        return false;
    }

    // checks if the organisation currently has any services that they can help in.
    // returns false if there is, true if there isn't.
    public boolean checkServicesAvailableForOrg(int id) {
        String sql = "SELECT (SELECT COUNT(*) from OrgServices where org_id ='" + id + "'), (SELECT COUNT(*) from Services)";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        int first = 0;
        int second = 0;
        if (db != null) {
            c = db.rawQuery(sql, null);
        }
        if (c.getCount() != 0) {
            while (c.moveToNext()) {
                first = c.getInt(0);
                second = c.getInt(1);
            }
        }
        if (first == second) return false;
        return true;
    }

    // checks if there are any requested help from users.
    // returns true if there is.
    public boolean checkIfRequestedHelpExists() {
        String sql = "SELECT COUNT(*) FROM UserHelp WHERE helped = 0 AND org_id IS NULL";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // checks if the organisation is helping anyone.
    // returns true if there is.
    public boolean checkIfHelping(int id) {
        String sql = "SELECT COUNT(*) FROM UserHelp WHERE helped = 1 AND org_id = '" + id + "'";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // checks if there is any existing ongoing goals by the user.
    // returns true if there is.
    public boolean checkExistingOnGoingGoals(int id) {
        String sql = "SELECT COUNT(*) FROM UserGoalTable WHERE user_id = '" + id + "' AND accomplished = 0";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // checks if there is any completed goal by the user.
    // returns true if there is
    public boolean checkCompletedGoalCount(int id) {
        String sql = "SELECT COUNT(*) FROM UserGoalTable WHERE user_id ='" + id + "' AND accomplished = 1";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // this is for get help from organisations (get help page)
    // checks if the user has any goals that can they can request help from
    public boolean checkExistingOnGoingGoalsHelp(int id) {
        String sql = "SELECT COUNT(*) FROM UserGoalTable WHERE user_id = '" + id + "' AND accomplished = 0 AND requested = 0";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // this is for show pending help (get help page)
    // checks if the user has any help that are pending (they request help and the organisation -
    // still has not decided to help the user yet, so the goal is pending.
    // returns true if there is any pending goal.
    public boolean checkPendingGoalCount(int id) {
        String sql = "SELECT COUNT(*) FROM UserHelp WHERE user_id = '" + id + "' AND helped = 0";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }

    // this is for show existing help (get help page)
    // checks if the user has any goals that the organisation is helping with.
    // returns true if there are.
    public boolean checkExistingHelpGoalCount(int id) {
        String sql = "SELECT COUNT(*) FROM UserHelp WHERE user_id = '" + id + "' AND helped = 1 AND org_id NOT NULL";
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        if (c.moveToFirst()) {
            int count = c.getInt(0);
            if (count > 0) return true;
        }
        return false;
    }


}
















