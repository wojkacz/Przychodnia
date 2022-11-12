package pl.kaczmarek.ciezka.bedka.przychodnia.Registration;

import org.json.JSONObject;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.Clinic;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Table
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationID;

    @ManyToOne
    @JoinColumn(name="doctorID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="pesel")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="visitDateID")
    private VisitDate visitDate;

    @ManyToOne
    @JoinColumn(name="clinicID")
    private Clinic clinic;

    private Boolean cancelled;

    public Registration() {
    }

    public Registration(Doctor doctor, Patient patient, VisitDate visitDate, Clinic clinic) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
        this.clinic = clinic;
        cancelled = false;
    }

    @Override
    public String toString() {
        return patient.toString() + visitDate.toString() + clinic.toString() + doctor.toString() + "[Cancelled: " + cancelled + "]";
    }

    public JSONObject getJSONObject(){
        return new JSONObject()
            .put("registrationID", registrationID)
            .put("doctorName", doctor.getPersonData().getName())
            .put("doctorSurname", doctor.getPersonData().getSurname())
            .put("doctorSpecialization", doctor.getSpecialization().getSpecName())
            .put("visitDate", visitDate.getDate())
            .put("visitTime", visitDate.getTime())
            .put("clinicAddress", clinic.getAddress())
            .put("clinicPhoneNumber", clinic.getPhoneNumber());

    }

    public Long getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(Long registrationID) {
        this.registrationID = registrationID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
