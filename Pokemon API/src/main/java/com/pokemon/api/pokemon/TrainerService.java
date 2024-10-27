package com.pokemon.api.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    public Trainer createTrainer(Trainer trainer) {
        Optional<Trainer> existingTrainer = trainerRepository.findByFirstNameAndLastName(trainer.getFirstName(), trainer.getLastName());
        if (existingTrainer.isPresent()) {
            throw new IllegalArgumentException("Trainer with this first and last name already exists.");
        }
        return trainerRepository.save(trainer);
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        return trainerRepository.findById(id)
                .map(trainer -> {
                    trainer.setFirstName(updatedTrainer.getFirstName());
                    trainer.setLastName(updatedTrainer.getLastName());
                    trainer.setExp(updatedTrainer.getExp());
                    return trainerRepository.save(trainer);
                })
                .orElse(null);
    }

    public void deleteTrainer(Long id) {
        trainerRepository.deleteById(id);
    }
}
