package at.wifi.swdev.kostenrechner.data;

import java.io.Serializable;

public class Author implements Serializable {
    final static String author = "Dominik Beyer";
    
    public String getAuthor() {
        return author;
    }
}
