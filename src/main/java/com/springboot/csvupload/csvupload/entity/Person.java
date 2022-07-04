package com.springboot.csvupload.csvupload.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;
import com.springboot.csvupload.csvupload.utilities.PersonUtilities;
import javax.persistence.*;

@JsonIgnoreProperties(value = { "address", "idColor" })
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @CsvBindByPosition(position = 3)
    private int idColor;
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByPosition(position = 1)
    private String lastname;
    @CsvBindByPosition(position = 2)
    private String address;
    private int zipcode;
    private String city;
    private String color;

    public Person(){}

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.idColor = idColor;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
        this.color = PersonUtilities.getColor(idColor);
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.zipcode = PersonUtilities.getZipcode(address);
        this.city = PersonUtilities.getCity(address);
    }

    @JsonProperty("zipcode")
    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
