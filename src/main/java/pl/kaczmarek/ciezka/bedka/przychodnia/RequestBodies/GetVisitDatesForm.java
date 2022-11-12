package pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies;

import java.time.LocalDate;

public class GetVisitDatesForm {
    private Long doctorID;
    private LocalDate date;

    public GetVisitDatesForm(Long doctorID, LocalDate date) {
        this.doctorID = doctorID;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }
}
