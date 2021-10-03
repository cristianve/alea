package com.alesa.pokemon.Controllers;

import com.alesa.pokemon.Controllers.impl.PokemonControllerImpl;
import com.alesa.pokemon.Entities.PokemonEntity;
import com.alesa.pokemon.Services.PokemonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class PokemonControllerTest {


    private static final int POKEMON_ID = 1;
    private static final String POKEMON_NAME = "bulbasaur";
    private static final int POKEMON_HEIGHT = 10;
    private static final int POKEMON_WEIGHT = 20;
    private static final int POKEMON_BASE_EXPERIENCE = 350;

    private static final PokemonEntity POKEMON_ENTITY = new PokemonEntity();
    private static final List<PokemonEntity> pokemonEntityList = new ArrayList<>();


    @Mock
    private PokemonService pokemonService;

    @Spy
    @InjectMocks
    private PokemonControllerImpl pokemonController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        POKEMON_ENTITY.setId(POKEMON_ID);
        POKEMON_ENTITY.setName(POKEMON_NAME);
        POKEMON_ENTITY.setHeight(POKEMON_HEIGHT);
        POKEMON_ENTITY.setWeight(POKEMON_WEIGHT);
        POKEMON_ENTITY.setBaseExperience(POKEMON_BASE_EXPERIENCE);

        pokemonEntityList.add(POKEMON_ENTITY);

        Mockito.when(pokemonService.retrieveHeaviest())
                .thenReturn(pokemonEntityList);
        Mockito.when(pokemonService.retrieveHighest())
                .thenReturn(pokemonEntityList);
        Mockito.when(pokemonService.retrieveMoreBaseExperience())
                .thenReturn(pokemonEntityList);

    }

    @Test
    public void retrieveHeaviestTest() {

        final List<PokemonEntity> response = pokemonController.retrieveHeaviest();

        verify(pokemonController).retrieveHeaviest();
        verify(pokemonService).retrieveHeaviest();
        assertEquals(response, pokemonEntityList);

    }

    @Test
    public void retrieveHighestTest() {

        final List<PokemonEntity> response = pokemonController.retrieveHighest();

        verify(pokemonController).retrieveHighest();
        verify(pokemonService).retrieveHighest();
        assertEquals(response, pokemonEntityList);

    }

    @Test
    public void retrieveMoreBaseExperience() {

        final List<PokemonEntity> response = pokemonController.retrieveMoreBaseExperience();

        verify(pokemonController).retrieveMoreBaseExperience();
        verify(pokemonService).retrieveMoreBaseExperience();
        assertEquals(response, pokemonEntityList);

    }

}
