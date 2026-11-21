package com.example.recipebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipebook.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_RECIPE = "Recipe";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "TITLE";
    private static final String COL_STEPS = "STEPS";
    private static final String COL_KEYWORDS = "KEYWORDS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_RECIPE + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " VARCHAR(30), " +
                COL_STEPS + " VARCHAR(300), " +
                COL_KEYWORDS + " VARCHAR(30))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        onCreate(db);
    }

    public boolean insertRecipe(String title, String steps, String keywords) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_STEPS, steps);
        values.put(COL_KEYWORDS, keywords);
        long result = db.insert(TABLE_RECIPE, null, values);
        return result != -1;
    }

    public boolean checkTitleExists(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RECIPE + " WHERE " + COL_TITLE + " = ?", new String[]{title});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Cursor searchRecipe(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_RECIPE + " WHERE " + COL_TITLE + " LIKE ? OR " + COL_KEYWORDS + " LIKE ?";
        String[] args = new String[]{"%" + query + "%", "%" + query + "%"};
        return db.rawQuery(sql, args);
    }

    public boolean deleteRecipe(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_RECIPE, COL_TITLE + " = ?", new String[]{title});
        return deletedRows > 0;
    }

    public Cursor getAllRecipes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_RECIPE, null);
    }
}