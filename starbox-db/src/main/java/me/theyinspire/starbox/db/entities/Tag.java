package me.theyinspire.starbox.db.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 6:53 PM)
 */
@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable(
            name = "OfferingTag",
            joinColumns = @JoinColumn(name = "tagId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "offeringId", referencedColumnName = "id")
    )
    private Set<Offering> offerings;

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
}
