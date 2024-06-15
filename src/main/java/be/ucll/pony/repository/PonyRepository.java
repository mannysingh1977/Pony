package be.ucll.pony.repository;

import be.ucll.pony.model.Pony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PonyRepository extends JpaRepository<Pony, Long> {

}
