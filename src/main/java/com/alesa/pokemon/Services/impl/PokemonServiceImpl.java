package com.alesa.pokemon.Services.impl;

import com.alesa.pokemon.Entities.PokemonEntity;
import com.alesa.pokemon.JSON.PokemonRest;
import com.alesa.pokemon.Repositories.PokemonRepository;
import com.alesa.pokemon.Services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    static final String API_URL = "https://pokeapi.co/api/v2/pokemon/{index}/";

    boolean isDataBaseLoaded = false;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public void loadAllDatabase() {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "Close");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>(headers);


        try {
            isDataBaseLoaded = true;
            for (int i = 0; i < 1118; i++) {

                //System.out.println("Loanding all Pokemons from PokeAPI to local H2 Database, please wait ...");
                ResponseEntity<PokemonRest> response = restTemplate.exchange(String.format(API_URL, i + 1), HttpMethod.GET, entity,
                        PokemonRest.class, i + 1);


                System.out.println(response);
                pokemonRepository.save(new PokemonEntity(response.getBody().getId(), response.getBody().getName(),
                        response.getBody().getHeight(), response.getBody().getWeight(), response.getBody().getBaseExperience()));


            }
        } catch (HttpClientErrorException e) {

        }


    }


    @Override
    public List<PokemonEntity> retrieveHeaviest() {
        if (!isDataBaseLoaded) {
            this.loadAllDatabase();
        }

        return pokemonRepository.getTopFiveHeaviest();
    }

    @Override
    public List<PokemonEntity> retrieveHighest() {
        if (!isDataBaseLoaded) {
            this.loadAllDatabase();
        }
        return pokemonRepository.getTopFiveHighest();
    }

    @Override
    public List<PokemonEntity> retrieveMoreBaseExperience() {
        if (!isDataBaseLoaded) {
            this.loadAllDatabase();
        }
        return pokemonRepository.getTopFiveBaseExperience();
    }
}
