package at.wifi.swdev.task1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainShowLoginBTN = findViewById(R.id.mainShowLoginBTN);
        mainShowLoginBTN.setOnClickListener(view -> onMainShowLoginBTNClick(view));

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK) {
                TextView mainLoginMessage = findViewById(R.id.mainLoginMSG);
                Boolean userAuth = result.getData().getBooleanExtra(LoginActivity.USER_AUTH, false);

                if(userAuth) {
                    mainLoginMessage.setText("Login erfolgreich!");
                } else {
                    mainLoginMessage.setText("Login fehlgeschlagen!");
                }
            }
        });
    }

    private void onMainShowLoginBTNClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        launcher.launch(intent);
    }
}