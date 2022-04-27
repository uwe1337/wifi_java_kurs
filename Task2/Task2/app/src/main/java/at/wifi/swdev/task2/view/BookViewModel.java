package at.wifi.swdev.task2.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import at.wifi.swdev.task2.db.Book;
import at.wifi.swdev.task2.db.BookRepository;

public class BookViewModel extends AndroidViewModel {

    private BookRepository repository;
    private LiveData<List<Book>> books;

    public BookViewModel(@NonNull Application application) {
        super(application);

        repository = new BookRepository(application);
        books = repository.getAllBooks();
    }

    public void insert(Book book) {
        repository.insert(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public LiveData<List<Book>> getAllBooks() {
        return books;
    }

}
