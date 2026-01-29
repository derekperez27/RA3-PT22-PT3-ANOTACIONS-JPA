package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "motorcycle")
public class Motorcycle extends Vehicle {

    private Boolean hasSidecar;

    public Motorcycle() {}

    public Boolean getHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(Boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }
}
