package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut(); // Logout
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}