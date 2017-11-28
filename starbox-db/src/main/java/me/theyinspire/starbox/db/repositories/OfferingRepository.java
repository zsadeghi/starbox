package me.theyinspire.starbox.db.repositories;

import me.theyinspire.starbox.db.entities.Ingredient;
import me.theyinspire.starbox.db.entities.Offering;
import me.theyinspire.starbox.db.entities.OfferingType;
import me.theyinspire.starbox.db.entities.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 7:22 PM)
 */
public interface OfferingRepository extends CrudRepository<Offering, Integer> {

    List<Offering> findDistinctByOfferingType(OfferingType offeringType);

    List<Offering> findDistinctByNameLikeAndOfferingTypeNameLikeAndTagsContainsAndIngredientsIsIn(String name, String offeringTypeName, List<Tag> tags, List<Ingredient> ingredients);

    List<Offering> findDistinctByNameLikeAndOfferingTypeNameLikeAndIngredientsIsIn(String name, String offeringTypeName, List<Ingredient> ingredients);

    List<Offering> findDistinctByNameLikeAndOfferingTypeNameLikeAndTagsIsIn(String name, String offeringTypeName, List<Tag> tags);

    List<Offering> findDistinctByNameLikeAndOfferingTypeNameLike(String name, String offeringTypeName);

    List<Offering> findDistinctByNameLikeOrOfferingTypeNameLikeOrTagsIsInOrIngredientsIsIn(String name, String offeringTypeName, List<Tag> tags, List<Ingredient> ingredients);

    List<Offering> findDistinctByOfferingTypeNameLikeOrTagsIsInOrIngredientsIsIn(String offeringTypeName, List<Tag> tags, List<Ingredient> ingredients);

    List<Offering> findDistinctByNameLikeOrTagsIsInOrIngredientsIsIn(String name, List<Tag> tags, List<Ingredient> ingredients);

    List<Offering> findDistinctByTagsIsInOrIngredientsIsIn(List<Tag> tags, List<Ingredient> ingredients);

}
