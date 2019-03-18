package com.david.farmatodoprueba.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.david.farmatodoprueba.R;
import com.david.farmatodoprueba.models.Calculate;
import com.david.farmatodoprueba.models.ResultEcuacion;
import com.david.farmatodoprueba.view_models.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private EditText editEquation;
    private TextView txtResult;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        configurarActionBar();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        mainActivityViewModel.getResult().observe(this, new Observer<ResultEcuacion>() {
            @Override
            public void onChanged(@Nullable ResultEcuacion resultEcuacion) {
                txtResult.setText(resultEcuacion.getResult());
            }
        });

        configView();
    }

    public void configurarActionBar() {
        Toolbar mToolbar = findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);

        ((TextView) findViewById(R.id.tvTitleTolbar)).setText(getString(R.string.farma_todo));
        ((TextView) findViewById(R.id.tvDescripcionTolbar)).setText(R.string.calculadora);
    }

    private void configView() {
        editEquation = findViewById(R.id.editEquation);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        txtResult.setText(mainActivityViewModel.getResult().getValue().getResult());

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate calculate = new Calculate();
                ResultEcuacion result = calculate.calculate(editEquation.getText().toString());

                mainActivityViewModel.setResult(result);
                txtResult.setText(result.getResult());

                if (result.isSuccess()) {
                    Intent intent = new Intent(context, MarvelActivity.class);
                    intent.putExtra("multiple", result.getMultiple());
                    startActivity(intent);
                }
            }
        });
    }
}
