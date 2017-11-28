package me.theyinspire.starbox.db.repositories;

import me.theyinspire.starbox.db.entities.Location;
import me.theyinspire.starbox.db.entities.Offering;
import me.theyinspire.starbox.db.entities.OfferingPrice;
import me.theyinspire.starbox.db.entities.OfferingType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 8:11 PM)
 */
public interface OfferingPriceRepository extends CrudRepository<OfferingPrice, Integer> {

    Iterable<OfferingPrice> findByLocationsContainingAndOfferingOfferingType(Location location, OfferingType offeringType);

    Iterable<OfferingPrice> findByOffering(Offering offering);

}
