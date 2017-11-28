package me.theyinspire.starbox.db.repositories;

import me.theyinspire.starbox.db.entities.Location;
import me.theyinspire.starbox.db.entities.OfferingPrice;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 8:11 PM)
 */
public interface LocationRepository extends CrudRepository<Location, Integer> {

    Iterable<Location> findByOfferingsContains(Iterable<OfferingPrice> offeringPrices);

}
