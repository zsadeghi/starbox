package me.theyinspire.starbox.db.entities;

import javax.persistence.*;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:08 PM)
 */
@Entity
@Table(name = "OfferingSize")
public class OfferingSize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer oz;

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

    public Integer getOz() {
        return oz;
    }

    public void setOz(Integer oz) {
        this.oz = oz;
    }
}
