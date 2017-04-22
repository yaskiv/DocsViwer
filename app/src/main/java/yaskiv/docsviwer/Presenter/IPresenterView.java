package yaskiv.docsviwer.Presenter;

import android.os.Bundle;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IPresenterView {
    void OpenLocal(String Url);
    void OpenFromWeb(String Url,Bundle b);

}
