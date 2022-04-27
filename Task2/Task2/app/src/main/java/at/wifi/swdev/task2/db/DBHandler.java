package at.wifi.swdev.task2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1)
public abstract class DBHandler extends RoomDatabase {

    private static DBHandler INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DBHandler getDatabase(final Context context) {
        if(INSTANCE == null) {
            //Thread...
            synchronized (DBHandler.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DBHandler.class, "bookstore.sqlite").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BookDao getBookDao();

}
