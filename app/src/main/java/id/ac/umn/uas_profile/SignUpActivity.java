package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText etName, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etNameSignUp);
        etEmail = findViewById(R.id.etEmailSignUp);
        etPassword = findViewById(R.id.etPwSignUp);
        etConfirmPassword = findViewById(R.id.etConfirmPwSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(backToLogin);
            }
        });
    }

    public void signIn(View v) {
        Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}