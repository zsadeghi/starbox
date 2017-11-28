package me.theyinspire.starbox.web.dto;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 9:35 PM)
 */
public class SearchResultItem {

    private Integer id;
    private String name;
    private String description;
    private Integer locations;

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

    public Integer getLocations() {
        return locations;
    }

    public void setLocations(Integer locations) {
        this.locations = locations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
