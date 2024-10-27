package com.pokemon.api.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BasePokemonService {

    private final BasePokemonRepository basePokemonRepository;
    private final RestTemplate restTemplate;

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    public BasePokemonService(BasePokemonRepository basePokemonRepository) {
        this.basePokemonRepository = basePokemonRepository;
        this.restTemplate = new RestTemplate();
    }

    public BasePokemon createBasePokemon(BasePokemon basePokemon) {
        if (basePokemonRepository.existsByName(basePokemon.getName())) {
            throw new IllegalArgumentException("Pokemon with name " + basePokemon.getName() + " already exists.");
        }
        return basePokemonRepository.save(basePokemon);
    }

    public List<BasePokemon> createBasePokemons(List<BasePokemon> basePokemons) {
        for (BasePokemon basePokemon : basePokemons) {
            if (basePokemonRepository.existsByName(basePokemon.getName())) {
                throw new IllegalArgumentException("Pokemon with name " + basePokemon.getName() + " already exists.");
            }
        }
        return basePokemonRepository.saveAll(basePokemons);
    }

    public List<BasePokemon> getAllBasePokemons() {
        return basePokemonRepository.findAll();
    }

    public Optional<BasePokemon> getBasePokemonById(Long id) {
        return basePokemonRepository.findById(id);
    }

    public BasePokemon updateBasePokemon(Long id, BasePokemon updatedPokemon) {
        return basePokemonRepository.findById(id)
                .map(basePokemon -> {
                    basePokemon.setName(updatedPokemon.getName());
                    basePokemon.setType(updatedPokemon.getType());
                    basePokemon.setStrength(updatedPokemon.getStrength());
                    basePokemon.setSpeed(updatedPokemon.getSpeed());
                    basePokemon.setDefense(updatedPokemon.getDefense());
                    basePokemon.setStamina(updatedPokemon.getStamina());
                    return basePokemonRepository.save(basePokemon);
                })
                .orElse(null);
    }

    public void deleteBasePokemon(Long id) {
        basePokemonRepository.deleteById(id);
    }

    public Optional<BasePokemon> findPokemonByName(String name) {
        return basePokemonRepository.findByName(name);
    }

    public Map<String, Object> fetchPokemonFromExternalApi(String name) {
        String url = POKEAPI_URL + name.toLowerCase();
        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return Map.of("error", "Pokémon not found in external API");
        }
    }
}
