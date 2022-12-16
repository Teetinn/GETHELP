package id.ac.umn.uas_profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<CategoryModel> categoryList;
    private LayoutInflater mInflater;
    private Context context;

    CategoryAdapter(Context context, ArrayList<CategoryModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }



    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(context);
        View mItemView = mInflater.inflate(R.layout.category_list, parent, false);
        return new CategoryViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        CategoryModel mCurrent = categoryList.get(position);
        holder.name.setText(mCurrent.getName());
        holder.jobDesc.setText(mCurrent.getJobDesc());
        holder.location.setText(mCurrent.getLocation());
        holder.rating.setText(mCurrent.getRating());
        Picasso.get().load(mCurrent.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, jobDesc, location, rating;
        ImageView imageView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            jobDesc = itemView.findViewById(R.id.tvJobDesc);
            location = itemView.findViewById(R.id.tvLocation);
            rating = itemView.findViewById(R.id.tvRating);
            imageView = itemView.findViewById(R.id.profilePic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent clickHelperProf = new Intent(context, HelperProfileActivity.class);
                    clickHelperProf.putExtra("maidList", categoryList.get(getAdapterPosition()));
                    context.startActivity(clickHelperProf);
                }
            });
        }
    }
}
