package id.ac.umn.uas_profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
//    private SearchView searchBar;
    private EditText searchBar;
    ArrayList<CategoryModel> categoryList = new ArrayList<CategoryModel>();
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    ImageView profileImage;
    FirebaseAuth fAuth;
    StorageReference storageReference;
    private static final String TAG = "FirestoreActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Query query = fStore.collection("maidList")
                .orderBy("name", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<CategoryModel> options = new FirestoreRecyclerOptions.Builder<CategoryModel>()
                .setQuery(query, CategoryModel.class)
                .build();
//        mAdapter = new CategoryAdapter(this, categoryList);
        mAdapter = new CategoryAdapter(options);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                CategoryModel category = documentSnapshot.toObject(CategoryModel.class);
//                String id = documentSnapshot.getId();
//                Toast.makeText(CategoryActivity.this, "Position: " + position + "ID: " + id, Toast.LENGTH_SHORT).show();
//                startActivity(CategoryActivity.this, HelperProfileActivity.class);
//            }
//        });
        profileImage = findViewById(R.id.profilePic);

        searchBar = findViewById(R.id.searchBar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "Searchbox has changed to: " + s.toString());
                Query query;

                if (s.toString().isEmpty()) {
                    query = fStore.collection("maidList")
                            .orderBy("name", Query.Direction.ASCENDING);
                } else {
                    query = fStore.collection("maidList")
                            .whereEqualTo("name", s.toString())
                            .orderBy("name", Query.Direction.ASCENDING);
                }

                FirestoreRecyclerOptions<CategoryModel> options = new FirestoreRecyclerOptions.Builder<CategoryModel>()
                        .setQuery(query, CategoryModel.class)
                        .build();

                mAdapter.updateOptions(options);
            }
        });

//        searchBar = findViewById(R.id.searchBar);
//        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchData(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
        EventChangeListener();
        ProfilePictShow();
    }

//    private void searchData(String query) {
//        progressDialog.setTitle("Searching...");
//        progressDialog.show();
//        fStore.collection("maidList").whereEqualTo("name", query.toLowerCase())
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        categoryList.clear();
//                        progressDialog.dismiss();
//
//                        for (DocumentChange dc : task.getResult()) {
//                            if (dc.getType() == DocumentChange.Type.ADDED) {
//                                categoryList.add(dc.getDocument().toObject(CategoryModel.class));
//                            }
//                            mAdapter.notifyDataSetChanged();
//                            if (progressDialog.isShowing()) progressDialog.dismiss();
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        progressDialog.dismiss();
//                        Toast.makeText(CategoryActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private void EventChangeListener() {
        fStore.collection("maidList").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                    Log.e("Firestore error", error.getMessage());
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        categoryList.add(dc.getDocument().toObject(CategoryModel.class));
                    }
                    mAdapter.notifyDataSetChanged();
                    if (progressDialog.isShowing()) progressDialog.dismiss();
                }
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
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

//    public void buttonClick(View v) {
//        switch (v.getId()) {
////           case R.id.profilePic:
////                Intent goToEdit = new Intent(this, EditProfileActivity.class);
////                startActivity(goToEdit);
////                break;
//            case R.id.profilePic:
//                Intent goToProfile = new Intent(this, ProfileActivity.class);
//                startActivity(goToProfile);
//                break;
//        }
//    }
}