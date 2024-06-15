package be.ucll.pony.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "MY_ANIMALS")
public class Pony {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Age must be a positive integer between 1 and 50")
    @Max(value = 50, message = "Age must be a positive integer between 1 and 50")
    private int age;

    public Pony(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Pony() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
