package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {
    float myRating = 0;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        ratingBar = findViewById(R.id.rbMaid);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myRating = ratingBar.getRating();
                Toast.makeText(RateActivity.this, "Thank you for rating us!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClick(View v) {
        super.onBackPressed();
    }
}