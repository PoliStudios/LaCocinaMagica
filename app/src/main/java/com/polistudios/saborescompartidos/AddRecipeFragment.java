package com.polistudios.saborescompartidos;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.polistudios.saborescompartidos.database.AppDatabase;
import com.polistudios.saborescompartidos.database.DatabaseCon;
import com.polistudios.saborescompartidos.database.dao.CategoryListDAO;
import com.polistudios.saborescompartidos.database.entity.Category;
import com.polistudios.saborescompartidos.database.entity.CategoryList;
import com.polistudios.saborescompartidos.database.entity.Recipe;
import com.polistudios.saborescompartidos.databinding.FragmentAddRecipeBinding;
import com.polistudios.saborescompartidos.models.ListItem;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecipeFragment extends Fragment implements ListDialog.ListDialogListener {
    public AddRecipeFragment() {
        // Required empty public constructor
    }

    public static AddRecipeFragment newInstance() {
        AddRecipeFragment fragment = new AddRecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        Bitmap bm = null;
                        try {
                            Bitmap og = BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(uri));
                            Bitmap c = og.copy(Bitmap.Config.RGBA_F16, true);
                            bm = Bitmap.createScaledBitmap(c, 512, 512, true);

                            b.addRecipeImage.setImageBitmap(bm);

                            //b.addRecipeImage.setImageURI(uri);
                        } catch (FileNotFoundException e) {
                            Toast.makeText(this.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            Log.e("PhotoPicker", "Error: " + e);
                        }
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
    }

    List<Category> categories;
    List<ListItem> ingredients;

    FragmentAddRecipeBinding b;

    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentAddRecipeBinding.inflate(inflater, container, false);

        b.addRecipeImage.setOnClickListener(v -> {

            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });

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

        b.addRecipeBtnSave.setOnClickListener(v -> {
            String title = b.addRecipeEtTitle.getText().toString();
            String desc = b.addRecipeEtDesc.getText().toString();
            String category = b.addRecipeEtCategory.getText().toString();
            String ingredients = b.addRecipeEtIngredients.getText().toString();
            String steps = b.addRecipeEtSteps.getText().toString();
            String time = b.addRecipeEtTime.getText().toString();
            String portions = b.addRecipeEtPortions.getText().toString();
            float rating = b.addRecipeRatingBar.getRating();

            if(title.isBlank()) {
                b.addRecipeEtTitle.setError(getString(R.string.s_err_noTitle));
                return;
            }

            if(ingredients.isBlank()) {
                b.addRecipeEtIngredients.setError(getString(R.string.s_err_noIngredients));
                return;
            }

            if(steps.isBlank()) {
                b.addRecipeEtSteps.setError(getString(R.string.s_err_noSteps));
                return;
            }

            if(time.isBlank()) {
                b.addRecipeEtTime.setError(getString(R.string.s_err_noTime));
                return;
            }

            if(portions.isBlank()) {
                b.addRecipeEtPortions.setError(getString(R.string.s_err_noPortions));
                return;
            }

            float timeF = 0;
            int portionsI = 0;

            try {
                timeF = Float.parseFloat(time);
            } catch (Exception e) {
                b.addRecipeEtTime.setError(getString(R.string.s_err_timeInvalid));
                return;
            }

            try {
                portionsI = Integer.parseInt(portions);
            } catch (Exception e) {
                b.addRecipeEtPortions.setError(getString(R.string.s_err_portionsInvalid));
                return;
            }

            SharedPreferences sp = requireActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
            int id = sp.getInt("id", 0);

            AppDatabase db = DatabaseCon.instance(requireActivity().getApplicationContext());

            Recipe recipe = new Recipe(
                id,
                title,
                desc,
                category,
                Recipe.Difficulty.values()[b.addRecipeSpDifficulty.getSelectedItemPosition()],
                rating,
                timeF,
                portionsI,
                steps,
                ingredients
            );

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