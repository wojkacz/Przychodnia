package pl.kaczmarek.ciezka.bedka.przychodnia.Specialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecializationService {
    SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }
}
