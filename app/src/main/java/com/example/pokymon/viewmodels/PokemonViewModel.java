package com.example.pokymon.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokymon.Model.Pokemon;
import com.example.pokymon.Model.PokemonResponse;
import com.example.pokymon.repository.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel  extends ViewModel {
    private Repository repository;

    MutableLiveData<ArrayList<Pokemon>> PokemonList = new MutableLiveData<>();
@ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return PokemonList;
    }
    @SuppressLint("CheckResult")
    public void getPokemons (){
     
      repository.getPokemons().subscribeOn(Schedulers.io())
              .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                  @Override
                  public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {

                      ArrayList<Pokemon> list =pokemonResponse.getResults();
                      for (Pokemon pokemon :list)
                      {
                          String url =pokemon.getUrl();
                          String[] pokemonIndex = url.split("/");
                          pokemon.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                      }
                      return list;
                  }

              })
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(result-> PokemonList.setValue(result) ,
                      error-> Log.e("ViewModel", error.getMessage()) );


    
    }
}
