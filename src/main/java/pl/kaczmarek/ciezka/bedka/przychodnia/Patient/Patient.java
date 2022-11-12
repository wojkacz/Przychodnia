package pl.kaczmarek.ciezka.bedka.przychodnia.Patient;

import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;
import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Patient {

    @Id
    private String pesel;

    @OneToOne
    @JoinColumn(name = "personDataID")
    private PersonData personData;

    @OneToMany(mappedBy = "patient")
    private List<Registration> registrations;

    public Patient() {
    }

    public Patient(String pesel, PersonData personData) {
        this.pesel = pesel;
        this.personData = personData;
    }

    @Override
    public String toString() {
        return "[Pesel: " + pesel + "]" + personData.toString();
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }
}
