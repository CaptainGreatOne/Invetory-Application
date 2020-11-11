package com.example.menutest.ui.removeItem;


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
import com.example.menutest.ui.removeItem.removeItemViewModel;

public class removeItemFragment extends Fragment implements View.OnClickListener{
    private removeItemViewModel removeItemViewModel;

    Button submit;
    EditText input;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        removeItemViewModel =
                ViewModelProviders.of(this).get(removeItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_remove_item, container, false);
        final TextView textView = root.findViewById(R.id.text_remove_item);


        submit = root.findViewById(R.id.btn_remove_submit);
        input = root.findViewById(R.id.text_remove_item_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                //to catch any input errors...
                try {
                    id = Integer.parseInt(input.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Item doesn't exist ", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper dbhelper = new DatabaseHelper(getActivity());
                boolean success = dbhelper.deleteOne(Integer.parseInt(input.getText().toString()));
                Toast.makeText(getActivity(), "Sucess: " + success, Toast.LENGTH_SHORT).show();

            }

        });

        removeItemViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
