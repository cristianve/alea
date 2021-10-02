package com.alesa.pokemon.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonRest {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("base_experience")
    private int baseExperience;

    @JsonProperty("height")
    private int height;

    @JsonProperty("weight")
    private int weight;

    @JsonProperty("game_indices")
    private List<GameIndicesRest> gameIndicesRestList;

}
