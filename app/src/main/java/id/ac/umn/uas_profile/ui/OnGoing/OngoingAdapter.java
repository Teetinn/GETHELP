package id.ac.umn.uas_profile.ui.OnGoing;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import id.ac.umn.uas_profile.EditProfile;
import id.ac.umn.uas_profile.FinishedSplashScreen;
import id.ac.umn.uas_profile.HWorkCategory;
import id.ac.umn.uas_profile.HelperProfileActivity;
import id.ac.umn.uas_profile.MainActivity;
import id.ac.umn.uas_profile.OrderScreenActivity;
import id.ac.umn.uas_profile.PaymentSplashScreen;
import id.ac.umn.uas_profile.ProfileActivity;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.ui.history.HistoryFragment;
import id.ac.umn.uas_profile.ui.home.HomeFragment;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.MyViewHolder>{

    Context context;
    String userId;
    ArrayList<Ongoing> ongoingArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    public FirebaseAuth fAuth = FirebaseAuth.getInstance();


    public OngoingAdapter(Context context, ArrayList<Ongoing> ongoingArrayList) {
        this.context = context;
        this.ongoingArrayList = ongoingArrayList;

    }

    @NonNull
    @Override
    public OngoingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ongoing_list, parent, false);
        return new OngoingAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.MyViewHolder holder, int position) {
        Ongoing ongoing = ongoingArrayList.get(position);
        holder.name.setText(ongoing.getName());
        Picasso.get().load(ongoing.getImage()).into(holder.imageView);

        holder.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(userId).collection("orderList").document(ongoing.docId);
                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            documentReference.update("status", "Finish").addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    notifyItemChanged(holder.getLayoutPosition());
                                }
                            });

                            Toast.makeText(context, "Order Finished", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent test = new Intent(context, FinishedSplashScreen.class);
                context.startActivity(test);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ongoingArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView imageView;
        Button btnFinish;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvOngoingName);
            imageView = itemView.findViewById(R.id.profilePicOngoing);
            btnFinish = itemView.findViewById(R.id.finishButton);
        }
    }
}
