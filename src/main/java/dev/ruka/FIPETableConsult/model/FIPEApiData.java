package dev.ruka.FIPETableConsult.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FIPEApiData (@JsonAlias("codigo") String code, @JsonAlias("nome") String name){
}
