package com.david.farmatodoprueba.models.itemsAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.david.farmatodoprueba.R;
import com.david.farmatodoprueba.models.MarvelResult;
import com.david.farmatodoprueba.views.activities.DetalleResultMarvel;

public class MarvelItem extends RecyclerView.ViewHolder {

    private View itemView;

    private TextView tv_name;

    private TextView tv_title;

    private TextView tv_description;

    private TextView tv_fullName;

    public MarvelItem(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_description = itemView.findViewById(R.id.tv_description);
        tv_fullName = itemView.findViewById(R.id.tv_fullName);
    }

    public void bindMarvelResult(final Context context, final MarvelResult marvelResult) {
        try {
            tv_name.setText(marvelResult.getName());
            tv_title.setText(marvelResult.getTitle());
            tv_description.setText(marvelResult.getDescription());
            tv_fullName.setText(marvelResult.getFullName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetalleResultMarvel.class);
                    intent.putExtra(MarvelResult.TAG, marvelResult);
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
