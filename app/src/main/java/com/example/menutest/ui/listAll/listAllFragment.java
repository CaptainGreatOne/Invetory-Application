package com.example.menutest.ui.listAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.menutest.DatabaseHelper;
import com.example.menutest.ItemModel;
import com.example.menutest.R;
import com.example.menutest.ui.listAll.listAllViewModel;

import java.util.List;

public class listAllFragment extends Fragment implements View.OnClickListener{

    private listAllViewModel listAllViewModel;
    ListView lv_itemList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listAllViewModel =
                ViewModelProviders.of(this).get(listAllViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list_all, container, false);
        final TextView textView = root.findViewById(R.id.text_list_all);
        listAllViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        lv_itemList = root.findViewById(R.id.listView_all_items);

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        List<ItemModel> allItems = databaseHelper.getAllItems();

        ArrayAdapter itemArrayAdapter = new ArrayAdapter<ItemModel>(getActivity(), android.R.layout.simple_list_item_1, allItems);
        lv_itemList.setAdapter(itemArrayAdapter);
        return root;

    }

//TODO Allow user to click item in list and copy item id to clipboard. 
    @Override
    public void onClick(View view) {

    }
}
