package yaskiv.docsviwer.Presenter.Database;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import yaskiv.docsviwer.Model.Entity.Document;

/**
 * Created by VB on 22.04.2017.
 */

public interface IDataBaseHelper {

    void onConfigure(SQLiteDatabase db);
    void onCreate(SQLiteDatabase db);
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    long addOrUpdateDocuments(Document document);
    List<Document> getAllDocuments();
}
