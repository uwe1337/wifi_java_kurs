package at.wifi.swdev.task2.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "books")
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int _id;

    @NonNull
    public String title;
    public String author;

    public Book (String title, String author) {
        this.title = title;
        this.author = author;
    }
}
