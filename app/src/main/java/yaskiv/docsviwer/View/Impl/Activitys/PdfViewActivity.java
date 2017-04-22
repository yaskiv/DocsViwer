package yaskiv.docsviwer.View.Impl.Activitys;

import android.Manifest;
import org.apache.commons.io.FilenameUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.DocumentsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import yaskiv.docsviwer.Adapter.RecyclerViewAdapter;
import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Presenter.Database.Impl.DataBaseHelper;
import yaskiv.docsviwer.Presenter.Impl.PresenterPdfView;
import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IPdfViewActivity;
import yaskiv.docsviwer.View.Impl.Services.FileDownladService;

public class PdfViewActivity extends AppCompatActivity implements IPdfViewActivity {
PDFView pdfView;
    PresenterPdfView presenterPdfView;
    DataBaseHelper dbHelper;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
    pdfView=(PDFView)findViewById(R.id.pdfView);
        presenterPdfView=new PresenterPdfView(this);
        Intent intent=getIntent();

//        File file=new File(intent.getStringExtra("FileName"));
       // presenterPdfView.OpenLocalFromFile(file);
       //presenterPdfView.OpenLocal("CV.pdf");
        int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=5;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // Explain to the user why we need to read the contacts
                    }
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

                return;
            }
        }
        String withPDF =String.valueOf(intent.getData())
                .substring(String.valueOf(intent.getData())
                        .lastIndexOf('/')+1, String.valueOf(intent.getData()).length());

        Log.d("QWERAS", String.valueOf(intent.getData())
                .substring(String.valueOf(intent.getData())
                        .lastIndexOf('/')+1, String.valueOf(intent.getData()).length()));
        Log.d("ss11", withPDF.substring(0, withPDF.lastIndexOf('.')));
        String fileName =withPDF.substring(0, withPDF.lastIndexOf('.'));
        dbHelper=new DataBaseHelper(this);
        db=openOrCreateDatabase(dbHelper.getDatabaseName(), Context.MODE_PRIVATE,
                null);
        dbHelper.onCreate(db);
        Document document=new Document(2,fileName, java.sql.Date.valueOf("1001-12-12"),
                intent.getData().toString(),
                intent.getData().toString(),true);
        dbHelper.addOrUpdateDocuments(document);


        Log.d("sss", intent.getData().toString());
        presenterPdfView.OpenFromWeb(intent.getData());
        Log.d("fsdf", "onCreate: ");
    }




    @Override
    public void openPdf(String name) {
        pdfView.fromAsset(name)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }



    @Override
    public void openPdfFromWeb(Uri url) {
        pdfView.fromUri(url)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }

    @Override
    public void openPdfFromFile(File file) {
        pdfView.fromFile(file)
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
