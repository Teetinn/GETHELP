package id.ac.umn.uas_profile.ui.favorites;

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

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentFavoritesBinding;
import id.ac.umn.uas_profile.ui.favorites.OngoingAdapter;
import id.ac.umn.uas_profile.ui.favorites.Ongoing;


public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private ArrayList<Ongoing> ongoingArrayList;
    private String[] name;
    private int[] imageView;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
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

        recyclerview = view.findViewById(R.id.rvOngoing);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        OngoingAdapter ongoingAdapter = new OngoingAdapter(getContext(), ongoingArrayList);
        recyclerview.setAdapter(ongoingAdapter);
        ongoingAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(){
        ongoingArrayList = new ArrayList<>();

        name = new String[]{
                getString(R.string.ongoing1),
                getString(R.string.ongoing1)
        };
        imageView = new int[]{
                R.drawable.pablo,
                R.drawable.pablo
        };

        for (int i=0; i< name.length; i++){
            Ongoing ongoing = new Ongoing(name[i], imageView[i]);
            ongoingArrayList.add(ongoing);
        }
    }
}