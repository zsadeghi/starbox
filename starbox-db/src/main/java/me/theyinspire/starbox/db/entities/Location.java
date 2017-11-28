package me.theyinspire.starbox.db.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 6:59 PM)
 */
@Entity
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @ManyToMany
    @JoinTable(
            name = "LocationOffering",
            joinColumns = @JoinColumn(name = "locationId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "offeringPriceId", referencedColumnName = "id")
    )
    private Set<OfferingPrice> offerings;

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

    public Set<OfferingPrice> getOfferings() {
        return offerings;
    }

    public void setOfferings(Set<OfferingPrice> offerings) {
        this.offerings = offerings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return id != null ? id.equals(location.id) : location.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
