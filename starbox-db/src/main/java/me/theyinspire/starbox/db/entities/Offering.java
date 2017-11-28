package me.theyinspire.starbox.db.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 6:52 PM)
 */
@Entity
@Table(name = "Offering")
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "offeringTypeId", nullable = false)
    private OfferingType offeringType;
    @ManyToMany
    @JoinTable(
            name = "OfferingTag",
            joinColumns = @JoinColumn(name = "offeringId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tagId", referencedColumnName = "id")
    )
    private Set<Tag> tags;
    @ManyToMany
    @JoinTable(
            name = "OfferingIngredient",
            joinColumns = @JoinColumn(name = "offeringId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId", referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients;
    @OneToMany
    private Set<OfferingPrice> prices;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferingType getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(OfferingType offeringType) {
        this.offeringType = offeringType;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<OfferingPrice> getPrices() {
        return prices;
    }

    public void setPrices(Set<OfferingPrice> prices) {
        this.prices = prices;
    }
}
