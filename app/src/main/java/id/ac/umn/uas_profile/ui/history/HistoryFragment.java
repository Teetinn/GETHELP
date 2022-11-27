package id.ac.umn.uas_profile.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import id.ac.umn.uas_profile.CategoryAdapter;
import id.ac.umn.uas_profile.CategoryModel;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {
//    private RecyclerView mRecyclerView;
//    private CategoryAdapter mAdapter;
//    ArrayList<HistoryModel> historyList = new ArrayList<HistoryModel>();

    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        categoryList.add(new CategoryModel(R.drawable.pablo, "Ashli", "Cleaning, Gardening, Cooking", "Serpong, 1.2 km", 4.5));
//        categoryList.add(new CategoryModel(R.drawable.pablo, "Hasli", "Babysitting, Petsitting", "BSD, 2.5 km", 3.8));
//        categoryList.add(new CategoryModel(R.drawable.pablo, "Lisha", "Gardening, Cooking", "Alam Sutera, 5.2 km", 4.8));
//        historyList.add(new CategoryModel(R.drawable.yanfei, "Ash Ketchup", "Cleaning, Gardening, Cooking"));

//        mRecyclerView = (RecyclerView) mRecyclerView.findViewById(R.id.rvHistory);
//        mAdapter = new HistoryAdapter(this, historyList);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        final TextView textView = binding.textHistory;
//        historyViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}