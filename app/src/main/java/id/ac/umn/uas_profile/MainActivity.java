package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

import id.ac.umn.uas_profile.databinding.ActivityMainBinding;
import id.ac.umn.uas_profile.ui.history.HistoryFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button btnBill, btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnRate = findViewById(R.id.rateButton);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_history, R.id.navigation_favorites, R.id.navigation_chat)
//                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

//        Bundle bun=getIntent().getExtras();
//        int val=bun.getInt("VAL");
//        if(val==1)
//        {
//            btnRate.setVisibility(View.GONE);
//        }
//        replaceFragment(new HistoryFragment());
    }

//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
//    }


    public void buttonClick(View v) {
        switch (v.getId()) {
            case R.id.houseworkButton:
                Intent goToCategory = new Intent(this, HWorkCategory.class);
                startActivity(goToCategory);
                break;
            case R.id.childcareButton:
                Intent goToChildcare = new Intent(this, CCareCategory.class);
                startActivity(goToChildcare);
                break;
            case R.id.educationButton:
                Intent goToEducation = new Intent(this, EduCategory.class);
                startActivity(goToEducation);
                break;
            case R.id.othersButton:
                Intent goToOthers = new Intent(this, OthersCategory.class);
                startActivity(goToOthers);
                break;
            case R.id.profilePic:
                Intent goToProfile = new Intent(this, ProfileActivity.class);
                startActivity(goToProfile);
                break;
            case R.id.rateButton:
                Intent goToRating = new Intent(this, RateActivity.class);
                startActivity(goToRating);
                break;
//            case R.id.finishButton:
//                Intent mainActivity = new Intent(this, PaymentSplashScreen.class);
//                startActivity(mainActivity);
//                break;
//            case R.id.btnDownBill:
//
//                Intent goToRateSplash = new Intent(this, PaymentSplashScreen.class);
//                startActivity(goToRateSplash);
        }

    }

}