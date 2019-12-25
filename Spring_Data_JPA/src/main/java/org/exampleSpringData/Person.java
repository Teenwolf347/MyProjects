package org.exampleSpringData;

import javax.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Passport passport;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn
    private Address address;

    public Person(){}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public int getId() {
        return id;
    }
    public void setId(Integer id){this.id = id;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Address getAddress(){return address;}
    public void setAddress(Address address){
        this.address = address;
    }
    public Passport getPassport(){return passport;}
    public void setPassport(Passport passport){
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
//                " " + passport +
                '}';
    }
}
