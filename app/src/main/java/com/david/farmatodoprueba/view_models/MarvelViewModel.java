package com.david.farmatodoprueba.view_models;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.david.farmatodoprueba.models.AppDialogs;
import com.david.farmatodoprueba.models.MarvelResult;
import com.david.farmatodoprueba.models.interfaces.Callbacks;
import com.david.farmatodoprueba.models.repositories.MarvelRepository;

import java.util.ArrayList;
import java.util.List;

public class MarvelViewModel extends ViewModel {

    private MutableLiveData<List<MarvelResult>> mListMarvel = new MutableLiveData<>();

    private ProgressDialog dialog;

    public void init(final Context mContext, int multiple) {
        dialog = AppDialogs.createLoading(mContext);

        MarvelRepository marvelRepository = MarvelRepository.getInstance();
        marvelRepository.getListMarvel(mContext, multiple, new Callbacks() {
            @Override
            public void onSuccess(Object data) {
                mListMarvel.setValue((List<MarvelResult>) data);

                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }

            @Override
            public void onFail(String message) {
                AppDialogs.createDialog(mContext, message).show();
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });
    }

    public LiveData<List<MarvelResult>> getmListMarvel() {
        return mListMarvel;
    }

    public List<MarvelResult> filter(String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<MarvelResult> filteredModelList = new ArrayList<>();

        if (mListMarvel.getValue() != null) {
            for (MarvelResult marvelResult : mListMarvel.getValue()) {
                String text = marvelResult.getTitle();

                if (text == null) {
                    text = marvelResult.getName();
                }

                if (text == null) {
                    text = marvelResult.getFullName();
                }

                if (text != null && text.toLowerCase().contains(lowerCaseQuery)) {
                    filteredModelList.add(marvelResult);
                }
            }
        }

        return filteredModelList;
    }

}
