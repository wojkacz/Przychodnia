package pl.kaczmarek.ciezka.bedka.przychodnia;

import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.Clinic;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;
import pl.kaczmarek.ciezka.bedka.przychodnia.Specialization.Specialization;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDate;

import java.time.LocalDate;
import java.time.LocalTime;

public class Logger {
    public static void log(String text){

        System.out.println( "[" + LocalDate.now().toString() + "]" +
                            "[" + LocalTime.now().toString() + "]" +
                            "[INFO] " + text + "\n");
    }

    public static void logDoctor(Doctor doctor){
        log("Doctor added to database " + doctor.toString());
    }

    public static void logSpecialization(Specialization specialization){
        log("Specialization added to database " + specialization.toString());
    }

    public static void logPersonData(PersonData personData){
        log("PersonData added to database " + personData.toString());
    }

    public static void logClinic(Clinic clinic){
        log("Clinic added to database " + clinic.toString());
    }

    public static void logPatient(Patient patient){
        log("Patient added to database " + patient.toString());
    }

    public static void logVisitDate(VisitDate visitDate){
        log("VisitDate added to database " + visitDate.toString());
    }

    public static void logRegistration(Registration registration){
        log("Registration added to database " + registration.toString());
    }

    public static void logCancellation(Registration registration){
        log("Visit has been cancelled " + registration.toString());
    }
}
