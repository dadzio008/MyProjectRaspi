package com.example.myprojectraspi.model;




import javax.persistence.*;


@Entity
@Table(name = "Shades")
public class ShadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id1;
    private String id;
    private String name;
    private Integer addressOpen;
    private Integer addressClose;
    private String provider = "pigpio-digital-output";
    private Integer timeToOpenAndCloseShade;
    private Integer status = 1;
    private Integer value = 0;

    public ShadeEntity() {

    }

    public void setAddressOpen(Integer addressOpen) {
        this.addressOpen = addressOpen;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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

    public ShadeEntity(Long id1, String id, String name, Integer addressOpen, Integer addressClose, String provider, Integer timeToOpenAndCloseShade, Integer status, Integer value) {
        this.id1 = id1;
        this.id = id;
        this.name = name;
        this.addressOpen = addressOpen;
        this.addressClose = addressClose;
        this.provider = provider;
        this.timeToOpenAndCloseShade = timeToOpenAndCloseShade;
        this.status = status;
        this.value = value;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public Long getId1() {
        return id1;
    }

    public void setTimeToOpenAndCloseShade(Integer timeToOpenAndCloseShade){
        this.timeToOpenAndCloseShade = timeToOpenAndCloseShade;
    }
    public Integer getTimeToOpenAndCloseShade(){
        return timeToOpenAndCloseShade;
    }
}
