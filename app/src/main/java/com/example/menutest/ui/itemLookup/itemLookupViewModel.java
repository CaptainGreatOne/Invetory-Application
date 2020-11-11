package com.example.menutest.ui.itemLookup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class itemLookupViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public itemLookupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the Item Lookup fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
