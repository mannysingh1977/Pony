package be.ucll.pony.service;

import be.ucll.pony.model.Pony;
import be.ucll.pony.repository.*;
import org.springframework.stereotype.Service;

@Service
public class PonyService {
    private PonyRepository ponyRepository;

    public PonyService(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    public Pony addPony(Pony pony) {
        return ponyRepository.save(pony);
    }
}
