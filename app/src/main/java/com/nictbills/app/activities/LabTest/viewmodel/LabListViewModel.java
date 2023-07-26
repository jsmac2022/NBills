package com.nictbills.app.activities.LabTest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nictbills.app.activities.LabTest.model.LabListModel;

import java.util.ArrayList;

public class LabListViewModel extends ViewModel {

    MutableLiveData<ArrayList<LabListModel>> userLiveData;
    ArrayList<LabListModel> userArrayList;

    public LabListViewModel() {
        userLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }

    public MutableLiveData<ArrayList<LabListModel>> getUserMutableLiveData(){
        return userLiveData;
    }

    public void init(){
        populateList();
        userLiveData.setValue(userArrayList);
    }

    public void populateList(){

        LabListModel user = new LabListModel();
        user.setTitle("Darknight");
        user.setDescription("Best rating movie");

        userArrayList = new ArrayList<>();
        userArrayList.add(user);
        userArrayList.add(user);
        userArrayList.add(user);
        userArrayList.add(user);
        userArrayList.add(user);
        userArrayList.add(user);
    }
}
