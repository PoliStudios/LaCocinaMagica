package com.polistudios.saborescompartidos;

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

import com.polistudios.saborescompartidos.databinding.DialogListBinding;
import com.polistudios.saborescompartidos.models.ListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    List<ListDialogListener> listeners = new ArrayList<ListDialogListener>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = getArguments().getParcelableArrayList(ARG_LIST);
        }
        Log.e("TAG", "onCreate");
    }

    DialogListBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = DialogListBinding.inflate(inflater);

        Log.e("TAG", "onCreateView");
        // Inflate the layout for this fragment
        return b.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_list, null);
        builder.setView(v)
                .setPositiveButton("Accept", (dialog, id) -> {
                    LinearLayout ll = v.findViewById(R.id.dialogList_listContainer);
                    for(int i = 0; i < ll.getChildCount(); i++)
                    {
                        CheckBox chk = (CheckBox) ll.getChildAt(i);
                        mList.get((Integer) chk.getTag()).checked = chk.isChecked();
                    }

                    // Send the positive button event back to the host activity.
                    notifyOnDialogPositiveClick(ListDialog.this, listID, mList);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // Send the negative button event back to the host activity.
                    notifyOnDialogNegativeClick(ListDialog.this, listID, mList);
                });

        Log.e("TAG", "onCreateDialog");


        for(int i = 0; i < mList.size(); i++)
        {
            ListItem item = mList.get(i);
            CheckBox chk = new CheckBox(requireContext());
            chk.setTag(i);
            chk.setText(item.value);
            chk.setChecked(item.checked);
            LinearLayout ll = v.findViewById(R.id.dialogList_listContainer);
            ll.addView(chk);
        }

        return builder.create();
    }

    public void addListener(ListDialogListener listener)
    {
        try {
            listeners.add(listener);
        } catch (Exception e) {
            Log.e("ListDialog", "Error adding listener: ", e);
        }
    }

    private void notifyOnDialogPositiveClick(DialogFragment dialog, String id, ArrayList<ListItem> data)
    {
        for(ListDialogListener l : listeners)
            l.onDialogPositiveClick(dialog, id, data);
    }

    private void notifyOnDialogNegativeClick(DialogFragment dialog, String id, ArrayList<ListItem> data)
    {
        for(ListDialogListener l : listeners)
            l.onDialogNegativeClick(dialog, id, data);
    }
}