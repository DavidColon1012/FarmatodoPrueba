package com.david.farmatodoprueba.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.david.farmatodoprueba.R;
import com.david.farmatodoprueba.models.MarvelResult;
import com.david.farmatodoprueba.models.adapters.MarvelAdapter;
import com.david.farmatodoprueba.view_models.MarvelViewModel;

import java.util.List;

public class MarvelActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerMarvel;
    private MarvelAdapter marvelAdapter;

    private MarvelViewModel marvelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel);

        configurarActionBar();

        configView();

        marvelViewModel = ViewModelProviders.of(this).get(MarvelViewModel.class);

        marvelViewModel.init(this, getIntent().getIntExtra("multiple", 0));

        marvelViewModel.getmListMarvel().observe(this, new Observer<List<MarvelResult>>() {
            @Override
            public void onChanged(@Nullable List<MarvelResult> marvels) {
                marvelAdapter.setMarvel(marvels);
            }
        });


        initRecyclerView();

    }

    public void configurarActionBar() {
        Toolbar mToolbar = findViewById(R.id.appbar);
        setSupportActionBar(mToolbar);

        ((TextView) findViewById(R.id.tvTitleTolbar)).setText(getString(R.string.farma_todo));
        ((TextView) findViewById(R.id.tvDescripcionTolbar)).setText(R.string.marvel);
    }

    private void configView() {
        recyclerMarvel = findViewById(R.id.recyclerMarvel);
        recyclerMarvel.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerView() {
        marvelAdapter = new MarvelAdapter(this, marvelViewModel.getmListMarvel().getValue());
        recyclerMarvel.setAdapter(marvelAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.marvel_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        List<MarvelResult> fileredMarvelResultList = marvelViewModel.filter(query);
        marvelAdapter.setMarvel(fileredMarvelResultList);
        recyclerMarvel.scrollToPosition(0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        List<MarvelResult> fileredMarvelResultList = marvelViewModel.filter(query);
        marvelAdapter.setMarvel(fileredMarvelResultList);
        recyclerMarvel.scrollToPosition(0);
        return false;
    }
}
