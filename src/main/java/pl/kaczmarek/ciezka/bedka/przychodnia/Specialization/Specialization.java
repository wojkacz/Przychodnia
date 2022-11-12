package pl.kaczmarek.ciezka.bedka.przychodnia.Specialization;

import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specializationID;
    private String specName;

    @OneToMany(mappedBy = "specialization")
    private List<Doctor> doctors;

    @Override
    public String toString() {
        return "[SpecName: " + specName + "]";
    }

    public Specialization(String name) {
        this.specName = name;
    }

    public Specialization() {}

    public Long getspecializationID() {
        return specializationID;
    }

    public void setspecializationID(Long specializationID) {
        this.specializationID = specializationID;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
