package com.alesa.pokemon.Controllers.impl;

import com.alesa.pokemon.Controllers.PokemonController;
import com.alesa.pokemon.Entities.PokemonEntity;
import com.alesa.pokemon.Services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PokemonControllerImpl implements PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Override
    @RequestMapping(value = "/heaviest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PokemonEntity> retrieveHeaviest() {

        return pokemonService.retrieveHeaviest();
    }

    @Override
    @RequestMapping(value = "/highest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PokemonEntity> retrieveHighest() {
        return pokemonService.retrieveHighest();
    }

    @Override
    @RequestMapping(value = "/baseExperience", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PokemonEntity> retrieveMoreBaseExperience() {
        return pokemonService.retrieveMoreBaseExperience();
    }

}
