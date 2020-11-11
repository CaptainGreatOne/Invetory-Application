package com.example.menutest.ui.listAll;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class listAllViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public listAllViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the List All Items fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
