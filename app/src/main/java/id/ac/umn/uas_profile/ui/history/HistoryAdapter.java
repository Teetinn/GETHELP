package id.ac.umn.uas_profile.ui.history;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.umn.uas_profile.CategoryAdapter;
import id.ac.umn.uas_profile.HelperProfileActivity;
import id.ac.umn.uas_profile.OrderScreenActivity;
import id.ac.umn.uas_profile.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter> {
    private ArrayList<HistoryModel> historyList;
    private LayoutInflater mInflater;
    private Context context;

    HistoryAdapter(Context context, ArrayList<HistoryModel> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(context);
        View mItemView = mInflater.inflate(R.layout.history_list, parent, false);
        return new HistoryAdapter.HistoryViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        HistoryModel mCurrent = historyList.get(position);
        holder.name.setText(mCurrent.getName());
        holder.jobDesc.setText(mCurrent.getJobDesc());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, jobDesc, reorder;
        ImageView imageView, btnReorder;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            jobDesc = itemView.findViewById(R.id.tvJobDesc);
            imageView = itemView.findViewById(R.id.profilePic);
            reorder = itemView.findViewById(R.id.tvReorder);
            btnReorder = itemView.findViewById(R.id.reorder);

            reorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent clickVideo = new Intent(context, OrderScreenActivity.class);
                    clickVideo.putExtra("reorder", historyList.get(getAdapterPosition()));
                    context.startActivity(clickVideo);
                }
            });

            btnReorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent clickVideo = new Intent(context, OrderScreenActivity.class);
                    clickVideo.putExtra("reorder", historyList.get(getAdapterPosition()));
                    context.startActivity(clickVideo);
                }
            });
        }
    }
}
