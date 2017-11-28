package me.theyinspire.starbox.web.dto;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/28/17, 12:04 AM)
 */
public class SizeDto {

    private String name;
    private Integer oz;
    private Float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOz() {
        return oz;
    }

    public void setOz(Integer oz) {
        this.oz = oz;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
