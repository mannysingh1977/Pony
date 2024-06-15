package be.ucll.pony.controller;

import be.ucll.pony.model.Pony;
import be.ucll.pony.service.PonyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class PonyRestController {
    private PonyService ponyService;

    @Autowired
    public PonyRestController(PonyService ponyService) {
        this.ponyService = ponyService;
    }

    @PostMapping
    public Pony postAnimal(@Valid @RequestBody Pony pony) {
        return ponyService.addPony(pony);
    }
}

