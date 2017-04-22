package yaskiv.docsviwer.Presenter.Impl;

import android.os.Bundle;

import yaskiv.docsviwer.Presenter.IPresenterView;
import yaskiv.docsviwer.View.IMainActivity;

/**
 * Created by yaski on 22.04.2017.
 */

public class PresenterDocsView implements IPresenterView {
    IMainActivity view;

    public PresenterDocsView(IMainActivity view) {
        this.view = view;
    }

    @Override
    public void OpenLocal(String Url) {

    }

    @Override
    public void OpenFromWeb(String Url, Bundle b) {

    }
}
