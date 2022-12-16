package id.ac.umn.uas_profile.ui.chat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.ui.history.HistAdapter;
import id.ac.umn.uas_profile.ui.history.History;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private static final int REQUEST_CALL = 1;
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
        holder.name.setText(chat.getName());
        Picasso.get().load(chat.getImage()).into(holder.imageView);
        holder.jobDesc.setText(chat.getJobDesc());
        holder.pNumber.setText(chat.getPhone());

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
            private void makePhoneCall() {
//                if (ContextCompat.checkSelfPermission(context,
//                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions((Activity) context,
//                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
//                } else {
                    Intent makeCall = new Intent(Intent.ACTION_DIAL);
                    makeCall.setData(Uri.parse("tel: " + chat.getPhone()));
                    context.startActivity(makeCall);
//                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_CALL) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//            }
//        }
//    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, jobDesc, pNumber;
        ImageView imageView;
        Button btnCall;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_call);
            imageView = itemView.findViewById(R.id.chatProfPic);
            jobDesc = itemView.findViewById(R.id.desc_call);
            btnCall = itemView.findViewById(R.id.btn_call);
            pNumber = itemView.findViewById(R.id.phoneNumber);

        }
    }
}






