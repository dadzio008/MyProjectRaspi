package com.example.myprojectraspi.model;


import javax.persistence.*;


@Entity
@Table(name = "Shades")
public class ShadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id1;
    private String id;
    private String name;
    private Integer addressOpen;
    private Integer addressClose;
    private String provider = "pigpio-digital-output";

    public ShadeEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAddressOpen() {
        return addressOpen;
    }

    public void setAddress(Integer addressOpen) {
        this.addressOpen = addressOpen;
    }

    public Integer getAddressClose(){
        return addressClose;
    }
    public void setAddressClose(Integer addressClose){
        this.addressClose = addressClose;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public ShadeEntity(Long id1, String id, String name, Integer addressOpen, Integer addressClose, String provider) {
        this.id1 = id1;
        this.id = id;
        this.name = name;
        this.addressOpen = addressOpen;
        this.addressClose = addressClose;
        this.provider = provider;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId1() {
        return id1;
    }
}
