package yaskiv.docsviwer.Model;

import yaskiv.docsviwer.Model.Entity.Document;

/**
 * Created by yaski on 22.04.2017.
 */

public interface IDocuments {
    Document getDocumentForName(String Name);
}
