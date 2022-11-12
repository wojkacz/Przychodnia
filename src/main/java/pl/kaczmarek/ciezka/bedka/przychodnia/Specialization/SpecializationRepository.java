package pl.kaczmarek.ciezka.bedka.przychodnia.Specialization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}
