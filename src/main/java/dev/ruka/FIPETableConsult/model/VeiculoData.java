package dev.ruka.FIPETableConsult.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VeiculoData(
    @JsonAlias("Marca") String marca, 
    @JsonAlias("Modelo") String modelo, 
    @JsonAlias("AnoModelo") String ano, 
    @JsonAlias("Combustivel") String combustivel, 
    @JsonAlias("CodigoFipe") String codigoFipe, 
    @JsonAlias("Valor") String valor) {
    
}
