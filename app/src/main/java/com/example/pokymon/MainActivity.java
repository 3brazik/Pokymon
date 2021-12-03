package com.example.pokymon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pokymon.Model.Pokemon;
import com.example.pokymon.adapters.PokemonAdapter;
import com.example.pokymon.viewmodels.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
 private RecyclerView recyclerView ;
 private PokemonViewModel viewModel;
 private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.pokemon_recyclerview);
        adapter =new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getPokemons();
        viewModel.getPokemonList().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                adapter.setList(pokemons);
            }
        });
    }
}