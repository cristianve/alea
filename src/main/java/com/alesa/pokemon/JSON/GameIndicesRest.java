package com.alesa.pokemon.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameIndicesRest {

    @JsonProperty("game_index")
    private int game_index;

    @JsonProperty("version")
    private VersionRest versionRest;

}
