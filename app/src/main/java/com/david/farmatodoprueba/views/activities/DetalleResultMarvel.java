package com.david.farmatodoprueba.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.david.farmatodoprueba.R;
import com.david.farmatodoprueba.models.MarvelResult;

public class DetalleResultMarvel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_marvel);

        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_description = findViewById(R.id.tv_description);
        TextView tv_fullName = findViewById(R.id.tv_fullName);

        MarvelResult marvelResult = getIntent().getParcelableExtra(MarvelResult.TAG);

        tv_name.setText(marvelResult.getName());
        tv_title.setText(marvelResult.getTitle());
        tv_description.setText(marvelResult.getDescription());
        tv_fullName.setText(marvelResult.getFullName());

    }

}
