package com.alesa.pokemon.Controllers;

import com.alesa.pokemon.Entities.PokemonEntity;

import java.util.List;

public interface PokemonController {

    List<PokemonEntity> retrieveHeaviest();
    List<PokemonEntity> retrieveHighest();
    List<PokemonEntity> retrieveMoreBaseExperience();

}
