package pl.kaczmarek.ciezka.bedka.przychodnia;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.Clinic;
import pl.kaczmarek.ciezka.bedka.przychodnia.Clinic.ClinicService;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.Doctor;
import pl.kaczmarek.ciezka.bedka.przychodnia.Doctor.DoctorService;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.Patient;
import pl.kaczmarek.ciezka.bedka.przychodnia.Patient.PatientService;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonData;
import pl.kaczmarek.ciezka.bedka.przychodnia.PersonData.PersonDataService;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.RegistrationService;
import pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies.CancellationForm;
import pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies.GetRegistrationsForm;
import pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies.GetVisitDatesForm;
import pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies.RegistrationForm;
import pl.kaczmarek.ciezka.bedka.przychodnia.Specialization.SpecializationService;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDate;
import pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate.VisitDateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class Controller {

    ClinicService clinicService;
    DoctorService doctorService;
    PatientService patientService;
    PersonDataService personDataService;
    RegistrationService registrationService;
    SpecializationService specializationService;
    VisitDateService visitDateService;

    @Autowired
    public Controller(ClinicService clinicService, DoctorService doctorService, PatientService patientService, PersonDataService personDataService, RegistrationService registrationService, SpecializationService specializationService, VisitDateService visitDateService) {
        this.clinicService = clinicService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.personDataService = personDataService;
        this.registrationService = registrationService;
        this.specializationService = specializationService;
        this.visitDateService = visitDateService;
    }

    @GetMapping(path = "lekarze")
    public ResponseEntity<String> getDoctors(){
        List<Doctor> doctors = doctorService.getDoctors();
        JSONArray result = new JSONArray();
        for(Doctor doctor : doctors) {
            result.put(doctor.getJSONObject());
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "adresy")
    public ResponseEntity<String> getAdresses(){
        List<Clinic> clinics = clinicService.getClinics();
        JSONArray result = new JSONArray();
        for(Clinic clinic : clinics) {
            result.put(clinic.getJSONObject());
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "rejestracje")
    public ResponseEntity<String> getRegistrations(@RequestBody GetRegistrationsForm body){
        if(!patientService.doesPatientExistAndMatchData(body.getPatientPesel(), body.getPatientName(), body.getPatientSurname())){
            return new ResponseEntity<>(Constants.PATIENT_NOT_FOUND, HttpStatus.NOT_ACCEPTABLE);
        }

        List<Registration> registrations = registrationService.getPatientRegistrations(body.getPatientPesel());
        if(registrations.isEmpty()) {
            return new ResponseEntity<>(Constants.REGISTRATION_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        JSONArray result = new JSONArray();
        for(Registration registration : registrations) {
            if(!registration.getCancelled()) {
                result.put(registration.getJSONObject());
            }
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "terminy")
    public ResponseEntity<String> getVisits(@RequestBody GetVisitDatesForm body){
        if(!doctorService.doesDoctorExist(body.getDoctorID())) {
            return new ResponseEntity<>(Constants.DOCTOR_NOT_FOUND, HttpStatus.NOT_ACCEPTABLE);
        }

        List<VisitDate> visitDates = visitDateService.getUnoccupiedVisitDates(body);
        if(visitDates.isEmpty()) {
            return new ResponseEntity<>(Constants.VISIT_DATES_NOT_FOUND_FOR_DOCTOR, HttpStatus.NOT_FOUND);
        }

        JSONArray result = new JSONArray();
        for(VisitDate visitDate : visitDates) {
            result.put(visitDate.getJSONObject());
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @PostMapping(path = "rejestracja")
    public ResponseEntity<String> registerVisit(@RequestBody RegistrationForm body){

        // Check if doctor exist
        if(!doctorService.doesDoctorExist(body.getDoctorID())) {
            return new ResponseEntity<>(Constants.DOCTOR_NOT_FOUND, HttpStatus.NOT_ACCEPTABLE);
        }
        Doctor doctor = doctorService.getDoctorByID(body.getDoctorID());

        // Check if clinic exist
        if(!clinicService.doesClinicExist(body.getClinicID())) {
            return new ResponseEntity<>(Constants.CLINIC_NOT_FOUND, HttpStatus.NOT_ACCEPTABLE);
        }
        Clinic clinic = clinicService.getClinicByID(body.getClinicID());

        // Check if date and time exist
        Optional<VisitDate> optionalVisitDate = visitDateService.getVisitDateByDateAndTime(body.getDate(), body.getTime());
        if(optionalVisitDate.isEmpty()){
            return new ResponseEntity<>(Constants.INCORRECT_DATE_OR_TIME, HttpStatus.NOT_ACCEPTABLE);
        }
        VisitDate visitDate = optionalVisitDate.get();

        // Check if doctor is free at this date and time
        if(visitDate.isDoctorOccupied(doctor.getDoctorID())){
            return new ResponseEntity<>(Constants.DATE_TIME_OCCUPIED, HttpStatus.NOT_ACCEPTABLE);
        }

        // Check if patient already exist
        Patient patient;
        if(!patientService.doesPatientExistAndMatchData(body.getPatientPesel(), body.getPatientName(), body.getPatientSurname())) {
            if (patientService.doesPatientExist(body.getPatientPesel())){
                return new ResponseEntity<>(Constants.PATIENT_DATA_DOES_NOT_MATCH, HttpStatus.NOT_ACCEPTABLE);
            }

            PersonData patientPersonData = personDataService.addPersonData(body.getPatientName(), body.getPatientSurname());
            patient = patientService.addNewPatient(body.getPatientPesel(), patientPersonData);
        }
        else patient = patientService.getPatientByPesel(body.getPatientPesel());

        Long registrationID = registrationService.registerNewVisit(doctor, patient, visitDate, clinic);
        JSONObject result = new JSONObject()
                .put("registrationID", registrationID);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @PostMapping(path = "odwolanie")
    public ResponseEntity<String> cancelVisit(@RequestBody CancellationForm body){

        // Check if registration exist
        Optional<Registration> optionalRegistration = registrationService.getRegistrationByID(body.getRegistrationID());
        if(optionalRegistration.isEmpty()) {
            return new ResponseEntity<>(Constants.REGISTRATION_NOT_FOUND, HttpStatus.NOT_ACCEPTABLE);
        }
        Registration registration = optionalRegistration.get();

        // Check if visit is already cancelled
        if(registration.getCancelled()) {
            return new ResponseEntity<>(Constants.VISIT_ALREADY_CANCELLED, HttpStatus.NOT_ACCEPTABLE);
        }

        // Check if patient data match registered patient
        if( !registration.getPatient().getPesel().equals(body.getPatientPesel()) ||
            !registration.getPatient().getPersonData().doesDataMatch(body.getPatientName(), body.getPatientSurname())) {
            return new ResponseEntity<>(Constants.REGISTRATION_PATIENT_DATA_DOES_NOT_MATCH, HttpStatus.NOT_ACCEPTABLE);
        }

        registrationService.cancelVisit(registration);
        return new ResponseEntity<>(Constants.VISIT_CANCELLED, HttpStatus.OK);
    }

}
