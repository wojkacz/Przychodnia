package pl.kaczmarek.ciezka.bedka.przychodnia.RequestBodies;

public class GetRegistrationsForm {
    private String patientPesel;
    private String patientName;
    private String patientSurname;

    public GetRegistrationsForm(String patientPesel, String patientName, String patientSurname) {
        this.patientPesel = patientPesel;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
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
