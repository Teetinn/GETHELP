package id.ac.umn.uas_profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends FirestoreRecyclerAdapter<CategoryModel, CategoryAdapter.CategoryViewHolder> {
//    private ArrayList<CategoryModel> categoryList;
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
//        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

//    CategoryAdapter(Context context, ArrayList<CategoryModel> categoryList) {
//        this.context = context;
//        this.categoryList = categoryList;
//    }

    CategoryAdapter(FirestoreRecyclerOptions<CategoryModel> options, OnItemClickListener listener) {
        super(options);
        this.listener = listener;
    }

    CategoryAdapter(FirestoreRecyclerOptions<CategoryModel> options) {
        super(options);
        this.listener = null;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        mInflater = LayoutInflater.from(context);
        View mItemView = mInflater.inflate(R.layout.category_list, parent, false);
        return new CategoryViewHolder(mItemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull CategoryModel model) {
//        CategoryModel mCurrent = categoryList.get(position);
        holder.name.setText(model.getName());
        holder.jobDesc.setText(model.getJobDesc());
        holder.location.setText(model.getLocation());
        holder.rating.setText(String.valueOf(model.getRating()));
        Picasso.get().load(model.getImage()).into(holder.imageView);

        if (listener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.getAdapterPosition());
//                    Intent clickHelperProf = new Intent(context, HelperProfileActivity.class);
//                    clickHelperProf.putExtra("maidList", holder.getAdapterPosition());
//                    context.startActivity(clickHelperProf);
                }
            });
        }
    }

//    @Override
//    public int getItemCount() {
//        return categoryList.size();
//    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, jobDesc, location, rating;
        ImageView imageView;
        View view;

        public CategoryViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.tvName);
            jobDesc = itemView.findViewById(R.id.tvJobDesc);
            location = itemView.findViewById(R.id.tvLocation);
            rating = itemView.findViewById(R.id.tvRating);
            imageView = itemView.findViewById(R.id.profilePic);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION && listener != null) {
//                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
//                    }
////                    Intent clickHelperProf = new Intent(context, HelperProfileActivity.class);
////                    clickHelperProf.putExtra("maidList", categoryList.get(getAdapterPosition()));
////                    context.startActivity(clickHelperProf);
//                }
//            });
        }
    }


}
