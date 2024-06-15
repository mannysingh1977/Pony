package be.ucll.pony.repository;

import be.ucll.pony.model.Pony;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbIntializer {

    @Autowired
    private PonyRepository ponyRepository;

    public DbIntializer(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    @PostConstruct
    public void initialize() {
        ponyRepository.save(new Pony("Bella", 20));
        ponyRepository.save(new Pony("Luna", 10));
        ponyRepository.save(new Pony("Muriel", 2));
        ponyRepository.save(new Pony("Little", 1));
    }
}
