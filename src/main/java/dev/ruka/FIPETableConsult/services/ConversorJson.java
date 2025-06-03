package dev.ruka.FIPETableConsult.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConversorJson implements IConversorDados{

    private ObjectMapper om = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return om.readValue(json, classe);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        
        CollectionType lista = om.getTypeFactory()
            .constructCollectionType(List.class, classe);
        
        try {
            return om.readValue(json, lista);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
