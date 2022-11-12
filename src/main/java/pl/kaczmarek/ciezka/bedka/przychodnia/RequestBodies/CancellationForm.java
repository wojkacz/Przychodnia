package pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies;

public class CancellationForm {
    private Long registrationID;
    private String patientPesel;
    private String patientName;
    private String patientSurname;

    public CancellationForm(Long registrationID, String patientPesel, String patientName, String patientSurname) {
        this.registrationID = registrationID;
        this.patientPesel = patientPesel;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
    }

    public Long getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(Long registrationID) {
        this.registrationID = registrationID;
    }

    public String getPatientPesel() {
        return patientPesel;
    }

    public void setPatientPesel(String patientPesel) {
        this.patientPesel = patientPesel;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }
}
