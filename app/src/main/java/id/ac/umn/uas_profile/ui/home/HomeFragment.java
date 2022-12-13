package id.ac.umn.uas_profile.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentHomeBinding;
import id.ac.umn.uas_profile.ui.favorites.Ongoing;
import id.ac.umn.uas_profile.ui.favorites.OngoingAdapter;
import id.ac.umn.uas_profile.ui.history.HistAdapter;
import id.ac.umn.uas_profile.ui.history.History;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ImageButton houseworkBtn;
    ImageView profileImage;
    FirebaseAuth fAuth;
    StorageReference storageReference;

    private ArrayList<History> historyArrayList;
    private ArrayList<Ongoing> ongoingArrayList;
    private String[] name;
    private int[] imageView;
    private String[] jobDesc;
    private RecyclerView recyclerview, recyclerview2;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        profileImage = root.findViewById(R.id.profilePic);

        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        ProfilePictShow();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;


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

        dataInitialize2();

        recyclerview2 = view.findViewById(R.id.rvOngoing);
        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setHasFixedSize(true);
        OngoingAdapter ongoingAdapter = new OngoingAdapter(getContext(), ongoingArrayList);
        recyclerview2.setAdapter(ongoingAdapter);
        ongoingAdapter.notifyDataSetChanged();
    }

    private void dataInitialize2(){
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
    public void ProfilePictShow() {
        StorageReference profileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}