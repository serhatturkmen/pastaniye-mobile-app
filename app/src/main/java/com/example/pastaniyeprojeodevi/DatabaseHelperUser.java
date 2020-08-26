package com.example.pastaniyeprojeodevi;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperUser extends SQLiteOpenHelper {
    public static final String DB_NAME = "pastaniye.db";
    static String DB_PATH;
    SQLiteDatabase myDatabase;
    final Context myContext;
    private static final String TAG = "DatabaseHelperUser";
    private static final String USER_TABLE_NAME = "User";
    private static final String COL1 = "ID";
    private static final String COL2 = "username";
    private static final String COL3 = "userpassword";
    private static final String COL4 = "telephone";
    private static final String COL5 = "adress";
    private static final String COL6 = "email";
    private static final String COL7 = "adsoyad";


    public DatabaseHelperUser(Context context) {
        super(context, DB_NAME , null, 1);
        DB_PATH = context.getFilesDir().getParent() + "/databases/";
        this.myContext = context;
    }


    public void CreateDataBase()
    {
        boolean dbExists = checkDataBase();
        if (!dbExists)
        {
            this.getReadableDatabase();
            try
            {
                copyDataBase();
            }
            catch (Exception ex)
            {
                Log.w("hata","Veritabanı kopyalanamıyor");
                throw new Error("Veritabanı kopyalanamıyor.");
            }
        }
    }
    //Sqlite veritabanı dosyasını açıp, veritabanımı kontrol ediyoruz
    boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;

        try
        {
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }
        catch (Exception ex)
        {
            Log.w("hata","Veritabanı açılamadı");
        }

        if (checkDB != null)
            checkDB.close();

        return checkDB != null ? true : false;
    }
    ///Assest dizinine koyduğumuz sql dosyasındaki verileri kopyalıyoruz
    void copyDataBase()
    {
        try
        {
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myInput.close();
            myOutput.close();
        }
        catch (Exception ex)
        {
            Log.w("hata", "Kopya oluşturma hatası.");
        }
    }
    @Override
    public synchronized void close()
    {
        if (myDatabase != null && myDatabase.isOpen())
            myDatabase.close();

        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateDataBase();
    }

    private void CreateUserTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " +
                COL3 +" TEXT," +
                COL4 +" TEXT," +
                COL5 +" TEXT," +
                COL6 +" TEXT," +
                COL7 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(String username, String userpassword, String telephone, String adress, String email, String adsoyad) {
        CreateUserTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, userpassword);
        contentValues.put(COL4, telephone);
        contentValues.put(COL5, adress);
        contentValues.put(COL6, email);
        contentValues.put(COL7, adsoyad);
        Log.d(TAG, "addData: Adding " + username + userpassword + telephone + adress + email + " to " + USER_TABLE_NAME);
        long result = db.insert(USER_TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getAdSoyad(int id){
        CreateUserTable();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE "+ COL1 +"="+ id +"";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        String adsoyad = data.getString(6);
        System.out.println(data.getString(6));
        return adsoyad;
    }

    public int getItemID(String username){
        CreateUserTable();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COL1 + " FROM " + USER_TABLE_NAME +
                " WHERE " + COL2 + " = '" + username + "'";
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        System.out.println(data.getInt(0));
        int id = data.getInt(0);
        return id;
    }

    public boolean logintrust(String username, String userpassword) {
        CreateUserTable();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME +
                " WHERE " + COL2 + " = '" + username + "' AND "+ COL3 +" = '"+ userpassword +"'";
        Cursor data = db.rawQuery(query, null);
        int rows = data.getCount();
        if(rows <= 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + USER_TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }
    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + USER_TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
}
