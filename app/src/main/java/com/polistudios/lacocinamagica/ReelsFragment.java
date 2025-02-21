package com.polistudios.lacocinamagica;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.polistudios.lacocinamagica.adapters.VideoAdapter;
import com.polistudios.lacocinamagica.databinding.FragmentReelsBinding;
import com.polistudios.lacocinamagica.models.VideoItem;

import java.util.ArrayList;
import java.util.List;

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
        videos.add(new VideoItem("", "https://cdn.pixabay.com/video/2024/08/16/226795_large.mp4", "", "", ""));
        videos.add(new VideoItem("", "https://cdn.pixabay.com/video/2025/01/03/250395_large.mp4", "", "", ""));

        b.reelsViewPager.setAdapter(new VideoAdapter(videos, requireContext()));

        // Inflate the layout for this fragment
        return b.getRoot();
    }
}