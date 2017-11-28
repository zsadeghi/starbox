package me.theyinspire.starbox.web.dto;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 8:00 PM)
 */
public class MenuItem {

    private String name;
    private List<SizeDto> sizes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SizeDto> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDto> sizes) {
        this.sizes = sizes;
    }

}
