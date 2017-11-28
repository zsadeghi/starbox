package me.theyinspire.starbox.web.controllers;

import me.theyinspire.starbox.db.entities.Location;
import me.theyinspire.starbox.db.entities.Offering;
import me.theyinspire.starbox.db.entities.OfferingPrice;
import me.theyinspire.starbox.db.entities.OfferingType;
import me.theyinspire.starbox.db.repositories.LocationRepository;
import me.theyinspire.starbox.db.repositories.OfferingPriceRepository;
import me.theyinspire.starbox.db.repositories.OfferingRepository;
import me.theyinspire.starbox.db.repositories.OfferingTypeRepository;
import me.theyinspire.starbox.web.dto.Menu;
import me.theyinspire.starbox.web.dto.MenuCategory;
import me.theyinspire.starbox.web.dto.MenuItem;
import me.theyinspire.starbox.web.dto.SizeDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:56 PM)
 */
@RestController
@RequestMapping("/api/rest/v1/menu")
public class MenuController {

    private final OfferingRepository offeringRepository;
    private final OfferingTypeRepository offeringTypeRepository;
    private final LocationRepository locationRepository;
    private final OfferingPriceRepository offeringPriceRepository;

    public MenuController(OfferingRepository offeringRepository, OfferingTypeRepository offeringTypeRepository, LocationRepository locationRepository, OfferingPriceRepository offeringPriceRepository) {
        this.offeringRepository = offeringRepository;
        this.offeringTypeRepository = offeringTypeRepository;
        this.locationRepository = locationRepository;
        this.offeringPriceRepository = offeringPriceRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"", "/"})
    public Menu getGeneralMenu() {
        final List<MenuCategory> categories = new ArrayList<>();
        final Menu menu = new Menu();
        menu.setCategories(categories);
        final Iterable<OfferingType> types = offeringTypeRepository.findAll();
        for (OfferingType type : types) {
            final MenuCategory category = new MenuCategory();
            categories.add(category);
            category.setName(type.getName());
            category.setDescription(type.getDescription());
            final List<MenuItem> items = new ArrayList<>();
            category.setItems(items);
            final List<Offering> offerings = offeringRepository.findDistinctByOfferingType(type);
            for (Offering offering : offerings) {
                final MenuItem item = new MenuItem();
                item.setName(offering.getName());
                items.add(item);
            }
        }
        return menu;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{locationId}", "/{locationId}/"})
    public Menu getLocationMenu(@PathVariable("locationId") Integer locationId) {
        final Location location = locationRepository.findOne(locationId);
        final List<MenuCategory> categories = new ArrayList<>();
        final Menu menu = new Menu();
        menu.setCategories(categories);
        final Iterable<OfferingType> types = offeringTypeRepository.findAll();
        for (OfferingType type : types) {
            final List<MenuItem> items = new ArrayList<>();
            final MenuCategory category = new MenuCategory();
            category.setName(type.getName());
            category.setItems(items);
            category.setDescription(type.getDescription());
            final Iterable<OfferingPrice> prices = offeringPriceRepository.findByLocationsContainingAndOfferingOfferingType(location, type);
            final Set<Offering> offerings = new HashSet<>();
            for (OfferingPrice price : prices) {
                final Offering offering = price.getOffering();
                offerings.add(offering);
            }
            for (Offering offering : offerings) {
                final Iterable<OfferingPrice> offeringPrices = offeringPriceRepository.findByOffering(offering);
                final MenuItem item = new MenuItem();
                item.setName(offering.getName());
                item.setSizes(new ArrayList<>());
                for (OfferingPrice price : offeringPrices) {
                    final SizeDto dto = new SizeDto();
                    dto.setName(price.getOfferingSize().getName());
                    dto.setOz(price.getOfferingSize().getOz());
                    dto.setPrice(price.getPrice());
                    item.getSizes().add(dto);
                }
                if (!item.getSizes().isEmpty()) {
                    items.add(item);
                }
            }
            if (!category.getItems().isEmpty()) {
                categories.add(category);
            }
        }
        return menu;
    }

}
