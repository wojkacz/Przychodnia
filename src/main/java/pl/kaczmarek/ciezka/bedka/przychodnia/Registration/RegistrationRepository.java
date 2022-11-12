package pl.kaczmarek.ciezka.bedka.przychodnia.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.patient.pesel = ?1 and r.patient.personData.name = ?2 and r.patient.personData.surname = ?3")
    List<Registration> findByPatientData(String pesel, String name, String surname);

    @Query("SELECT r FROM Registration r WHERE r.patient.pesel = ?1")
    List<Registration> findByPatientPesel(String pesel);
}
