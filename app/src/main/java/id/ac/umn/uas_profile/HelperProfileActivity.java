package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HelperProfileActivity extends AppCompatActivity {
    private Button btnBook;
    private CategoryModel model;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Intent helperIntent = getIntent();
        model = (CategoryModel) helperIntent.getSerializableExtra("helper");

        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderScreenActivity = new Intent(HelperProfileActivity.this, OrderScreenActivity.class);
                startActivity(orderScreenActivity);
            }
        });
    }
}