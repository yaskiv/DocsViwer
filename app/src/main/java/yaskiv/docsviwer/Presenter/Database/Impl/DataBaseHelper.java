package yaskiv.docsviwer.Presenter.Database.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Model.Impl.Documents;
import yaskiv.docsviwer.Presenter.Database.IDataBaseHelper;

/**
 * Created by VB on 22.04.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper implements IDataBaseHelper {

    private static final String DATABASE_NAME = "DocsViewerDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DOCS = "Documents";

    private static final String KEY_DOCS_ID = "id";



    private static final String KEY_DOCS_NAME = "name";
    private static final String KEY_DOCS_DATE = "date";
    private static final String KEY_DOCS_LOCAL_URL = "localURL";
    private static final String KEY_DOCS_WEB_URL = "webURL";
    private static final String KEY_DOCS_SAVE = "isSave";

    private static DataBaseHelper sInstance;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DOCS +
                "(" +
                KEY_DOCS_ID + " INTEGER PRIMARY KEY," +
                KEY_DOCS_NAME + " VARCHAR," +
                KEY_DOCS_DATE + " DATE," +
                KEY_DOCS_LOCAL_URL + " VARCHAR," +
                KEY_DOCS_WEB_URL + " VARCHAR," +
                KEY_DOCS_SAVE + " BOOLEAN" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCS);
            onCreate(db);
        }
    }

    @Override
    public long addOrUpdateDocuments(Document document) {
        SQLiteDatabase db = getWritableDatabase();
        long documentsID = -1;
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(KEY_DOCS_NAME, document.getName());
            values.put(KEY_DOCS_DATE, String.valueOf(document.getDateOfDownloads()));
            values.put(KEY_DOCS_LOCAL_URL, document.getLocalUrl());
            values.put(KEY_DOCS_WEB_URL, document.getWebUrl());
            values.put(KEY_DOCS_SAVE, document.getSaveLocal());

            int rows = db.update(TABLE_DOCS, values, KEY_DOCS_NAME + "= ?", new String[]{document.getName()});

            if (rows == 1) {
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        KEY_DOCS_ID, TABLE_DOCS, KEY_DOCS_NAME);

                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(document.getName())});
                try {
                    if (cursor.moveToFirst()) {
                        documentsID = cursor.getInt(0);
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                Log.d("dsa", values.getAsString(KEY_DOCS_SAVE));
                documentsID = db.insertOrThrow(TABLE_DOCS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {

            Log.d("ExceptionDocs", "error");
        } finally {
            db.endTransaction();
        }
        return documentsID;

    }

    @Override
    public Documents getAllDocuments() {

        Documents documents = new Documents();

        String selectQuery = String.format("SELECT * FROM %s", TABLE_DOCS);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(selectQuery, null);
        String pattern = "YYYY:MM:DD";
        String date="14-03-2014 09:02:10";
        String reqdate=date.substring(0,10);
        List<Document> list=new ArrayList<>();
        //SimpleDateFormat format = new SimpleDateFormat(pattern);
        if(cur.moveToFirst()) {
            do {
                Log.d("dsa",cur.getString(0)+"|"+cur.getString(1)+"|"
                        + java.sql.Date.valueOf(cur.getString(2).replace(".","-")).toString()
                        +"|"+cur.getString(3)+"|"+ cur.getString(4)+"|"+(cur.getInt(5) == 1));

                list.add(new Document(
                        Integer.parseInt(cur.getString(0)),
                        cur.getString(1),
                        java.sql.Date.valueOf(cur.getString(2).replace(".","-")),
                        cur.getString(3),
                        cur.getString(4),
                        (cur.getInt(5) == 1)));
             } while (cur.moveToNext());
            documents.setListOfDocument(list);
            cur.close();
        }
        return documents;
    }

    @Override
    public  String getDatabaseName() {
        return DATABASE_NAME;
    }

    public  synchronized DataBaseHelper getInstance(Context context)
    {
        if(sInstance == null)
        {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }
}
