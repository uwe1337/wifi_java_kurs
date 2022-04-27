package at.wifi.swdev.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startWebSearch(View view) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);

        EditText searchEditText = findViewById(R.id.searchInput);
        String searchInput = searchEditText.getText().toString();
        intent.putExtra(SearchManager.QUERY, searchInput);
        Intent chooser = Intent.createChooser(intent, "Auswahl der APP:");

        try {
            startActivity(chooser);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, "Es wurde keine passende App für deine Suche gefunden...", Toast.LENGTH_SHORT).show();
        }
    }
}