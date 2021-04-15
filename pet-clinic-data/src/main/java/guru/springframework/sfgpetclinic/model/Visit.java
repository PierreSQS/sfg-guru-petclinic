package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

public class Visit {
    private LocalDate date;
    private String reason;
    private Pet pet;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
