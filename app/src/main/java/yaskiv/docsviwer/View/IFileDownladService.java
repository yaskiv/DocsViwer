package yaskiv.docsviwer.View;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IFileDownladService {
    void downladFile(String fileURL,String fileName);
    void showNotification(String message,String title);
}
