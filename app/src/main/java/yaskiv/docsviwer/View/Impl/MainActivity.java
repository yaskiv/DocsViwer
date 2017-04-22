package yaskiv.docsviwer.View.Impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yaskiv.docsviwer.Presenter.Impl.PresenterMain;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity{

    private PresenterMain pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pm=new PresenterMain(this);
    }

    @Override
    public void ButtonClick(View view) {

    }
}
