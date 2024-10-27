import React from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store/store";

const TrainerCards: React.FC = () => {
  // Dodajemy typowanie, aby uzyskać strukturę stanu
  const { trainers, status, error } = useSelector(
    (state: RootState) => state.trainers
  );

  if (status === "loading") {
    return <p>Loading...</p>;
  }

  if (status === "failed") {
    return <p>{error}</p>;
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 p-4">
      {trainers.map((trainer) => (
        <div key={trainer.id} className="bg-white shadow-md rounded-lg p-6">
          <h2 className="text-lg font-bold">
            {trainer.firstName} {trainer.lastName}
          </h2>
          <p className="text-gray-600">Exp: {trainer.exp}</p>
          <h3 className="mt-4 font-semibold">Pokemons:</h3>
          <ul className="mt-2 space-y-2">
            {trainer.pokemons.map((pokemon) => (
              <li key={pokemon.id} className="bg-gray-100 p-2 rounded">
                <p>
                  <strong>Name:</strong> {pokemon.name}
                </p>
                <p>
                  <strong>Type:</strong> {pokemon.type}
                </p>
                <p>
                  <strong>Level:</strong> {pokemon.level}
                </p>
                <p>
                  <strong>Strength:</strong> {pokemon.strength}
                </p>
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default TrainerCards;
