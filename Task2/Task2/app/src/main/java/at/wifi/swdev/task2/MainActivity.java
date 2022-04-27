package at.wifi.swdev.task2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import at.wifi.swdev.task2.databinding.ActivityMainBinding;
import at.wifi.swdev.task2.db.Book;
import at.wifi.swdev.task2.view.BookListAdapter;
import at.wifi.swdev.task2.view.BookViewModel;
import at.wifi.swdev.task2.view.BookViewModelFactory;
import at.wifi.swdev.task2.view.SwipeToDeleteCallback;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private BookViewModel viewModel;
    private ActivityResultLauncher<Intent> createLauncher;
    private ActivityResultLauncher<Intent> editLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recycleView;

        viewModel = new ViewModelProvider(this, new BookViewModelFactory(getApplication())).get(BookViewModel.class);

        BookListAdapter adapter = new BookListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllBooks().observe(this, books -> adapter.setBooks(books));

        recyclerView.setAdapter(adapter);

        createLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK) {
                String title = result.getData().getStringExtra(CreateBookActivity.TITLE_EXTRA);
                String author = result.getData().getStringExtra(CreateBookActivity.AUTHOR_EXTRA);

                Book newBook = new Book(title, author);
                viewModel.insert(newBook);
                Snackbar.make(binding.getRoot(), "Buch erstellt", Snackbar.LENGTH_SHORT).show();
            }
        });
        editLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK) {
                if(result.getResultCode() == RESULT_OK) {
                    Book book = (Book) result.getData().getSerializableExtra(UpdateBookActivity.UPDATE_BOOK_EXTRA);
                    viewModel.update(book);
                    Snackbar.make(binding.getRoot(), "Buch wurde aktualisiert", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter, viewModel));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.getClickedBook().subscribe(new Observer<Book>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Book book) {
                Intent intent = new Intent(MainActivity.this, UpdateBookActivity.class);
                intent.putExtra(UpdateBookActivity.UPDATE_BOOK_EXTRA, book);
                editLauncher.launch(intent);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void openCreateBook(View view) {
        Intent intent = new Intent(this, CreateBookActivity.class);
        createLauncher.launch(intent);
    }

}