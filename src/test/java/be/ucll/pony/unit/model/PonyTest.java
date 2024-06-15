package be.ucll.pony.unit.model;

import be.ucll.pony.model.Pony;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class PonyTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void givenAnEmptyName_WhenCreatingPony_ThenAnExceptionIsThrow() {
        Pony pony = new Pony("", 12);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals(1, violations.size());
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void givenAgeUnder1_WhenCreatingPony_ThenAnExceptionIsThrow() {
        Pony pony = new Pony("Manny", 0);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals(1, violations.size());
        assertEquals("Age must be a positive integer between 1 and 50", violations.iterator().next().getMessage());
    }

    @Test
    public void givenAgeAbove50_WhenCreatingPony_ThenAnExceptionIsThrow() {
        Pony pony = new Pony("Manny", 55);
        Set<ConstraintViolation<Pony>> violations = validator.validate(pony);
        assertEquals(1, violations.size());
        assertEquals("Age must be a positive integer between 1 and 50", violations.iterator().next().getMessage());
    }
}
