package fr.enseeiht.tderocke.account_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static fr.enseeiht.tderocke.account_manager.Depense.ANNUEL;
import static fr.enseeiht.tderocke.account_manager.Depense.HEBDOMADAIRE;
import static fr.enseeiht.tderocke.account_manager.Depense.JOURNALIER;
import static fr.enseeiht.tderocke.account_manager.Depense.MENSUEL;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_ACTION_TYPE = "ACTION_TYPE";
    public static final String COLUMN_ACTION_NAME = "ACTION_NAME";
    public static final String COLUMN_ACTION_DATE = "ACTION_DATE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ACTION_FREQUENCY = "ACTION_FREQUENCY";
    public static final String COLUMN_ACTION_VALUE = "ACTION_VALUE";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "compte.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACTION_TYPE + " TEXT, " + COLUMN_ACTION_NAME + " TEXT, " + COLUMN_ACTION_DATE + " INTEGER, " + COLUMN_ACTION_FREQUENCY + " INT, " + COLUMN_ACTION_VALUE + " FLOAT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Depense depense) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTION_TYPE, depense.getType());
        cv.put(COLUMN_ACTION_NAME, depense.getNom());
        cv.put(COLUMN_ACTION_DATE, depense.getDate());
        cv.put(COLUMN_ACTION_FREQUENCY, depense.getFrequency());
        cv.put(COLUMN_ACTION_VALUE, depense.getValue());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(Depense depense) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + depense.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public List<Depense> getEveryone() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<Depense> getDaily() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = " + JOURNALIER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<Depense> getWeekly() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = " + HEBDOMADAIRE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<Depense> getMonthly() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = " + MENSUEL;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<Depense> getAnnually() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = " + ANNUEL;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public List<Depense> getFix() {
        List<Depense> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE (" + COLUMN_ACTION_FREQUENCY + " = " + ANNUEL + " OR " +
                COLUMN_ACTION_FREQUENCY + " = " + MENSUEL + " OR " + COLUMN_ACTION_FREQUENCY + " = " + HEBDOMADAIRE +
                " OR " + COLUMN_ACTION_FREQUENCY + " = " + JOURNALIER + ")";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                int depenseDate = cursor.getInt(3);
                int depenseFrequency = cursor.getInt(4);
                float depenseValue = cursor.getFloat(5);

                Depense newDepense = new Depense(depenseID, depenseType, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
