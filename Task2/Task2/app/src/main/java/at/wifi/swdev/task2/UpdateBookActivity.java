package at.wifi.swdev.task2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import at.wifi.swdev.task2.databinding.ActivityUpdateBookBinding;
import at.wifi.swdev.task2.db.Book;

public class UpdateBookActivity extends AppCompatActivity {

    public static final String UPDATE_BOOK_EXTRA = "updateBook";
    private ActivityUpdateBookBinding binding;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra(UPDATE_BOOK_EXTRA);

        binding.editBookTitleInput.setText(book.title);
        binding.editBookAuthorInput.setText(book.author);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Buch bearbeiten");
        }
    }

    public void updateBook(View view) {

        EditText bookTitleEdit = binding.editBookTitleInput;
        String bookTitle = bookTitleEdit.getText().toString();
        book.title = bookTitle;

        EditText bookAuthorEdit = binding.editBookAuthorInput;
        String bookAuthor = bookAuthorEdit.getText().toString();
        book.author = bookAuthor;

        Intent intent = new Intent();
        intent.putExtra(UPDATE_BOOK_EXTRA, book);
        setResult(RESULT_OK, intent);
        finish();
    }
}