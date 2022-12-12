package id.ac.umn.uas_profile.ui.chat;

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
import id.ac.umn.uas_profile.ui.history.HistAdapter;
import id.ac.umn.uas_profile.ui.history.History;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    Context context;
    ArrayList<Chat> chatArrayList;

    public ChatAdapter(Context context, ArrayList<Chat> chatArrayList) {
        this.context = context;
        this.chatArrayList = chatArrayList;

    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.chat_list, parent, false);
        return new ChatAdapter.MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        Chat chat = chatArrayList.get(position);
        holder.name.setText(chat.name);
        holder.imageView.setImageResource(chat.imageView);
        holder.jobDesc.setText(chat.jobDesc);

    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView name, jobDesc;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_call);
            imageView = itemView.findViewById(R.id.chatProfPic);
            jobDesc = itemView.findViewById(R.id.desc_call);

        }
    }
}
