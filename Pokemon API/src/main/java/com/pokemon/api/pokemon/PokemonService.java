package com.pokemon.api.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemonById(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(Long id, Pokemon updatedPokemon) {
        return pokemonRepository.findById(id)
                .map(pokemon -> {
                    pokemon.setName(updatedPokemon.getName());
                    pokemon.setType(updatedPokemon.getType());
                    pokemon.setStrength(updatedPokemon.getStrength());
                    pokemon.setSpeed(updatedPokemon.getSpeed());
                    pokemon.setDefense(updatedPokemon.getDefense());
                    pokemon.setStamina(updatedPokemon.getStamina());
                    pokemon.setLevel(updatedPokemon.getLevel());
                    pokemon.setTrainer(updatedPokemon.getTrainer());
                    return pokemonRepository.save(pokemon);
                })
                .orElse(null);
    }

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}
