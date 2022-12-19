package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.text.DecimalFormat;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import id.ac.umn.uas_profile.ui.history.History;

public class RateActivity extends AppCompatActivity {
    float myRating = 0;
    RatingBar ratingBar;
    TextView RName, RDate, RBookid, RjobDesc, RMaidFare, RPlatFee, RTotalPrice, RAddress;
    History rating;
    ImageView RPic;
    Button btnBill, btnRate;
    String PlatFee;
    FirebaseAuth fAuth;
    String totalPrice;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        ratingBar = findViewById(R.id.rbMaid);
        RName = findViewById(R.id.tvName);
        RDate = findViewById(R.id.tvBookingDate);
        RBookid = findViewById(R.id.tvBookingID);
        RjobDesc = findViewById(R.id.tvJobDesc);
        RMaidFare = findViewById(R.id.tvMaidFare);
        RPlatFee = findViewById(R.id.tvPlatformFee);
        RTotalPrice = findViewById(R.id.tvTotal);
        RAddress = findViewById(R.id.tvUseraddress);

        RPic = findViewById(R.id.profilePic);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        Intent ratingIntent = getIntent();
        rating = (History) ratingIntent.getSerializableExtra("orderList");

        RName.setText(rating.getName());

        PlatFee = ("50000");
        RPlatFee.setText("Rp 50,000.0");

        RDate.setText(rating.getDate());
        RBookid.setText(rating.getDocId());
        RjobDesc.setText(rating.getJobDesc());
        RAddress.setText(rating.getUserAddress());
        RMaidFare.setText("Rp " + formatNumberCurrency(rating.getHFee()));
        RTotalPrice.setText("Rp " + formatNumberCurrency(rating.getFee()));
        Picasso.get().load(rating.getImage()).into(RPic);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myRating = ratingBar.getRating();

                Intent goToRateSplash = new Intent(RateActivity.this, RateSplashScreen.class);
                startActivity(goToRateSplash);

            }
        });
    }

    public void onClick(View v) {
        super.onBackPressed();
    }

    private static String formatNumberCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.0");
        return formatter.format(Double.parseDouble(number));
    }
}