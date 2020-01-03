package org.exampleSpringData.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table()
public class Address {
    private String site;
    private String street;
    @Id
    private Integer building;

    public Address() { }

    public Address(String site, String street, Integer building) {
        this.site = site;
        this.street = street;
        this.building = building;
    }
    @OneToMany(mappedBy="address", fetch= FetchType.LAZY)
    private List<Person> tenants;

    @Override
    public String toString() {
        return " site='" + site + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building;
    }
}
