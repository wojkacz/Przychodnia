package pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies.GetVisitDatesForm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitDateService {
    VisitDateRepository visitDateRepository;

    @Autowired
    public VisitDateService(VisitDateRepository visitDateRepository) {
        this.visitDateRepository = visitDateRepository;
    }

    public List<VisitDate> getUnoccupiedVisitDates(GetVisitDatesForm body){
        List<VisitDate> visitDates = visitDateRepository.findByDate(body.getDate());
        visitDates.removeIf(visitDate -> visitDate.isDoctorOccupied(body.getDoctorID()));
        return visitDates;
    }

    public Optional<VisitDate> getVisitDateByDateAndTime(LocalDate date, LocalTime time){
        return visitDateRepository.findByDateAndTime(date, time);
    }
}
