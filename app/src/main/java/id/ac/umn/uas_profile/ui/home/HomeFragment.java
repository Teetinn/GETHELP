package id.ac.umn.uas_profile.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentHomeBinding;
import id.ac.umn.uas_profile.ui.OnGoing.Ongoing;
import id.ac.umn.uas_profile.ui.OnGoing.OngoingAdapter;
import id.ac.umn.uas_profile.ui.history.HistAdapter;
import id.ac.umn.uas_profile.ui.history.History;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static final String TAG = "";
    ImageButton houseworkBtn;
    ImageView profileImage;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    StorageReference storageReference;
    TextView emptyView, emptyView2;

    private ArrayList<History> historyArrayList;
    private OngoingAdapter ongoingAdapter;
    private HistAdapter histAdapter;
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
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        ProfilePictShow();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        fetchDataHistory();

        recyclerview = view.findViewById(R.id.rvHistory);
        emptyView = (TextView) view.findViewById(R.id.empty_view);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        histAdapter = new HistAdapter(getContext(), historyArrayList);
        recyclerview.setAdapter(histAdapter);
        histAdapter.notifyDataSetChanged();

        fetchDataonGoing();

        recyclerview2 = view.findViewById(R.id.rvOngoing);
        emptyView = (TextView) view.findViewById(R.id.empty_view);
        emptyView2 = (TextView) view.findViewById(R.id.empty_view2);

        recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview2.setHasFixedSize(true);
        ongoingAdapter = new OngoingAdapter(getContext(), ongoingArrayList);
        recyclerview2.setAdapter(ongoingAdapter);
        ongoingAdapter.notifyDataSetChanged();

    }


    private void fetchDataHistory() {
        userId = fAuth.getCurrentUser().getUid();
        Log.d(TAG, "fetchData: " + userId);
        historyArrayList = new ArrayList<History>();

        fStore.collection("users").document(userId).collection("orderList").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        if(dc.getDocument().toObject(History.class).getStatus().equals("Finish")) {
                            historyArrayList.add(dc.getDocument().toObject(History.class));
                        }
                        else{
                            Log.e("Empty Data", "Order is empty");
                            return;
                        }
                    }
                    ongoingAdapter.notifyDataSetChanged();


                }
                recyclerview.setVisibility(historyArrayList.isEmpty() ? View.GONE : View.VISIBLE);
                emptyView2.setVisibility(historyArrayList.isEmpty() ? View.VISIBLE : View.GONE);
            }


        });
    }

    private void fetchDataonGoing() {
        userId = fAuth.getCurrentUser().getUid();
        Log.d(TAG, "fetchData: " + userId);
        ongoingArrayList = new ArrayList<Ongoing>();

        fStore.collection("users").document(userId).collection("orderList").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        if(dc.getDocument().toObject(Ongoing.class).getStatus().equals("Ongoing")) {
                            ongoingArrayList.add(dc.getDocument().toObject(Ongoing.class));
                        }
                        else{
                            Log.e("Empty Data", "Order is empty");
                        }
                    }
                    ongoingAdapter.notifyDataSetChanged();


//                    if(ongoingArrayList.size() > 1) {
//                        ViewGroup.LayoutParams params = recyclerview2.getLayoutParams();
//                        params.height = 600;
//                        recyclerview2.setLayoutParams(params);
//                    }
                }

                recyclerview2.setVisibility(ongoingArrayList.isEmpty() ? View.GONE : View.VISIBLE);
                emptyView.setVisibility(ongoingArrayList.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
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