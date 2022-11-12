package pl.kaczmarek.ciezka.bedka.przychodnia.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmarek.ciezka.bedka.przychodnia.Logger;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;

import java.util.Optional;

@Service
public class PatientService {
    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Boolean doesPatientExist(String pesel){
        return patientRepository.findById(pesel).isPresent();
    }

    public Boolean doesPatientExistAndMatchData(String pesel, String name, String surname){
        Optional<Patient> p = patientRepository.findById(pesel);
        if(p.isEmpty()) return false;
        PersonData patientPersonData = p.get().getPersonData();
        return patientPersonData.doesDataMatch(name, surname);
    }

    public Patient getPatientByPesel(String pesel){
        Optional<Patient> p = patientRepository.findById(pesel);
        if(p.isEmpty()) return null;
        return p.get();
    }

    public Patient addNewPatient(String pesel, PersonData personData){
        Patient patient = new Patient(pesel, personData);
        Logger.logPatient(patient);
        return patientRepository.saveAndFlush(patient);
    }
}
