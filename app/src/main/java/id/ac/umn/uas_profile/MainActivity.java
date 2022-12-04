package id.ac.umn.uas_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.umn.uas_profile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        ImageButton houseworkBtn = (ImageButton) view.findViewById(R.id.houseworkButton);

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
    }

    public void buttonClick(View v) {
        switch (v.getId()) {
            case R.id.houseworkButton:
                Intent goToCategory = new Intent(this, CategoryActivity.class);
                startActivity(goToCategory);
                break;
//           case R.id.profilePic:
//                Intent goToEdit = new Intent(this, EditProfileActivity.class);
//                startActivity(goToEdit);
//                break;
            case R.id.profilePic:
                Intent goToProfile = new Intent(this, ProfileActivity.class);
                startActivity(goToProfile);
                break;
        }
    }


}