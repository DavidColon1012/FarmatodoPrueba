package com.david.farmatodoprueba.view_models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.david.farmatodoprueba.models.ResultEcuacion;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ResultEcuacion> resultEcuacion;

    public void init() {
        if (resultEcuacion != null) {
            return;
        }
        MutableLiveData<ResultEcuacion> data = new MutableLiveData<>();
        data.setValue(new ResultEcuacion());
        resultEcuacion = data;
    }

    public LiveData<ResultEcuacion> getResult() {
        return resultEcuacion;
    }

    public void setResult(ResultEcuacion result) {
        resultEcuacion.postValue(result);
    }
}
