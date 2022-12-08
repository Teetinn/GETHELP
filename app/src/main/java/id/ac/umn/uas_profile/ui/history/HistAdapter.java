package id.ac.umn.uas_profile.ui.history;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.MyViewHolder>{

    Context context;
    ArrayList<History> historyArrayList;

    public HistAdapter(Context context, ArrayList<History> historyArrayList) {
        this.context = context;
        this.historyArrayList = historyArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        History history = historyArrayList.get(position);
        holder.name.setText(history.name);
        holder.imageView.setImageResource(history.imageView);
        holder.jobDesc.setText(history.jobDesc);

    }

    @Override
    public int getItemCount() {
        return historyArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name, jobDesc;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameHist);
            imageView = itemView.findViewById(R.id.profilePicHist);
            jobDesc = itemView.findViewById(R.id.tvJobDescHist);

        }
}
}