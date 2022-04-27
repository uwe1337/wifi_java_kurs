package at.wifi.swdev.task2.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Objects;

public class BookRepository {

    private final DBHandler db;
    private final BookDao bookDao;
    private LiveData<List<Book>> allBooks;

    public BookRepository(Application application) {
        db = DBHandler.getDatabase(application);
        bookDao = db.getBookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insert(Book book) {
        if(!Objects.isNull(book)) {
            DBHandler.dbWriteExecutor.execute(() -> bookDao.insert(book));
        }
    }

    public void update(Book book) {
        if(!Objects.isNull(book)) {
            DBHandler.dbWriteExecutor.execute(() -> bookDao.update(book));
        }
    }

    public void delete(Book book) {
        if(!Objects.isNull(book)) {
            DBHandler.dbWriteExecutor.execute(() -> bookDao.delete(book));
        }
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

}
