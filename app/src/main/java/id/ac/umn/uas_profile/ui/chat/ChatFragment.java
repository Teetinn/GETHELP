package id.ac.umn.uas_profile.ui.chat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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

import id.ac.umn.uas_profile.CategoryModel;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentChatBinding;
import id.ac.umn.uas_profile.ui.chat.ChatAdapter;
import id.ac.umn.uas_profile.ui.chat.Chat;
import id.ac.umn.uas_profile.ui.history.History;


public class ChatFragment extends Fragment {

    private final int PHONE_PERMISSION_CODE = 1;
    public static final String TAG = "";
    private FragmentChatBinding binding;
    private Chat chat;
    private ArrayList<Chat> chatArrayList;
    private ChatAdapter chatAdapter;
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
        storageReference = FirebaseStorage.getInstance().getReference();

        fetchData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChatViewModel chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
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

        recyclerview = view.findViewById(R.id.rvChat);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        chatAdapter = new ChatAdapter(getContext(), chatArrayList);
        recyclerview.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
    }

    private void fetchData() {
        userId = fAuth.getCurrentUser().getUid();
        Log.d(TAG, "fetchData: " + userId);
        chatArrayList = new ArrayList<Chat>();

        fStore.collection("users").document(userId).collection("orderList").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        if(dc.getDocument().toObject(Chat.class).status.equals("Ongoing")) {
                            chatArrayList.add(dc.getDocument().toObject(Chat.class));
                        }
                        else{
                            Log.e("Empty Data", "Order is empty");
                        }
                    }
                    chatAdapter.notifyDataSetChanged();
//                    recyclerview.setVisibility(historyArrayList.isEmpty() ? View.GONE : View.VISIBLE);
//                    emptyView.setVisibility(historyArrayList.isEmpty() ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

}