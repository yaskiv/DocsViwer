package yaskiv.docsviwer.View.Impl.Activitys;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yaskiv.docsviwer.Presenter.Impl.PresenterMain;
import yaskiv.docsviwer.Presenter.Impl.ReciverForPdf;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private PresenterMain pm;

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

        myReceiver = new ReciverForPdf();


    }
}
