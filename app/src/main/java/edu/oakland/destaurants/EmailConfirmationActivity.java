package edu.oakland.destaurants;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
//import com.amplifyframework.datastore.generated.model.User;

public class EmailConfirmationActivity extends AppCompatActivity {

    Button confirmBtn;
    EditText confirmCode;
    Intent intent;
    Intent loginIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String username = intent.getStringExtra("name");
        confirmBtn = findViewById(R.id.confirm_account_button);
        confirmCode = findViewById(R.id.confirm_account_input);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send confirmation email of sign up to user
                Amplify.Auth.confirmSignUp(
                        username,
                        confirmCode.getText().toString(),
                        this::onSuccess,
                        this::onFail
                );
            }

            private void onFail(AuthException e) {
                EmailConfirmationActivity.this.runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                });
            }

            private void onSuccess(AuthSignUpResult authSignUpResult) {
                Log.i("AmplifyApp", authSignUpResult.isSignUpComplete() ? "SignUp Succeeded" : "Signup not completed");

              loginIntent = new Intent(EmailConfirmationActivity.this, LoginActivity.class);
              startActivity(loginIntent);
//                Amplify.Auth.signIn(
//                        email,
//                        password,
//                        this::onLoginSuccess,
//                        this::onFail
//                );

            }

//            private void onLoginSuccess(AuthSignInResult authSignInResult) {
//
//                Log.i("AuthQuickstart", authSignInResult.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
//                String userId = Amplify.Auth.getCurrentUser().getUserId();
//
//
//
//                Amplify.DataStore.save(
//                        User.builder().id(userId).name(name).build(),
//                        this::onSavedSuccess,
//                        this::onError
//                );
//
//            }
//
//            private void onError(DataStoreException e) {
//                EmailConfirmationA n n,  b v
//                ctivity.this.runOnUiThread(() -> {
//                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
//                            .show();
//                });
//            }
//
//            private <T extends Model> void onSavedSuccess(DataStoreItemChange<T> tDataStoreItemChange) {
//
//                Intent intent1 = new Intent(getApplicationContext(), AppActivity.class);
//                startActivity(intent1);
//
//            }
        });
    }
}