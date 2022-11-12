package pl.kaczmarek.ciezka.bedka.przychodnia.PersonData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kaczmarek.ciezka.bedka.przychodnia.Logger;

@Service
public class PersonDataService {
    PersonDataRepository personDataRepository;

    @Autowired
    public PersonDataService(PersonDataRepository personDataRepository) {
        this.personDataRepository = personDataRepository;
    }

    public PersonData addPersonData(String name, String surname){
        PersonData personData = new PersonData(name, surname);
        Logger.logPersonData(personData);
        return personDataRepository.saveAndFlush(personData);
    }
}
