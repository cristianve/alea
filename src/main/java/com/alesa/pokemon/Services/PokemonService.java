package com.alesa.pokemon.Services;

import com.alesa.pokemon.Entities.PokemonEntity;

import java.util.List;

public interface PokemonService {

    void loadAllDatabase();
    List<PokemonEntity> retrieveHeaviest();
    List<PokemonEntity> retrieveHighest();
    List<PokemonEntity> retrieveMoreBaseExperience();

}
