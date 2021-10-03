package com.alesa.pokemon.Services;

import com.alesa.pokemon.Entities.PokemonEntity;
import com.alesa.pokemon.Repositories.PokemonRepository;
import com.alesa.pokemon.Services.impl.PokemonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class PokemonServiceTest {

    private static final int POKEMON_ID = 1;
    private static final String POKEMON_NAME = "bulbasaur";
    private static final int POKEMON_HEIGHT = 10;
    private static final int POKEMON_WEIGHT = 20;
    private static final int POKEMON_BASE_EXPERIENCE = 350;
    public static final int FIRST_ELEMENT = 0;

    @Mock
    PokemonRepository pokemonRepository;

    @Spy
    @InjectMocks
    private PokemonServiceImpl pokemonService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<PokemonEntity> pokemonEntityList = new ArrayList<>();
        pokemonEntityList.add(new PokemonEntity(POKEMON_ID, POKEMON_NAME, POKEMON_HEIGHT, POKEMON_WEIGHT, POKEMON_BASE_EXPERIENCE));

        Mockito.when(pokemonService.isDataBaseLoaded()).thenReturn(Boolean.TRUE);

        Mockito.when(pokemonRepository.getTopFiveBaseExperience()).thenReturn(pokemonEntityList);
        Mockito.when(pokemonRepository.getTopFiveHighest()).thenReturn(pokemonEntityList);
        Mockito.when(pokemonRepository.getTopFiveHeaviest()).thenReturn(pokemonEntityList);

    }

    @Test
    public void retrieveHighestTest() {

        final List<PokemonEntity> topFiveHighestResult = pokemonService.retrieveHighest();

        verify(pokemonService).retrieveHighest();
        verify(pokemonRepository).getTopFiveHighest();
        assertEquals(topFiveHighestResult.get(FIRST_ELEMENT).getId(),POKEMON_ID);
        assertEquals(topFiveHighestResult.get(FIRST_ELEMENT).getName(),POKEMON_NAME);
        assertEquals(topFiveHighestResult.get(FIRST_ELEMENT).getWeight(),POKEMON_WEIGHT);
        assertEquals(topFiveHighestResult.get(FIRST_ELEMENT).getHeight(),POKEMON_HEIGHT);
        assertEquals(topFiveHighestResult.get(FIRST_ELEMENT).getBaseExperience(),POKEMON_BASE_EXPERIENCE);
        assertNotNull(topFiveHighestResult);

    }

    @Test
    public void retrieveHeaviestTest() {

        final List<PokemonEntity> topFiveHeaviestResult = pokemonService.retrieveHeaviest();

        verify(pokemonService).retrieveHeaviest();
        verify(pokemonRepository).getTopFiveHeaviest();
        assertEquals(topFiveHeaviestResult.get(FIRST_ELEMENT).getId(),POKEMON_ID);
        assertEquals(topFiveHeaviestResult.get(FIRST_ELEMENT).getName(),POKEMON_NAME);
        assertEquals(topFiveHeaviestResult.get(FIRST_ELEMENT).getWeight(),POKEMON_WEIGHT);
        assertEquals(topFiveHeaviestResult.get(FIRST_ELEMENT).getHeight(),POKEMON_HEIGHT);
        assertEquals(topFiveHeaviestResult.get(FIRST_ELEMENT).getBaseExperience(),POKEMON_BASE_EXPERIENCE);
        assertNotNull(topFiveHeaviestResult);

    }


    @Test
    public void retrieveMoreBaseExperience() {

        final List<PokemonEntity> topBaseExperienceResult = pokemonService.retrieveMoreBaseExperience();

        verify(pokemonService).retrieveMoreBaseExperience();
        verify(pokemonRepository).getTopFiveBaseExperience();
        assertEquals(topBaseExperienceResult.get(FIRST_ELEMENT).getId(),POKEMON_ID);
        assertEquals(topBaseExperienceResult.get(FIRST_ELEMENT).getName(),POKEMON_NAME);
        assertEquals(topBaseExperienceResult.get(FIRST_ELEMENT).getWeight(),POKEMON_WEIGHT);
        assertEquals(topBaseExperienceResult.get(FIRST_ELEMENT).getHeight(),POKEMON_HEIGHT);
        assertEquals(topBaseExperienceResult.get(FIRST_ELEMENT).getBaseExperience(),POKEMON_BASE_EXPERIENCE);
        assertNotNull(topBaseExperienceResult);

    }
}
