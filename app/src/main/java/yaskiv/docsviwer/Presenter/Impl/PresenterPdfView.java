package yaskiv.docsviwer.Presenter.Impl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;

import yaskiv.docsviwer.Presenter.IPresenterView;
import yaskiv.docsviwer.View.IMainActivity;
import yaskiv.docsviwer.View.IPdfViewActivity;
import yaskiv.docsviwer.View.Impl.Services.FileDownladService;

/**
 * Created by yaski on 22.04.2017.
 */

public class PresenterPdfView implements IPresenterView {
    IPdfViewActivity view;

    public PresenterPdfView(IPdfViewActivity view) {
        this.view = view;
    }

    @Override
    public void OpenLocal(String Url) {
        File file = new File(Url);
     view.openPdf(file);

    }


    @Override
    public void OpenFromWeb(String Url,Bundle b) {


    }
}
