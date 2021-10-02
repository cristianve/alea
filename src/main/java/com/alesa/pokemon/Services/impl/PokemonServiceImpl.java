package com.alesa.pokemon.Services.impl;

import com.alesa.pokemon.Entities.PokemonEntity;
import com.alesa.pokemon.JSON.PokeApiCountRest;
import com.alesa.pokemon.JSON.PokemonRest;
import com.alesa.pokemon.Repositories.PokemonRepository;
import com.alesa.pokemon.Services.PokemonService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);
    static final String BASE_API_URL = "https://pokeapi.co/api/v2/pokemon/";
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

        logger.info("Loanding all Pokemons from PokeAPI to local H2 Database. This can take up to 2 minutes.");

        try {
            isDataBaseLoaded = true;
            int maxPokemon = this.getMaxPokemonCount();

            for (int i = 0; i < maxPokemon; i++) {

                //System.out.println(String.format("Loanding all Pokemons from PokeAPI to local H2 Database, please wait ... ( %d / %d)", i + 1, maxPokemon));
                ResponseEntity<PokemonRest> response = restTemplate.exchange(String.format(API_URL, i + 1), HttpMethod.GET, entity,
                        PokemonRest.class, i + 1);

                boolean isRedVersion = response.getBody().getGameIndicesRestList().stream().anyMatch(gameIndicesRest ->
                        gameIndicesRest.getVersionRest().getName().equals("red"));

                if (isRedVersion) {
                    pokemonRepository.save(new PokemonEntity(response.getBody().getId(), response.getBody().getName(),
                            response.getBody().getHeight(), response.getBody().getWeight(), response.getBody().getBaseExperience()));
                }
            }
        } catch (HttpClientErrorException e) {
            //Do nothing
        }
        logger.info("Load all Red Pokemons from PokeAPI to local H2 Database. FINISHED!");

    }

    private int getMaxPokemonCount() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection", "Close");
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>(headers);


        ResponseEntity<PokeApiCountRest> response = restTemplate.exchange(BASE_API_URL, HttpMethod.GET, entity,
                PokeApiCountRest.class);

        return response.getBody().getCount();
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
