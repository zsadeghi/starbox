package me.theyinspire.starbox.web.config;

import me.theyinspire.starbox.db.repositories.*;
import me.theyinspire.starbox.web.controllers.LocationController;
import me.theyinspire.starbox.web.controllers.MenuController;
import me.theyinspire.starbox.web.controllers.OfferingController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 8:07 PM)
 */
@Configuration
public class ControllersConfiguration {

    @Bean
    public MenuController menuController(OfferingRepository offeringRepository, OfferingTypeRepository offeringTypeRepository, LocationRepository locationRepository, OfferingPriceRepository offeringPriceRepository) {
        return new MenuController(offeringRepository, offeringTypeRepository, locationRepository, offeringPriceRepository);
    }

    @Bean
    public OfferingController offeringController(OfferingRepository offeringRepository, TagRepository tagRepository, IngredientRepository ingredientRepository, OfferingPriceRepository offeringPriceRepository) {
        return new OfferingController(offeringRepository, offeringPriceRepository, tagRepository, ingredientRepository);
    }

    @Bean
    public LocationController locationController(LocationRepository locationRepository) {
        return new LocationController(locationRepository);
    }

}
