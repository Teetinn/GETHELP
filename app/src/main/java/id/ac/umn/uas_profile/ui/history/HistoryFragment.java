package id.ac.umn.uas_profile.ui.history;

import android.os.Bundle;
import android.util.Log;
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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import id.ac.umn.uas_profile.CategoryAdapter;
import id.ac.umn.uas_profile.CategoryModel;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentHistoryBinding;
import id.ac.umn.uas_profile.ui.OnGoing.Ongoing;
import id.ac.umn.uas_profile.ui.OnGoing.OngoingAdapter;

public class HistoryFragment extends Fragment {

    public static final String TAG = "";
    private FragmentHistoryBinding binding;
    private ArrayList<History> historyArrayList;
    private HistAdapter histAdapter;
    private RecyclerView recyclerview;
    private TextView emptyView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        fetchData();
    }

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

        recyclerview = view.findViewById(R.id.rvHistory);
        emptyView = view.findViewById(R.id.empty_view);

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        histAdapter = new HistAdapter(getContext(), historyArrayList);
        recyclerview.setAdapter(histAdapter);
        histAdapter.notifyDataSetChanged();
    }

    private void fetchData() {
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
                        if(dc.getDocument().toObject(History.class).status.equals("Finish")) {
                            historyArrayList.add(dc.getDocument().toObject(History.class));
                        }
                        else{
                            Log.e("Empty Data", "Order is empty");
                        }
                    }
                    histAdapter.notifyDataSetChanged();
//                    recyclerview.setVisibility(historyArrayList.isEmpty() ? View.GONE : View.VISIBLE);
//                    emptyView.setVisibility(historyArrayList.isEmpty() ? View.VISIBLE : View.GONE);
                }
            }
        });
    }
}