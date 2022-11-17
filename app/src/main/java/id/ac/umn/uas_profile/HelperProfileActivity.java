package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelperProfileActivity extends AppCompatActivity {
    private Button btnBook;
    private CategoryModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

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