package com.polistudios.lacocinamagica;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.polistudios.lacocinamagica.models.ListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDialog extends DialogFragment {
    public String listID;

    private static final String ARG_LIST = "param1";

    private ArrayList<ListItem> mList;

    public ListDialog(String id) {
        listID = id;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param list The list to display.
     * @return A new instance of fragment ListDialog.
     */
    public static ListDialog newInstance(String id, ArrayList<ListItem> list) {
        ListDialog fragment = new ListDialog(id);
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    public interface ListDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String id, ArrayList<ListItem> data);
        public void onDialogNegativeClick(DialogFragment dialog, String id, ArrayList<ListItem> data);
    }
    ListDialogListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        for(ListItem item : mList)
        {
            CheckBox chk = new CheckBox(requireContext());
            chk.setText(item.value);
            LinearLayout layout = container.findViewById(R.id.dialogList_listContainer);
            layout.addView(chk);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_list, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_list, null))
                .setPositiveButton("Accept", (dialog, id) -> {
                    // Send the positive button event back to the host activity.
                    listener.onDialogPositiveClick(ListDialog.this, listID, mList);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // Send the negative button event back to the host activity.
                    listener.onDialogNegativeClick(ListDialog.this, listID, mList);
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface.
        try {
            // Instantiate the NoticeDialogListener so you can send events to
            // the host.
            listener = (ListDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface. Throw exception.
            throw new ClassCastException(context
                    + " must implement ListDialogListener");
        }
    }
}