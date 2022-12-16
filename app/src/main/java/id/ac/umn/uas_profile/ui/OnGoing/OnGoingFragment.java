package id.ac.umn.uas_profile.ui.OnGoing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
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

import id.ac.umn.uas_profile.CategoryAdapter;
import id.ac.umn.uas_profile.CategoryModel;
import id.ac.umn.uas_profile.HWorkCategory;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentFavoritesBinding;
import id.ac.umn.uas_profile.ui.history.HistoryFragment;


public class OnGoingFragment extends Fragment {


    public static final String TAG = "";
    private FragmentFavoritesBinding binding;
    private ArrayList<Ongoing> ongoingArrayList;
    private OngoingAdapter ongoingAdapter;
    private RecyclerView recyclerview;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fetchData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OnGoingViewModel favoritesViewModel =
                new ViewModelProvider(this).get(OnGoingViewModel.class);

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

        recyclerview = view.findViewById(R.id.rvOngoing);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        ongoingAdapter = new OngoingAdapter(getContext(), ongoingArrayList);
        recyclerview.setAdapter(ongoingAdapter);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();



    }

    private void fetchData() {
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
                        if(dc.getDocument().toObject(Ongoing.class).status.equals("Ongoing")) {
                            ongoingArrayList.add(dc.getDocument().toObject(Ongoing.class));
                        }
                        else{
                            Log.e("Empty Data", "Order is empty");
                        }
                    }
                    ongoingAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}