package yaskiv.docsviwer.Presenter.Impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by yaski on 22.04.2017.
 */

public class ReciverForPdf extends BroadcastReceiver {
    public ReciverForPdf() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();

    }
}
