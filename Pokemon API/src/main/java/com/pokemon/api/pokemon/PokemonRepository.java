package com.pokemon.api.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    // Możesz dodać dodatkowe metody wyszukiwania, jeśli będą potrzebne
}
