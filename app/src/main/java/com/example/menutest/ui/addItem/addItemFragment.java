package com.example.menutest.ui.addItem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class addItemFragment extends Fragment implements View.OnClickListener {
    //title
    private addItemViewModel addItemViewModel;
    Button submit;
    EditText id, name, dept, price, quantity, barcode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        addItemViewModel =
                ViewModelProviders.of(this).get(addItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);
        final TextView textView = root.findViewById(R.id.text_add_item);


        submit = root.findViewById(R.id.button_add_item);

        id = root.findViewById(R.id.text_add_item_id);
        name = root.findViewById(R.id.text_add_item_name);
        dept = root.findViewById(R.id.text_add_item_dept);
        price = root.findViewById(R.id.text_add_item_price);
        quantity = root.findViewById(R.id.text_add_item_quantity);
        barcode = root.findViewById(R.id.text_add_item_barcode);



        //listener
       submit.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
                boolean success = false;
               ItemModel itemModel;
              // checkInput();

               try {
                   itemModel = new ItemModel((Integer.parseInt(id.getText().toString())), name.getText().toString().toUpperCase(), dept.getText().toString().toUpperCase(), Double.parseDouble(price.getText().toString()), Integer.parseInt(quantity.getText().toString()), barcode.getText().toString());
                   Toast.makeText(getActivity(), itemModel.toString(), Toast.LENGTH_SHORT).show();
                    // TODO Restrict incorrect input length for both ID and Barcode, then dynamically display it.

               }
               catch (Exception e){
                   Toast.makeText(getActivity(), "You're a dumbass", Toast.LENGTH_SHORT).show();
                   itemModel = new ItemModel(-1, "error", "error dept", 69.20, 69, "1234567890");
               }

               DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
               success = dbhelper.addOne(itemModel);
               Toast.makeText(getActivity(), "Sucess: " + success, Toast.LENGTH_SHORT).show();

           }

       });


        addItemViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    @Override
    public void onClick(View view) {
    }

    public void checkInput(){
    }

}
