package dev.ruka.FIPETableConsult.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloFIPE(List<FIPEApiData> modelos) {
    
}
