package me.theyinspire.starbox.web.dto;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 10:40 PM)
 */
public class LocationDto {

    private Integer id;
    private String name;
    private String address;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
