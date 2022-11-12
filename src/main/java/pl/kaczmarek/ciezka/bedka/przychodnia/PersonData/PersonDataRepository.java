package pl.kaczmarek.ciezka.bedka.przychodnia.PersonData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDataRepository extends JpaRepository<PersonData, Long> {
}
