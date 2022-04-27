package at.wifi.swdev.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_AUTH = "userAuth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginAuthBTN = findViewById(R.id.loginAuthBTN);
        loginAuthBTN.setOnClickListener(view -> onLoginAuthBTNClick(view));
    }

    private void onLoginAuthBTNClick(View view) {
        EditText userNameEdit = findViewById(R.id.loginUsernameInput);
        String usernameInput = userNameEdit.getText().toString();

        EditText passwortEdit = findViewById(R.id.loginPasswortInput);
        String passwortInput = passwortEdit.getText().toString();

        Intent intent = new Intent();

        if(usernameInput.equals("user") && passwortInput.equals("password")) {
            intent.putExtra(USER_AUTH, true);
        } else {
            intent.putExtra(USER_AUTH, false);
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}