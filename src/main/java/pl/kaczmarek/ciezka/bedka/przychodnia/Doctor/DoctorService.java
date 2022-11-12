package pl.kaczmarek.ciezka.bedka.przychodnia.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Boolean doesDoctorExist(Long doctorID) {
        return doctorRepository.findById(doctorID).isPresent();
    }

    public Doctor getDoctorByID(Long doctorID){
        Optional<Doctor> d = doctorRepository.findById(doctorID);
        if(d.isEmpty()) return null;
        return d.get();
    }
}
