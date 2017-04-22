package yaskiv.docsviwer.Model.Entity;

import java.util.Date;

/**
 * Created by yaski on 22.04.2017.
 */

public class Document {
    private Integer ID;
    private String Name;
    private Date DateOfDownloads;
    private String LocalUrl;
    private String WebUrl;
    private Boolean SaveLocal;

    public Document(Integer ID, String name, Date dateOfDownloads, String localUrl, String webUrl, Boolean saveLocal) {

        this.ID = ID;
        Name = name;
        DateOfDownloads = dateOfDownloads;
        LocalUrl = localUrl;
        WebUrl = webUrl;
        SaveLocal = saveLocal;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateOfDownloads() {
        return DateOfDownloads;
    }

    public void setDateOfDownloads(Date dateOfDownloads) {
        DateOfDownloads = dateOfDownloads;
    }

    public String getLocalUrl() {
        return LocalUrl;
    }

    public void setLocalUrl(String localUrl) {
        LocalUrl = localUrl;
    }

    public String getWebUrl() {
        return WebUrl;
    }

    public void setWebUrl(String webUrl) {
        WebUrl = webUrl;
    }

    public Boolean getSaveLocal() {
        return SaveLocal;
    }

    public void setSaveLocal(Boolean saveLocal) {
        SaveLocal = saveLocal;
    }


}
