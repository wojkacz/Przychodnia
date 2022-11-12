package pl.kaczmarek.ciezka.bedka.przychodnia.Clinic;

import org.json.JSONObject;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicID;
    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "clinic")
    private List<Registration> registrations;

    public Clinic() {
    }

    public Clinic(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "[Address: " + address + "][Phone: " + phoneNumber + "]";
    }

    public JSONObject getJSONObject(){
        return new JSONObject()
            .put("address", address)
            .put("phoneNumber", phoneNumber);
    }

    public Long getClinicID() {
        return clinicID;
    }

    public void setClinicID(Long clinicID) {
        this.clinicID = clinicID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
