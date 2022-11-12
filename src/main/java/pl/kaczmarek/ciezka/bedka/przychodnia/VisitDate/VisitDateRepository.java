package pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitDateRepository extends JpaRepository<VisitDate, Long> {
    @Query("SELECT v FROM VisitDate v WHERE v.date = ?1")
    List<VisitDate> findByDate(LocalDate date);

    @Query("SELECT v FROM VisitDate v WHERE v.date = ?1 and v.time = ?2")
    Optional<VisitDate> findByDateAndTime(LocalDate date, LocalTime time);
}
