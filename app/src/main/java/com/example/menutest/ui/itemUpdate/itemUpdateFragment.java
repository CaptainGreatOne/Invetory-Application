package com.example.menutest.ui.itemUpdate;

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
import com.example.menutest.R;
import com.example.menutest.ui.itemUpdate.itemUpdateViewModel;

public class itemUpdateFragment extends Fragment{
//TODO FIX ITEM update. add limit to id input, give error message if item isn't found. 
    private itemUpdateViewModel itemUpdateViewModel;

    Button submit;
    EditText id, quantity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        itemUpdateViewModel =
                ViewModelProviders.of(this).get(itemUpdateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_update, container, false);
        final TextView textView = root.findViewById(R.id.text_item_update);

        submit = root.findViewById(R.id.btn_update_submit);
        id = root.findViewById(R.id.text_update_id);
        quantity = root.findViewById(R.id.text_quant_update);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
                int id_;
                int quant;
                boolean success = false;

                //to catch any input errors...
                try {

                    id_ = Integer.parseInt(id.getText().toString());
                    quant = Integer.parseInt(quantity.getText().toString());
                    success = dbhelper.updateQuantity(id_, quant);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Item doesn't exist ", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(getActivity(), "Sucess: " + success, Toast.LENGTH_SHORT).show();

            }

        });


        itemUpdateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
