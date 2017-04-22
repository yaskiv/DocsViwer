package yaskiv.docsviwer.Model.Impl;

import java.util.List;

import yaskiv.docsviwer.Model.Entity.Document;
import yaskiv.docsviwer.Model.IDocuments;

/**
 * Created by yaski on 22.04.2017.
 */

public class Documents implements IDocuments {
    private List<Document> listOfDocument;

    public List<Document> getListOfDocument() {
        return listOfDocument;
    }

    public void setListOfDocument(List<Document> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    @Override
    public Document getDocumentForName(String Name) {
        for (Document doc:listOfDocument) {
            if (doc.getName().equals(Name))
                return doc;

        }
        return null;
    }
}
