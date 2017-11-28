package me.theyinspire.starbox.db.repositories;

import me.theyinspire.starbox.db.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 9:19 PM)
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    List<Ingredient> findByNameLike(String name);

}
