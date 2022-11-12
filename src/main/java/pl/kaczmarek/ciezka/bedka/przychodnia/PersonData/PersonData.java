package pl.kaczmarek.ciezka.bedka.przychodnia.PersonData;

import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;

import javax.persistence.*;

@Entity
@Table
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personDataID;
    private String name;
    private String surname;

    @OneToOne(mappedBy = "personData")
    private Doctor doctor;

    @OneToOne(mappedBy = "personData")
    private Patient patient;

    public PersonData() {
    }

    public PersonData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "[Name: " + name + "][Surname: " + surname + "]";
    }

    public Boolean doesDataMatch(String name, String surname) {
        return (this.name.equalsIgnoreCase(name) && this.surname.equalsIgnoreCase(surname));
    }

    public Long getPersonDataID() {
        return personDataID;
    }

    public void setPersonDataID(Long personDataID) {
        this.personDataID = personDataID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
