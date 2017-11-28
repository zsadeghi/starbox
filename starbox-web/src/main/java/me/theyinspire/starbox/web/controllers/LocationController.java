package me.theyinspire.starbox.web.controllers;

import me.theyinspire.starbox.db.entities.Location;
import me.theyinspire.starbox.db.repositories.LocationRepository;
import me.theyinspire.starbox.web.dto.LocationDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 10:58 PM)
 */
@RestController
@RequestMapping("/api/rest/v1/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}", "/{id}/"})
    public LocationDto getLocation(@PathVariable("id") Integer id) {
        final Location location = locationRepository.findOne(id);
        final LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setAddress(location.getAddress());
        dto.setPhone(location.getPhone());
        return dto;
    }

}
