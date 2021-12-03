package com.example.pokymon.repository;

import com.example.pokymon.Model.PokemonResponse;
import com.example.pokymon.Network.PokemonApiService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private PokemonApiService pokemonApiService;

    @Inject
    public Repository(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    public Observable<PokemonResponse> getPokemons(){
        return pokemonApiService.getPokemons();
    }
}
