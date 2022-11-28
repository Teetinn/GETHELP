package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button btnSignUp;
    private EditText etName, etEmail, etPNumber, etPassword, etConfirmPassword;
    String userID;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etNameSignUp);
        etEmail = findViewById(R.id.etEmailSignUp);
        etPNumber = findViewById(R.id.etPNumberSignUp);
        etPassword = findViewById(R.id.etPwSignUp);
        etConfirmPassword = findViewById(R.id.etConfirmPwSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrasi();
            }

            private void registrasi() {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pnumber = etPNumber.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    etName.setError("Name is Required.");
                    etName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email is Required.");
                    etEmail.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(pnumber)) {
                    etPNumber.setError("Phone Number is Required.");
                    etPNumber.requestFocus();
                    return;
                }

                if (pnumber.length() < 10) {
                    etPNumber.setError("Enter a Valid Phone Number");
                    etPNumber.requestFocus();
                    return;
                } else if (pnumber.length() > 12) {
                    etPNumber.setError("Enter a Valid Phone Number");
                    etPNumber.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Password is Required.");
                    etPassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError("Password Must be >= 6 Characters");
                    etPassword.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(confirmPassword)) {
                    etConfirmPassword.setError("ConfirmPassword is Required.");
                    etConfirmPassword.requestFocus();
                    return;
                }

                // Check if confirm password = password, then create user data

                if (password.equals(confirmPassword)) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "User Successfully Created", Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name", name);
                                user.put("Email", email);
                                user.put("Phone", pnumber);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "onSuccess: user profile is created for " + userID);
                                    }

                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                } else {
                    etConfirmPassword.setError("Password not matched");
                    etConfirmPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Register the user in firebase

//                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//
//                        } else {
//                            Toast.makeText(SignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                });
                //                Intent backToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                //                startActivity(backToLogin);
            }
        });
    }

    public void signIn(View v) {
        Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}