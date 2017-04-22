package yaskiv.docsviwer.View.Impl.Activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yaskiv.docsviwer.Presenter.Impl.PresenterPdfView;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IPdfViewActivity;
import yaskiv.docsviwer.View.Impl.Services.FileDownladService;

public class PdfViewActivity extends AppCompatActivity implements IPdfViewActivity {
PDFView pdfView;
    PresenterPdfView presenterPdfView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
    pdfView=(PDFView)findViewById(R.id.pdfView);
        presenterPdfView=new PresenterPdfView(this);

       presenterPdfView.OpenLocal("/storage/emulated/0/Download/635183-1.pdf");
        Log.d("fsdf", "onCreate: ");
    }




    @Override
    public void openPdf(File file) {
        pdfView.fromAsset("CV.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }
}
