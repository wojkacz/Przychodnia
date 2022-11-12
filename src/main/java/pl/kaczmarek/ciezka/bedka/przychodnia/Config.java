package pl.kaczmarek.ciezka.bedka.przychodnia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.Clinic;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.ClinicRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.DoctorRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.PatientRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonDataRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.RegistrationRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.Specialization.Specialization;
import pl.kaczmarek.ciezka.bedka.przychodnia.Specialization.SpecializationRepository;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDate;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDateRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class Config {
    @Bean
    CommandLineRunner commandLineRunner(ClinicRepository clinicRepository, PersonDataRepository personDataRepository, SpecializationRepository specializationRepository, DoctorRepository doctorRepository, VisitDateRepository visitDateRepository, RegistrationRepository registrationRepository, PatientRepository patientRepository) {
        return args -> {
            // ======== Dummy data ========

            // VisitDates
            LocalDate date = LocalDate.now().plusDays(1);
            LocalDate nextMonth = date.plusMonths(1);
            while (date.isBefore(nextMonth)) {
                if( date.getDayOfWeek() != DayOfWeek.SATURDAY &&
                    date.getDayOfWeek() != DayOfWeek.SUNDAY) {

                    LocalTime time = LocalTime.of(8,0);
                    LocalTime finishTime = LocalTime.of(16,1);
                    while (time.isBefore(finishTime)){
                        VisitDate vd = new VisitDate(date, time);
                        visitDateRepository.saveAndFlush(vd);
                        Logger.logVisitDate(vd);
                        time = time.plusMinutes(30);
                    }
                }
                date = date.plusDays(1);
            }

            // Clinics
            Clinic c1 = new Clinic("Szybka 1", "600111222");
            Clinic c2 = new Clinic("Wolna 13", "334213523");
            clinicRepository.saveAllAndFlush(List.of(c1, c2));
            Logger.logClinic(c1);
            Logger.logClinic(c2);

            // Doctors
            PersonData pd1 = new PersonData("Jan", "Kowalski");
            PersonData pd2 = new PersonData("Adam", "Nowak");
            PersonData pd3 = new PersonData("Jarek", "Szybki");

            pd1 = personDataRepository.saveAndFlush(pd1);
            Logger.logPersonData(pd1);

            pd2 = personDataRepository.saveAndFlush(pd2);
            Logger.logPersonData(pd2);

            pd3 = personDataRepository.saveAndFlush(pd3);
            Logger.logPersonData(pd3);

            Specialization s1 = new Specialization("Kardiologia");
            Specialization s2 = new Specialization("Stomatologia");

            s1 = specializationRepository.saveAndFlush(s1);
            Logger.logSpecialization(s1);

            s2 = specializationRepository.saveAndFlush(s2);
            Logger.logSpecialization(s2);

            Doctor d1 = new Doctor(pd1, s1, 2);
            Doctor d2 = new Doctor(pd2, s2, 6);
            Doctor d3 = new Doctor(pd3, s1, 9);
            doctorRepository.saveAllAndFlush(List.of(d1, d2, d3));
            Logger.logDoctor(d1);
            Logger.logDoctor(d2);
            Logger.logDoctor(d3);

            // Patients
            PersonData pd4 = new PersonData("Krzysztof", "Poniedzialek");
            pd4 = personDataRepository.saveAndFlush(pd4);
            Logger.logPersonData(pd4);

            Patient p1 = new Patient("11111111111", pd4);
            p1 = patientRepository.saveAndFlush(p1);
            Logger.logPatient(p1);

            // Visits
            VisitDate vd1 = visitDateRepository.findById(2L).get();
            VisitDate vd2 = visitDateRepository.findById(3L).get();
            Registration r1 = new Registration(d1, p1, vd1, c1);
            Registration r2 = new Registration(d2, p1, vd2, c2);
            registrationRepository.saveAllAndFlush(List.of(r1, r2));
            Logger.logRegistration(r1);
            Logger.logRegistration(r2);
        };
    }
}
