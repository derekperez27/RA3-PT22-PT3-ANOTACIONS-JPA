package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {

    private String studentCode;

    public Student() {}

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
