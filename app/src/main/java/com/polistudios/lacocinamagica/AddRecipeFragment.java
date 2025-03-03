package com.polistudios.lacocinamagica;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.polistudios.lacocinamagica.database.AppDatabase;
import com.polistudios.lacocinamagica.database.DatabaseCon;
import com.polistudios.lacocinamagica.database.entity.Category;
import com.polistudios.lacocinamagica.databinding.FragmentAddRecipeBinding;
import com.polistudios.lacocinamagica.models.ListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecipeFragment extends Fragment implements ListDialog.ListDialogListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecipeFragment newInstance(String param1, String param2) {
        AddRecipeFragment fragment = new AddRecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentAddRecipeBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentAddRecipeBinding.inflate(inflater, container, false);

        b.addRecipeEtCategory.setOnClickListener(v -> {
            onEtCategoryAction();
        });

        b.addRecipeEtCategory.setOnFocusChangeListener((view, b1) -> {
            if(b1)
                onEtCategoryAction();
        });

        b.addRecipeEtIngredients.setOnClickListener(v -> {
            ListDialog dialog = ListDialog.newInstance("Ingredients", new ArrayList<ListItem>());
            dialog.addListener(this);
            dialog.show(getChildFragmentManager(), "NoticeDialogFragment");
        });

        // Inflate the layout for this fragment
        return b.getRoot();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String listID, ArrayList<ListItem> data) {
        Toast.makeText(requireContext(), "Positive Click", Toast.LENGTH_SHORT).show();

        switch(listID)
        {
            case "Category" -> {
                String s = "";
                for (ListItem item : data) {
                    if (item.checked)
                        s += item.value + ", ";
                }
                s = s.substring(0, s.length() - 2);

                b.addRecipeEtCategory.setText(s);
            }

            case "Ingredients" -> {
                String s = "";
                for (ListItem item : data) {
                    if (item.checked)
                        s += item.value + "\n";
                }

                b.addRecipeEtIngredients.setText(s);
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, String listID, ArrayList<ListItem> data) {
        Toast.makeText(requireContext(), "Negative Click", Toast.LENGTH_SHORT).show();
    }

    public void GenerateDialog(String id, ArrayList<ListItem> list)
    {
        ListDialog dialog = ListDialog.newInstance(id, list);
        dialog.addListener(this);
        dialog.show(getChildFragmentManager(), "NoticeDialogFragment");
    }

    public void onEtCategoryAction()
    {
        Log.d("AddRecipeFragment", "Category Clicked");
        ArrayList<ListItem> list = new ArrayList<>();
        try {
            AppDatabase db = new DatabaseCon().instance(requireActivity().getApplicationContext());
            List<String> str = Arrays.asList(b.addRecipeEtCategory.getText().toString().split(", "));
            db.categoryDAO().getAll().forEach(category -> {
                list.add(new ListItem(category.name, str.contains(category.name)));
                Log.e("DATABASE", "onCreateView: " + category.toString());
            });
        } catch (Exception e) {
            Log.e("DATABASE", e.toString());
        }

        GenerateDialog("Category", list);
    }
}