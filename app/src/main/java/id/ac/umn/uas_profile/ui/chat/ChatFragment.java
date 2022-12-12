package id.ac.umn.uas_profile.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.umn.uas_profile.R;
import id.ac.umn.uas_profile.databinding.FragmentChatBinding;
import id.ac.umn.uas_profile.ui.chat.ChatAdapter;
import id.ac.umn.uas_profile.ui.chat.Chat;


public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ArrayList<Chat> chatArrayList;
    private String[] name;
    private int[] imageView;
    private String[] jobDesc;
    private RecyclerView recyclerview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChatViewModel chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textChat;
//        chatViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

        recyclerview = view.findViewById(R.id.rvChat);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        ChatAdapter histAdapter = new ChatAdapter(getContext(), chatArrayList);
        recyclerview.setAdapter(histAdapter);
        histAdapter.notifyDataSetChanged();
    }

    private void dataInitialize(){
        chatArrayList = new ArrayList<>();

        name = new String[]{
                getString(R.string.testchat),
                getString(R.string.testchat)
        };
        imageView = new int[]{
                R.drawable.pablo,
                R.drawable.pablo
        };

        jobDesc = new String[]{
                getString(R.string.testchat),
                getString(R.string.testchat)
        };

        for (int i=0; i< name.length; i++){
            Chat chat = new Chat(name[i], jobDesc[i], imageView[i]);
            chatArrayList.add(chat);
        }
    }
}