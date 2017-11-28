package me.theyinspire.starbox.db.repositories;

import me.theyinspire.starbox.db.entities.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 9:19 PM)
 */
public interface TagRepository extends CrudRepository<Tag, Integer> {

    List<Tag> findByNameLike(String name);

}
