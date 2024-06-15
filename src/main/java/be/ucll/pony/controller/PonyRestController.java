package be.ucll.pony.controller;

import be.ucll.pony.model.DomainException;
import be.ucll.pony.model.Pony;
import be.ucll.pony.service.PonyService;
import be.ucll.pony.service.ServiceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.DOMException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping
    public List<Pony> GetAllAnimals() {
        return ponyService.findAllAnimals();
    }

    @GetMapping("/age/{age}")
    public List<Pony> getAnimalsOlderThan(@PathVariable int age) {
        return ponyService.getAllAnimalsOlderThan(age);
    }

    @GetMapping("/age/oldest")
    public List<Pony> getOldestAnimal() {
        return ponyService.getOldestAnimal();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainException.class)
    public Map<String, String> handleDomainExceptions(DomainException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public Map<String, String> handleServiceExceptions(ServiceException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getField(), ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

