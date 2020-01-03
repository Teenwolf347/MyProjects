package org.exampleSpringData.entity;

import javax.persistence.*;

@Entity
@Table
public class Passport {
    @Id
    private Integer series;
    private Integer number;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "passport")
    private Person person;

    public Passport() {}

    public Passport(Integer series, Integer number) {
        this.series = series;
        this.number = number;
    }

    public Integer getSeries() {
        return series;
    }
    public void setSeries(Integer series) {
        this.series = series;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return series + ":" + number + " " ;
    }
}
