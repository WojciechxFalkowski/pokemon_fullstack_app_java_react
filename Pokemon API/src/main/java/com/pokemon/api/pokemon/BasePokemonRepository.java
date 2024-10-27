package com.pokemon.api.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasePokemonRepository extends JpaRepository<BasePokemon, Long> {
    boolean existsByName(String name);
    Optional<BasePokemon> findByName(String name);
}
