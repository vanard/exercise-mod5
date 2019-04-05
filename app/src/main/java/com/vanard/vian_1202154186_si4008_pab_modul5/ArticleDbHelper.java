package com.vanard.vian_1202154186_si4008_pab_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;

public class ArticleDbHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase db;

    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "articles.db";

    // Table Names
    private static final String TABLE_ARTICLE = "articles";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // NOTES Table - column names
    private static final String KEY_ARTICLE = "article";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";



    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_ARTICLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ARTICLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ARTICLE
            + " TEXT," + KEY_TITLE + " TEXT," + KEY_AUTHOR + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";

    public ArticleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_ARTICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLE);

        // create new tables
        onCreate(db);
    }

    public boolean insertData(Article article){
        ContentValues val = new ContentValues();
        val.put(KEY_AUTHOR, article.getPenulis());
        val.put(KEY_TITLE, article.getJudul());
        val.put(KEY_ARTICLE, article.getDeskripsi());
        val.put(KEY_CREATED_AT, article.getCreated_at());

        long res = db.insert(TABLE_ARTICLE, null, val);
        return res != -1;
    }

    public void readData(List<Article> dataArticle){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT author, title, article, created_at FROM "+TABLE_ARTICLE, null);
        while (cursor.moveToNext()){
            dataArticle.add(new Article(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
    }

    public boolean removeData(String title) {
        return db.delete(TABLE_ARTICLE, KEY_TITLE+"=\""+title+"\"", null)>0;
    }

}
