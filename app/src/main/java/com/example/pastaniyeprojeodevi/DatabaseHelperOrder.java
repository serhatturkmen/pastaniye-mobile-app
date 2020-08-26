package com.example.pastaniyeprojeodevi;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperOrder extends SQLiteOpenHelper {
    public static final String DB_NAME = "pastaniye.db";
    static String DB_PATH;
    SQLiteDatabase myDatabase;
    final Context myContext;
    private static final String TAG = "DatabaseHelperOrder";
    private static final String ORDER_TABLE_NAME = "Siparis";
    private static final String COL1 = "ID";
    private static final String COL2 = "aciklama";
    private static final String COL3 = "odemeyontemi";
    private static final String COL4 = "siparistutari";
    private static final String COL5 = "kullaniciadi";

    public DatabaseHelperOrder(Context context) {
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

    private void CreateOrderTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE_NAME + "("+
                COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, " +
                COL3 +" TEXT," +
                COL4 +" TEXT," +
                COL5 +" TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOrder(String aciklama, String odemeyontemi, String siparistutari, String kullaniciadi) {
        CreateOrderTable();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, aciklama);
        contentValues.put(COL3, odemeyontemi);
        contentValues.put(COL4, siparistutari);
        contentValues.put(COL5, kullaniciadi);
        Log.d(TAG, "addData: Adding " + aciklama + odemeyontemi + siparistutari + kullaniciadi + " to " + ORDER_TABLE_NAME);
        long result = db.insert(ORDER_TABLE_NAME, null, contentValues);
        return result != -1;
    }
}