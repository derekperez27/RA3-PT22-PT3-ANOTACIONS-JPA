package es.ilerna.M0486.ra3.pt22.anotacions.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends Person {

    private String teacherCode;

    public Teacher() {}

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
}
