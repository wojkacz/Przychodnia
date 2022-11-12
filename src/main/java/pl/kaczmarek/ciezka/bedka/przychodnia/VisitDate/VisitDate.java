package pl.kaczmarek.ciezka.bedka.przychodnia.VisitDate;

import org.json.JSONObject;
import pl.kaczmarek.ciezka.bedka.przychodnia.Registration.Registration;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Table
@Entity
public class VisitDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitDateID;
    private LocalDate date;
    private LocalTime time;

    @OneToMany(mappedBy = "visitDate")
    private List<Registration> registrations;

    public VisitDate() {
    }

    public VisitDate(LocalDate date, LocalTime time){
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[Date: " + date + "][Time: " + time + "]";
    }

    public JSONObject getJSONObject(){
        return new JSONObject()
            .put("time", time);
    }

    public boolean isDoctorOccupied(Long doctorID){
       for(Registration registration : registrations){
           if(!registration.getCancelled() && registration.getDoctor().getDoctorID().equals(doctorID)){
               return true;
           }
       }
       return false;
    }

    public Long getvisitDateID() {
        return visitDateID;
    }

    public void setvisitDateID(Long visitDateID) {
        this.visitDateID = visitDateID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
