package id.ac.umn.uas_profile.ui.history;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.umn.uas_profile.FinishedSplashScreen;
import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.RateActivity;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.MyViewHolder>{

    Context context;
    ArrayList<History> historyArrayList;
    public FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    public FirebaseAuth fAuth = FirebaseAuth.getInstance();

    public HistAdapter(Context context, ArrayList<History> historyArrayList) {
        this.context = context;
        this.historyArrayList = historyArrayList;

    }

    @NonNull
    @Override
    public HistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
        return new HistAdapter.MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull HistAdapter.MyViewHolder holder, int position) {
        History history = historyArrayList.get(position);
        holder.name.setText(history.getName());
        Picasso.get().load(history.getImage()).into(holder.imageView);
        holder.btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ratingIntent = new Intent(context, RateActivity.class);
                ratingIntent.putExtra("orderList", history);
                context.startActivity(ratingIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return historyArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, jobDesc;
        ImageView imageView;
        Button btnRate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameHist);
            imageView = itemView.findViewById(R.id.profilePicHist);
            jobDesc = itemView.findViewById(R.id.tvJobDescHist);
            btnRate = itemView.findViewById(R.id.rateButton);
        }
}
}