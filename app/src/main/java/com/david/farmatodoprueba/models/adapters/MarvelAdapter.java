package com.david.farmatodoprueba.models.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.david.farmatodoprueba.R;
import com.david.farmatodoprueba.models.MarvelResult;
import com.david.farmatodoprueba.models.itemsAdapter.MarvelItem;

import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelItem> {

    private List<MarvelResult> listMarvelResult;
    private Context mContext;

    public MarvelAdapter(Context mContext,List<MarvelResult> listMarvelResult) {
        this.listMarvelResult = listMarvelResult;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MarvelItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_marvel, viewGroup, false);
        return new MarvelItem(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MarvelItem marvelItem, int position) {
        MarvelResult marvelResult = listMarvelResult.get(position);
        marvelItem.bindMarvelResult(mContext,marvelResult);
    }

    @Override
    public int getItemCount() {
        if (listMarvelResult != null) {
            return listMarvelResult.size();
        } else {
            return 0;
        }
    }

    public void setMarvel(List<MarvelResult> marvels) {
        listMarvelResult = marvels;
        notifyDataSetChanged();
    }

}
