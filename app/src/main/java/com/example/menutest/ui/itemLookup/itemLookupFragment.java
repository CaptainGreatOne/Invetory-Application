package com.example.menutest.ui.itemLookup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.menutest.DatabaseHelper;
import com.example.menutest.ItemModel;
import com.example.menutest.R;
import com.example.menutest.ui.itemLookup.itemLookupViewModel;

import java.util.List;

public class itemLookupFragment extends Fragment{

    private itemLookupViewModel itemLookupViewModel;
    ListView lv_itemList;
    EditText editText;
    Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        itemLookupViewModel =
                ViewModelProviders.of(this).get(itemLookupViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lookup_item, container, false);
        final TextView textView = root.findViewById(R.id.text_item_lookup);

        lv_itemList = root.findViewById(R.id.listview_lookup);
        editText = root.findViewById(R.id.text_search_item);
        btn = root.findViewById(R.id.btn_lookup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                String x = editText.getText().toString();

                //to catch any input errors...
                try {
                    List<ItemModel> results = databaseHelper.searchItems(x);
                    ArrayAdapter itemArrayAdapter = new ArrayAdapter<ItemModel>(getActivity(), android.R.layout.simple_expandable_list_item_1, results);
                    lv_itemList.setAdapter(itemArrayAdapter);
                    Toast.makeText(getActivity(), "got to the search ", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Failed Search ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        itemLookupViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
