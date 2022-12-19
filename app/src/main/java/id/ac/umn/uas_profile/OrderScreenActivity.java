package id.ac.umn.uas_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OrderScreenActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button btnPay;
    private CategoryModel helper ;
    TextView HName, HDesc, HFee, TPrice, PlatformFee;
    EditText etDays, etHours, etAddress;
    ImageView HImage;
    FirebaseAuth fAuth;
    String userId, TotalPrice, PlatFee, HPrice;
    ProgressBar progressBar;
    FirebaseUser user;
    FirebaseFirestore fStore;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);

        btnPay = findViewById(R.id.payButton);
        HName = findViewById(R.id.tvName);
        HDesc = findViewById(R.id.tvJobDesc);
        HFee = findViewById(R.id.tvPricePerHour);
        HImage = findViewById(R.id.profilePic);
        etDays = findViewById(R.id.etDays);
        etHours = findViewById(R.id.etHours);
        TPrice = findViewById(R.id.tvTotalPrice);
        progressBar = findViewById(R.id.progressBar);
        etAddress = findViewById(R.id.etAddress);
        PlatformFee = findViewById(R.id.tvPlatformFee);

        Calendar calendar = Calendar.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();

        PlatFee = ("50000");
        PlatformFee.setText("Rp 50,000");
        etDays.setText("0");
        etHours.setText("0");

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etDays.getText().toString().equals("") && !etHours.getText().toString().equals("")) {
                    double a,b,c,d,e,f;

                    if(etDays.getText().toString().length() == 0) {
                        etDays.setText("0");
                    }
                    if(etHours.getText().toString().length() == 0) {
                        etHours.setText("0");
                    }

                    a = Double.parseDouble(etDays.getText().toString());
                    b = Double.parseDouble(etHours.getText().toString());
                    c = Double.parseDouble(helper.getFee());
                    e = Double.parseDouble(PlatFee);
                    a = a * 24;
                    d = ((a+b) * c);
                    f = d + e;
                    TPrice.setText("Rp " + formatNumberCurrency(String.valueOf(f)));
                    HPrice = String.valueOf(d);
                    TotalPrice = String.valueOf(f);

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
        etDays.addTextChangedListener(textWatcher);
        etHours.addTextChangedListener(textWatcher);

        Intent helperIntent = getIntent();
        helper = (CategoryModel) helperIntent.getSerializableExtra("maidList");

        HName.setText(helper.getName());
        HDesc.setText(helper.getJobDesc());
        HFee.setText("Rp " + formatNumberCurrency(helper.getFee()));
        Picasso.get().load(helper.getImage()).into(HImage);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = helper.getName().trim();
                String fee = TotalPrice.trim();
                String HFee = HPrice.trim();
                String userAddress = etAddress.getText().toString().trim();
                String pnumber = helper.getPhone().trim();
                String jobDesc = helper.getJobDesc().trim();
                String profImage = helper.getImage().trim();
                String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

                DocumentReference documentReference = fStore.collection("users").document(userId).collection("orderList").document(helper.getDocId());
                Map<String, Object> order = new HashMap<>();
                order.put("name", name);
                order.put("fee", fee);
                order.put("Hfee", HFee);
                order.put("userAddress", userAddress);
                order.put("jobDesc", jobDesc);
                order.put("phone", pnumber);
                order.put("image", profImage);
                order.put("status", "Ongoing");
                order.put("date", currentDate);
                documentReference.set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressBar.setVisibility(View.VISIBLE);
                        Log.d(TAG, "Maid order Successfully " + helper.getDocId());
                    }

                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });

//                last edit category activity to main activity
                Intent mainActivity = new Intent(OrderScreenActivity.this, PaymentSplashScreen.class);
                startActivity(mainActivity);
            }
        });
    }

    private static String formatNumberCurrency(String number) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(Double.parseDouble(number));
    }

}