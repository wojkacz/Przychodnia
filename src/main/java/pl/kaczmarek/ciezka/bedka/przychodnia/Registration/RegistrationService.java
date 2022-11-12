package pl.kaczmarek.ciezka.bedka.przychodnia.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.Clinic;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Logger;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDate;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Optional<Registration> getRegistrationByID(Long registrationID){
        return registrationRepository.findById(registrationID);
    }

    public List<Registration> getPatientRegistrations(String pesel) {
        return registrationRepository.findByPatientPesel(pesel);
    }

    public Long registerNewVisit(Doctor doctor, Patient patient, VisitDate visitDate, Clinic clinic) {
        Registration registration = new Registration(doctor, patient, visitDate, clinic);
        Logger.logRegistration(registration);
        return registrationRepository.saveAndFlush(registration).getRegistrationID();
    }

    public void cancelVisit(Registration registration){
        registration.setCancelled(true);
        Logger.logCancellation(registration);
        registrationRepository.saveAndFlush(registration);
    }
}
