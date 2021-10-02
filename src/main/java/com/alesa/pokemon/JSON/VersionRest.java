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
public class VersionRest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
