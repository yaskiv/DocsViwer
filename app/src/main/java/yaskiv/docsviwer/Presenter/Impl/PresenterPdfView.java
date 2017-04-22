package yaskiv.docsviwer.Presenter.Impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

import yaskiv.docsviwer.Presenter.IPresenterView;
import yaskiv.docsviwer.View.IMainActivity;

/**
 * Created by yaski on 22.04.2017.
 */

public class PresenterPdfView implements IPresenterView {
    IMainActivity view;

    public PresenterPdfView(IMainActivity view) {
        this.view = view;
    }

    @Override
    public void OpenLocal(String Url) {
        File file = new File(Url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //view.startLocalPdf(intent);

    }

    @Override
    public void OpenFromWeb(String Url,Bundle b) {

    }
}
