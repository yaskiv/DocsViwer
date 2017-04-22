package yaskiv.docsviwer.Presenter.Database;

import android.database.sqlite.SQLiteDatabase;

import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Model.Impl.Documents;

/**
 * Created by VB on 22.04.2017.
 */

public interface IDataBaseHelper {

    void onConfigure(SQLiteDatabase db);
    void onCreate(SQLiteDatabase db);
    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    long addOrUpdateDocuments(Document document);
    Documents getAllDocuments();
}
