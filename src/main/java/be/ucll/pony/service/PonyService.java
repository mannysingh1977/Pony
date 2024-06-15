package be.ucll.pony.service;

import be.ucll.pony.model.Pony;
import be.ucll.pony.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PonyService {
    private PonyRepository ponyRepository;

    public PonyService(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    public Pony addPony(Pony pony) {

        if(pony.getName() == null || pony.getName().isEmpty()) {
            throw new ServiceException("ServiceException:", "Name is required");
        }

        if(ponyRepository.findByName(pony.getName()).isPresent()) {
            throw new ServiceException("ServiceException:", "This name is already in the database");
        }

        return ponyRepository.save(pony);
    }

    public List<Pony> findAllAnimals() {
        return ponyRepository.findAll();
    }

    public List<Pony> getAllAnimalsOlderThan(int age) {
        if(age < 1 || age > 50) {
            throw new ServiceException("ServiceException:", "Age must be between 1 and 50");
        }

        return ponyRepository.findByAgeGreaterThan(age);
    }

    public List<Pony> getOldestAnimal() {
        return ponyRepository.findOldestAnimal();
    }
}
