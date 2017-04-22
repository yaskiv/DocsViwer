package yaskiv.docsviwer.View.Impl.Activitys;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Date;

import yaskiv.docsviwer.Adapter.RecyclerViewAdapter;
import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Model.Impl.Documents;
import yaskiv.docsviwer.Presenter.Database.Impl.DataBaseHelper;
import yaskiv.docsviwer.Presenter.Impl.PresenterMain;
import yaskiv.docsviwer.Presenter.Impl.ReciverForPdf;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private PresenterMain pm;
    DataBaseHelper dbHelper;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((myReceiver),
                new IntentFilter("yaskiv.docsviwer.BroadcastReceiver"));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onStop();
    }
    ReciverForPdf myReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pm=new PresenterMain(this);
        dbHelper=new DataBaseHelper(this);
        db=openOrCreateDatabase(dbHelper.getDatabaseName(), Context.MODE_PRIVATE,
                null);
        dbHelper.onCreate(db);
        Document document=new Document(1,"name", java.sql.Date.valueOf("1001-12-12"),
                "",
                "",true);
        Log.d("dsa", String.valueOf(document.getSaveLocal()));
        dbHelper.addOrUpdateDocuments(document);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this,dbHelper.getAllDocuments().getListOfDocument());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myReceiver = new ReciverForPdf();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void ButtonClick(View view) {

    }

    @Override
    public void OnItemClick(View view) {

    }

}
