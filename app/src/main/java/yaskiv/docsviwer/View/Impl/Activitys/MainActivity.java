package yaskiv.docsviwer.View.Impl.Activitys;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yaskiv.docsviwer.Adapter.RecyclerViewAdapter;
import yaskiv.docsviwer.Model.Impl.Documents;
import yaskiv.docsviwer.Presenter.Database.Impl.DataBaseHelper;
import yaskiv.docsviwer.Presenter.Impl.PresenterMain;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private PresenterMain pm;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    DataBaseHelper dbHelper ;
    SQLiteDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pm=new PresenterMain(this);
        dbHelper=new DataBaseHelper(this);
        db=openOrCreateDatabase(dbHelper.getDatabaseName(), Context.MODE_PRIVATE,
                null);
        dbHelper.onCreate(db);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this,dbHelper.getAllDocuments().getListOfDocument());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }


    @Override
    public void ButtonClick(View view) {

    }

    @Override
    public void OnItemClick(View view) {

    }
}
