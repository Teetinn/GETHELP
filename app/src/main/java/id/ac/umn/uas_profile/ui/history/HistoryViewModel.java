package id.ac.umn.uas_profile.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.umn.uas_profile.CategoryAdapter;
import id.ac.umn.uas_profile.R;

public class HistoryViewModel extends ViewModel {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    ArrayList<HistoryModel> historyList = new ArrayList<HistoryModel>();

    private final MutableLiveData<String> mText;

    public HistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is history fragment");

//        categoryList.add(new CategoryModel(R.drawable.pablo, "Ashli", "Cleaning, Gardening, Cooking", "Serpong, 1.2 km", 4.5));
//        categoryList.add(new CategoryModel(R.drawable.pablo, "Hasli", "Babysitting, Petsitting", "BSD, 2.5 km", 3.8));
//        categoryList.add(new CategoryModel(R.drawable.pablo, "Lisha", "Gardening, Cooking", "Alam Sutera, 5.2 km", 4.8));
//        historyList.add(new CategoryModel(R.drawable.yanfei, "Ash Ketchup", "Cleaning, Gardening, Cooking"));

        mRecyclerView = (RecyclerView) mRecyclerView.findViewById(R.id.rvHistory);
        mAdapter = new HistoryAdapter(this, historyList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public LiveData<String> getText() {
        return mText;
    }
}