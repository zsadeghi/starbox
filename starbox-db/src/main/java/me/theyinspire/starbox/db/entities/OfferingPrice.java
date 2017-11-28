package me.theyinspire.starbox.db.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:10 PM)
 */
@Entity
@Table(name = "OfferingPrice")
public class OfferingPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "offeringId")
    private Offering offering;
    @ManyToOne
    @JoinColumn(name = "offeringSizeId")
    private OfferingSize offeringSize;
    @Column(nullable = false)
    private Float price;
    @ManyToMany
    @JoinTable(
            name = "LocationOffering",
            joinColumns = @JoinColumn(name = "offeringPriceId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "locationId", referencedColumnName = "id")
    )
    private Set<Location> locations;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offering getOffering() {
        return offering;
    }

    public void setOffering(Offering offering) {
        this.offering = offering;
    }

    public OfferingSize getOfferingSize() {
        return offeringSize;
    }

    public void setOfferingSize(OfferingSize offeringSize) {
        this.offeringSize = offeringSize;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

}
