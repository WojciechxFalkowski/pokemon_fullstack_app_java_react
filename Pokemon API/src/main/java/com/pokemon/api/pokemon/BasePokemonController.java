package com.pokemon.api.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/base-pokemons")
public class BasePokemonController {

    private final BasePokemonService basePokemonService;

    @Autowired
    public BasePokemonController(BasePokemonService basePokemonService) {
        this.basePokemonService = basePokemonService;
    }

    @PostMapping
    public ResponseEntity<BasePokemon> createBasePokemon(@RequestBody BasePokemon basePokemon) {
        BasePokemon savedPokemon = basePokemonService.createBasePokemon(basePokemon);
        return new ResponseEntity<>(savedPokemon, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<BasePokemon>> createBasePokemons(@RequestBody List<BasePokemon> basePokemons) {
        List<BasePokemon> savedPokemons = basePokemonService.createBasePokemons(basePokemons);
        return new ResponseEntity<>(savedPokemons, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BasePokemon>> getAllBasePokemons() {
        List<BasePokemon> pokemons = basePokemonService.getAllBasePokemons();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasePokemon> getBasePokemonById(@PathVariable Long id) {
        Optional<BasePokemon> basePokemon = basePokemonService.getBasePokemonById(id);
        return basePokemon.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasePokemon> updateBasePokemon(@PathVariable Long id, @RequestBody BasePokemon updatedPokemon) {
        BasePokemon pokemon = basePokemonService.updateBasePokemon(id, updatedPokemon);
        return (pokemon != null) ? ResponseEntity.ok(pokemon) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasePokemon(@PathVariable Long id) {
        basePokemonService.deleteBasePokemon(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchPokemonByName(@RequestParam String name) {
        Optional<BasePokemon> localPokemon = basePokemonService.findPokemonByName(name);

        if (localPokemon.isPresent()) {
            return new ResponseEntity<>(localPokemon.get(), HttpStatus.OK);
        } else {
            Map<String, Object> externalPokemonData = basePokemonService.fetchPokemonFromExternalApi(name);
            if (externalPokemonData.containsKey("error")) {
                return new ResponseEntity<>(externalPokemonData, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(externalPokemonData, HttpStatus.OK);
            }
        }
    }
}
