package com.alesa.pokemon.Repositories;

import com.alesa.pokemon.Entities.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {

    @Query(value = "SELECT * FROM POKEMONS ORDER BY weight  DESC LIMIT 5", nativeQuery = true)
    List<PokemonEntity> getTopFiveHeaviest();

    @Query(value = "SELECT * FROM POKEMONS ORDER BY height  DESC LIMIT 5", nativeQuery = true)
    List<PokemonEntity> getTopFiveHighest();

    @Query(value = "SELECT * FROM POKEMONS ORDER BY BASE_EXPERIENCE  DESC LIMIT 5", nativeQuery = true)
    List<PokemonEntity> getTopFiveBaseExperience();


}
