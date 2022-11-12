package pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistrationForm {
    private Long doctorID;
    private String patientPesel;
    private String patientName;
    private String patientSurname;
    private LocalDate date;
    private LocalTime time;
    private Long clinicID;

    public RegistrationForm(Long doctorID, String patientPesel, String patientName, String patientSurname, LocalDate date, LocalTime time, Long clinicID) {
        this.doctorID = doctorID;
        this.patientPesel = patientPesel;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.date = date;
        this.time = time;
        this.clinicID = clinicID;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientPesel() {
        return patientPesel;
    }

    public void setPatientPesel(String patientPesel) {
        this.patientPesel = patientPesel;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public Long getClinicID() {
        return clinicID;
    }

    public void setClinicID(Long clinicID) {
        this.clinicID = clinicID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
