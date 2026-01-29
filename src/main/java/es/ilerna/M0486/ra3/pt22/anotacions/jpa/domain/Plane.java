package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plane")
public class Plane extends Vehicle {

    private Integer tailNumber;
    private Boolean autopilot;

    public Plane() {}

    public Integer getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(Integer tailNumber) {
        this.tailNumber = tailNumber;
    }

    public Boolean getAutopilot() {
        return autopilot;
    }

    public void setAutopilot(Boolean autopilot) {
        this.autopilot = autopilot;
    }
}
