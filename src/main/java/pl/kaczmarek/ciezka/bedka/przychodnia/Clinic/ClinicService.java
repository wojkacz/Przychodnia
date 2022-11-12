package pl.kaczmarek.ciezka.bedka.przychodnia.Clinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {
    ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getClinics(){
        return clinicRepository.findAll();
    }

    public Boolean doesClinicExist(Long clinicID) {
        return clinicRepository.findById(clinicID).isPresent();
    }

    public Clinic getClinicByID(Long clinicID){
        Optional<Clinic> c = clinicRepository.findById(clinicID);
        if(c.isEmpty()) return null;
        return c.get();
    }
}
