package id.ac.umn.uas_profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    ArrayList<CategoryModel> categoryList = new ArrayList<CategoryModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoryList.add(new CategoryModel(R.drawable.pablo, "Ashli", "Cleaning, Gardening, Cooking", "Serpong, 1.2 km", 4.5));
        categoryList.add(new CategoryModel(R.drawable.yanfei, "Hasli", "Babysitting, Petsitting", "BSD, 2.5 km", 3.8));
        categoryList.add(new CategoryModel(R.drawable.pablo, "Lisha", "Gardening, Cooking", "Alam Sutera, 5.2 km", 4.8));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new CategoryAdapter(this, categoryList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}