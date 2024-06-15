package be.ucll.pony.repository;

import be.ucll.pony.model.Pony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PonyRepository extends JpaRepository<Pony, Long> {
    Optional<Pony> findByName(String name);

    List<Pony> findByAgeGreaterThan(int age);

    @Query("select p from Pony p order by p.age desc limit 1")
    List<Pony> findOldestAnimal();
}
