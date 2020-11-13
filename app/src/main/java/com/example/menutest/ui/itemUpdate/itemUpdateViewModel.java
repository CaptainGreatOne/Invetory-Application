package com.example.menutest.ui.itemUpdate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class itemUpdateViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public itemUpdateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Update Item Quantity");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
