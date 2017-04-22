package yaskiv.docsviwer.View.Impl.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import yaskiv.docsviwer.R;
import yaskiv.docsviwer.View.IFileDownladService;
import yaskiv.docsviwer.View.Impl.Activitys.MainActivity;

public class FileDownladService extends Service  implements IFileDownladService {
    Context ct;

    public FileDownladService(Context ct) {
        this.ct = ct;
    }

    private final IBinder mBinder = new LocalBinder();
    public class LocalBinder extends Binder {
        FileDownladService getService() {
             return FileDownladService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        downladFile(intent.getStringExtra("fileUrl"),intent.getStringExtra("fileName"));
        return mBinder;
    }

    @Override
    public void showNotification(String message, String title) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");//Activity for open

        PendingIntent pendingIntent = PendingIntent.getActivity(ct, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(ct);

        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setNumber(100);
        builder.build();

        Notification myNotication = builder.getNotification();
        manager.notify(11, myNotication);

    }

    @Override
    public void downladFile(String fileURL, String fileName) {


        StatFs stat_fs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double avail_sd_space = (double) stat_fs.getAvailableBlocks() * (double) stat_fs.getBlockSize();
        double MB_Available = (avail_sd_space / 10485783);

        Log.d("MB", "" + MB_Available);
        try {
            File root = new File(Environment.getExternalStorageDirectory() + "/docsviwer");
            if (root.exists() && root.isDirectory()) {

            } else {
                root.mkdir();
            }
            Log.d("CURRENT PATH", root.getPath());
            URL u = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            int fileSize = c.getContentLength() / 1048576;
            Log.d("FILESIZE", "" + fileSize);
            if (MB_Available <= fileSize) {
                this.showNotification("No memmory", "You don t have memory");
                c.disconnect();
                return;
            }

            FileOutputStream f = new FileOutputStream(new File(root.getPath(), fileName));

            InputStream in = c.getInputStream();


            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();




        } catch (Exception e) {
            Log.d("Downloader", e.getMessage());


        }
    }
}