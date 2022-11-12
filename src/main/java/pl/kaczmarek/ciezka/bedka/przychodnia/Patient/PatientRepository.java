package pl.kaczmarek.ciezka.bedka.przychodnia.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
