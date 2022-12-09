package id.ac.umn.uas_profile.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


    private FragmentHistoryBinding binding;
    private ArrayList<History> historyArrayList;
    private String[] name;
    private int[] imageView;
    private String[] jobDesc;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.rvHistory);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        HistAdapter histAdapter = new HistAdapter(getContext(), historyArrayList);
        recyclerview.setAdapter(histAdapter);
        histAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(){
        historyArrayList = new ArrayList<>();

        name = new String[]{
                getString(R.string.history1),
                getString(R.string.history1)
        };
        imageView = new int[]{
                R.drawable.pablo,
                R.drawable.pablo
        };

        jobDesc = new String[]{
                getString(R.string.history1),
                getString(R.string.history1)
        };

        for (int i=0; i< name.length; i++){
            History history = new History(name[i], jobDesc[i], imageView[i]);
            historyArrayList.add(history);
        }
    }
}