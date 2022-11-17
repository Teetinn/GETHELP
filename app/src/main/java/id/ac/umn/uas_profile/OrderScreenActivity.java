package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderScreenActivity extends AppCompatActivity {

    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        btnPay = findViewById(R.id.payButton);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                last edit category activity to main activity
                Intent mainActivity = new Intent(OrderScreenActivity.this, PaymentSplashScreen.class);
                startActivity(mainActivity);
            }
        });
    }
}