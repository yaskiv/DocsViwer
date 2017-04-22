package yaskiv.docsviwer.Presenter;

import android.net.Uri;
import android.os.Bundle;

import java.io.File;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IPresenterView {
    void OpenLocal(String Url);
    void OpenFromWeb(Uri Url);

    void OpenLocalFromFile(File file);
}
