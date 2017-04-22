package yaskiv.docsviwer.Presenter.Impl;

import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Presenter.IPresenterMain;
import yaskiv.docsviwer.View.IMainActivity;

/**
 * Created by yaski on 22.04.2017.
 */

public class PresenterMain implements IPresenterMain{
    final IMainActivity mainActivityView;



    public PresenterMain(IMainActivity mainActivityView) {
        this.mainActivityView = mainActivityView;

    }
}
