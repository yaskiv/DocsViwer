package yaskiv.docsviwer.View;

import java.io.File;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IFileDownladService {
    File downladFile(String fileURL, String fileName);
    void showNotification(String message,String title);
}
