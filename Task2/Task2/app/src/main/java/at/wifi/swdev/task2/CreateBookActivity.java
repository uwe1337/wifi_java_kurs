package at.wifi.swdev.task2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import at.wifi.swdev.task2.databinding.ActivityCreateBookBinding;

public class CreateBookActivity extends AppCompatActivity {

    private ActivityCreateBookBinding binding;
    public static final String TITLE_EXTRA = "title";
    public static final String AUTHOR_EXTRA = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Buch hinzufügen");
        }
    }

    public void createBook(View view) {
        Log.i("createBook", "hinzufügen wurde geklickt...");
        EditText bookTitleEdit = binding.createBookTitleInput;
        String bookTitle = bookTitleEdit.getText().toString();

        EditText bookAuthorEdit = binding.createBookAuthorInput;
        String bookAuthor = bookAuthorEdit.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(TITLE_EXTRA, bookTitle);
        intent.putExtra(AUTHOR_EXTRA, bookAuthor);

        setResult(RESULT_OK, intent);
        finish();
    }
}