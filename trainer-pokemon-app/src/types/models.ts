export interface Pokemon {
    id: number;
    name: string;
    type: string;
    strength: number;
    speed: number;
    defense: number;
    stamina: number;
    level: number;
}

export interface Trainer {
    id: number;
    firstName: string;
    lastName: string;
    exp: number;
    pokemons: Pokemon[];
}