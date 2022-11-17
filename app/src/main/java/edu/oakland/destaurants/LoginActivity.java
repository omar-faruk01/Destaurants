package edu.oakland.destaurants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginBtn;
    TextView registerBtn;
    Intent appActivityIntent, registerActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initiate user login flow
                Amplify.Auth.signIn(
                        username.getText().toString(),
                        password.getText().toString(),
                        this::onLoginSuccess,
                        this::onLoginFail);

            }

            private void onLoginFail(AuthException e) {
                LoginActivity.this.runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                });
            }

            private void onLoginSuccess(AuthSignInResult authSignInResult) {
                Log.i("AmplifyApp", "Log in Success");

                Amplify.Auth.fetchAuthSession(onSuccess -> {
                    appActivityIntent = new Intent(getApplicationContext(), AppActivity.class);
                    startActivity(appActivityIntent);
                }, onError ->{
                    Log.i("AmplifyApp", "Error getting user session: " + onError.toString());
                });

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivityIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerActivityIntent);
            }
        });

    }
}