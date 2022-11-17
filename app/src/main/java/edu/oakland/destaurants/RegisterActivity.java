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
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText strEmail, strPassword, strUsername;
    Button registerBtn;
    Intent confirmationActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        strEmail = findViewById(R.id.emailET);
        strPassword = findViewById(R.id.passwordET);
        strUsername = findViewById(R.id.usernameET);
        registerBtn = findViewById(R.id.registerBtn);

        List<AuthUserAttribute> authUserAttributes =new ArrayList<>();
        authUserAttributes.add(new AuthUserAttribute(AuthUserAttributeKey.email(), strEmail.getText().toString()));


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initiate user registration flow
                Amplify.Auth.signUp(
                        strUsername.getText().toString(),
                        strPassword.getText().toString(),
                        AuthSignUpOptions.builder().userAttributes(authUserAttributes).build(),
                        this::onRegisterSuccess,
                        this::onRegisterFail
                );
            }

            private void onRegisterFail(AuthException e) {
                RegisterActivity.this.runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                });

            }

            private void onRegisterSuccess(AuthSignUpResult authSignUpResult) {

                confirmationActivityIntent = new Intent(RegisterActivity.this, EmailConfirmationActivity.class);
                confirmationActivityIntent.putExtra("email", strEmail.getText().toString());
                confirmationActivityIntent.putExtra("password", strPassword.getText().toString());
                confirmationActivityIntent.putExtra("name", strUsername.getText().toString());
                startActivity(confirmationActivityIntent);
            }

        });

    }

}