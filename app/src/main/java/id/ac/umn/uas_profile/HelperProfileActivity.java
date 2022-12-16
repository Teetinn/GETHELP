package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class HelperProfileActivity extends AppCompatActivity {
    private Button btnBook;
    private CategoryModel helper ;
    TextView HName, HAge, HDesc, HExpTime1, HExpTime2, HExpTime3, HExp1, HExp2, HExp3, HFee, HRating;
    ImageView HImage;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile);

        HName = findViewById(R.id.tvHelperName);
        HAge = findViewById(R.id.tvHelperAge);
        HDesc = findViewById(R.id.tvHelperExpertise);
        HExpTime1 = findViewById(R.id.tvYear1);
        HExpTime2 = findViewById(R.id.tvYear2);
        HExpTime3 = findViewById(R.id.tvYear3);
        HExp1 = findViewById(R.id.tvHelperJob1);
        HExp2 = findViewById(R.id.tvHelperJob2);
        HExp3 = findViewById(R.id.tvHelperJob3);
        HFee = findViewById(R.id.tvHelperFee);
        HRating = findViewById(R.id.tvRatings);
        HImage = findViewById(R.id.profilePic);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Intent helperIntent = getIntent();
        helper = (CategoryModel) helperIntent.getSerializableExtra("maidList");

        HName.setText(helper.getName());
        HAge.setText(helper.getAge());
        HDesc.setText(helper.getJobDesc());
        HRating.setText(helper.getRating());
        HExpTime1.setText(helper.getExptime1());
        HExpTime2.setText(helper.getExptime2());
        HExpTime3.setText(helper.getExptime3());
        HExp1.setText(helper.getExp1());
        HExp2.setText(helper.getExp2());
        HExp3.setText(helper.getExp3());
        HFee.setText("Rp " + formatNumberCurrency(helper.getFee()) + " / Hour");
        Picasso.get().load(helper.getImage()).into(HImage);

        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderScreenActivity = new Intent(HelperProfileActivity.this, OrderScreenActivity.class);
                orderScreenActivity.putExtra("maidList", helper);
                startActivity(orderScreenActivity);
            }
        });
    }

    private static String formatNumberCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(number));
    }
}