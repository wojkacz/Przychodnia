package pl.kaczmarek.ciezka.bedka.przychodnia.Doctor;

import org.json.JSONObject;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;
import pl.kaczmarek.ciezka.bedka.przychodnia.Specialization.Specialization;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Table
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorID;
    private Integer yearsOfExperience;

    @OneToOne
    @JoinColumn(name = "personDataID")
    private PersonData personData;

    @ManyToOne
    @JoinColumn(name="specializationID")
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor")
    private List<Registration> registrations;

    public Doctor() {
    }

    public Doctor(PersonData personData, Specialization specialization, Integer yearsOfExperience) {
        this.personData = personData;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return personData.toString() + specialization.toString() + "[Exp: " + yearsOfExperience + "]";
    }

    public JSONObject getJSONObject(){
        return new JSONObject()
            .put("doctorID", doctorID)
            .put("name", personData.getName())
            .put("surname", personData.getSurname())
            .put("specialization", specialization.getSpecName())
            .put("yearsOfExperience", yearsOfExperience.toString());
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
