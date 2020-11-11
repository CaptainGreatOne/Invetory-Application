package com.example.menutest.ui.removeItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class removeItemViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public removeItemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Remove Item fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
