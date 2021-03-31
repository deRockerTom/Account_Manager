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
    public static final String FIX_TABLE = "FIX_TABLE";

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
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACTION_TYPE + " TEXT, " + COLUMN_ACTION_NAME + " TEXT, " + COLUMN_ACTION_DATE + " TEXT, " + COLUMN_ACTION_FREQUENCY + " TEXT, " + COLUMN_ACTION_VALUE + " DECIMAL(18,2))";
        String createTableStatement2 = "CREATE TABLE " + FIX_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ACTION_TYPE + " TEXT, " + COLUMN_ACTION_NAME + " TEXT, " + COLUMN_ACTION_DATE + " TEXT, " + COLUMN_ACTION_FREQUENCY + " TEXT, " + COLUMN_ACTION_VALUE + " DECIMAL(18,2))";

        db.execSQL(createTableStatement);
        db.execSQL(createTableStatement2);

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
    public boolean addFix(Depense depense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACTION_TYPE, depense.getType());
        cv.put(COLUMN_ACTION_NAME, depense.getNom());
        cv.put(COLUMN_ACTION_DATE, depense.getDate());
        cv.put(COLUMN_ACTION_FREQUENCY, depense.getFrequency());
        cv.put(COLUMN_ACTION_VALUE, depense.getValue());

        long insert = db.insert(FIX_TABLE, null, cv);
        return insert != -1;
    }

    public boolean deleteOne(Depense depense) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + depense.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public Depense getDepense(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        int depenseID = cursor.getInt(0);
        String depenseType = cursor.getString(1);
        String depenseName = cursor.getString(2);
        String depenseDate = cursor.getString(3);
        String depenseFrequency = cursor.getString(4);
        float depenseValue = cursor.getFloat(5);

        Depense depenseCherchee = new Depense(depenseID, depenseName, depenseType, depenseDate, depenseFrequency, depenseValue);
        cursor.close();
        db.close();

        return depenseCherchee;
    }

    public boolean deleteOne(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public boolean deleteOneFix(Depense depense) {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + FIX_TABLE + " WHERE " + COLUMN_ID + " = " + depense.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public Depense getDepenseFix(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        int depenseID = cursor.getInt(0);
        String depenseType = cursor.getString(1);
        String depenseName = cursor.getString(2);
        String depenseDate = cursor.getString(3);
        String depenseFrequency = cursor.getString(4);
        float depenseValue = cursor.getFloat(5);

        Depense depenseCherchee = new Depense(depenseID, depenseName, depenseType, depenseDate, depenseFrequency, depenseValue);
        cursor.close();
        db.close();

        return depenseCherchee;
    }

    public boolean deleteOneFix(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + FIX_TABLE + " WHERE " + COLUMN_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public ArrayList<DepenseAffichee> getEveryone() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DepenseAffichee> getDaily() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = \"Journalier\"" + "ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DepenseAffichee> getWeekly() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = \"Hebdomadaire\"" + "ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DepenseAffichee> getMonthly() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = \"Mensuel\"" + "ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DepenseAffichee> getAnnually() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE " + COLUMN_ACTION_FREQUENCY + " = \"Annuel\"" + "ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
                returnList.add(newDepense);

            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<DepenseAffichee> getFix() {
        ArrayList<DepenseAffichee> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FIX_TABLE + " WHERE (" + COLUMN_ACTION_FREQUENCY + " = \"Annuel\"" + " OR " +
                COLUMN_ACTION_FREQUENCY + " = \"Mensuel\"" + " OR " + COLUMN_ACTION_FREQUENCY + " = \"Hebdomadaire\"" +
                " OR " + COLUMN_ACTION_FREQUENCY + " = \"Journalier\"" + ")" + "ORDER BY ACTION_DATE desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects.
            do {
                int depenseID = cursor.getInt(0);
                String depenseType = cursor.getString(1);
                String depenseName = cursor.getString(2);
                String depenseDate = cursor.getString(3);
                String depenseFrequency = cursor.getString(4);
                float depenseValue = cursor.getFloat(5);

                DepenseAffichee newDepense = new DepenseAffichee(depenseID, depenseName, depenseDate, depenseFrequency, depenseValue);
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
