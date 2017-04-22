package yaskiv.docsviwer.View;

import android.net.Uri;

import java.io.File;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IPdfViewActivity {
    void openPdf(String Name);
    void openPdfFromWeb(Uri url);

    void openPdfFromFile(File file);
}
