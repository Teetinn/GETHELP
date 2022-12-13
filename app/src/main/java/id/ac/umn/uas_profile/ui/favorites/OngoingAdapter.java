package id.ac.umn.uas_profile.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.ui.favorites.OngoingAdapter;
import id.ac.umn.uas_profile.ui.favorites.Ongoing;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.MyViewHolder>{

    Context context;
    ArrayList<Ongoing> ongoingArrayList;

    public OngoingAdapter(Context context, ArrayList<Ongoing> ongoingArrayList) {
        this.context = context;
        this.ongoingArrayList = ongoingArrayList;

    }

    @NonNull
    @Override
    public OngoingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.history_list, parent, false);
        return new OngoingAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.MyViewHolder holder, int position) {
        Ongoing history = ongoingArrayList.get(position);
        holder.name.setText(history.name);
        holder.imageView.setImageResource(history.imageView);

    }

    @Override
    public int getItemCount() {
        return ongoingArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvOngoingName);
            imageView = itemView.findViewById(R.id.profilePicHist);
        }
    }
}
