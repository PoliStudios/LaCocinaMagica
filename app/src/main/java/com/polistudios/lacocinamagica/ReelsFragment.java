package com.polistudios.lacocinamagica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polistudios.lacocinamagica.adapters.VideoAdapter;
import com.polistudios.lacocinamagica.databinding.FragmentReelsBinding;
import com.polistudios.lacocinamagica.models.VideoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReelsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReelsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReelsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReelsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReelsFragment newInstance(String param1, String param2) {
        ReelsFragment fragment = new ReelsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    FragmentReelsBinding b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentReelsBinding.inflate(inflater, container, false);

        List<VideoItem> videos = new ArrayList<VideoItem>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("reels");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<HashMap<String, Object>> data = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                Log.d("REELS (DATA)", data.toString());
                for (HashMap<String, Object> i : data)
                {
                    //public VideoItem(String idUser, String videoTitle, String videoDescription, String idRecipe, String shares, String likes, String videoURL) {
                    String idUser = String.valueOf(i.get("idUser"));
                    String title = String.valueOf(i.get("title"));
                    String description = String.valueOf(i.get("description"));
                    String idRecipe = String.valueOf(i.get("idRecipe"));
                    String shares = String.valueOf(i.get("shares"));
                    String likes = String.valueOf(i.get("likes"));
                    String videoURL = String.valueOf(i.get("url"));

                    VideoItem videoItem = new VideoItem(idUser, title, description, idRecipe, shares, likes, videoURL);
                    Log.d("REELS (FOR LOOP)", videoItem.toString());
                    videos.add(videoItem);
                }

                Log.d("REELS (VIDEOS)", videos.toString());
                b.reelsViewPager.setAdapter(new VideoAdapter(videos, requireContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("ERROR", "Failed to read value.", error.toException());
            }
        });

        // Inflate the layout for this fragment
        return b.getRoot();
    }
}