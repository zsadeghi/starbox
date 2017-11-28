package me.theyinspire.starbox.web.controllers;

import me.theyinspire.starbox.db.entities.*;
import me.theyinspire.starbox.db.repositories.IngredientRepository;
import me.theyinspire.starbox.db.repositories.OfferingPriceRepository;
import me.theyinspire.starbox.db.repositories.OfferingRepository;
import me.theyinspire.starbox.db.repositories.TagRepository;
import me.theyinspire.starbox.web.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (11/27/17, 9:16 PM)
 */
@RestController
@RequestMapping("/api/rest/v1/offerings")
public class OfferingController {

    private final OfferingRepository offeringRepository;
    private final OfferingPriceRepository offeringPriceRepository;
    private final TagRepository tagRepository;
    private final IngredientRepository ingredientRepository;

    public OfferingController(OfferingRepository offeringRepository, OfferingPriceRepository offeringPriceRepository, TagRepository tagRepository, IngredientRepository ingredientRepository) {
        this.offeringRepository = offeringRepository;
        this.offeringPriceRepository = offeringPriceRepository;
        this.tagRepository = tagRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/search", "/search/"})
    public List<SearchResultItem> search(@RequestBody SearchRequest request) {
        if (request.getName() == null) {
            request.setName("");
        }
        if (request.getType() == null) {
            request.setType("");
        }
        if (request.getIngredients() == null) {
            request.setIngredients(new ArrayList<>());
        }
        if (request.getTags() == null) {
            request.setTags(new ArrayList<>());
        }
        request.setName("%" + request.getName() + "%");
        request.setType("%" + request.getType() + "%");
        final List<Ingredient> ingredients = new ArrayList<>();
        final List<String> ingredientNames = request.getIngredients();
        for (String ingredientName : ingredientNames) {
            ingredients.addAll(ingredientRepository.findByNameLike(ingredientName));
        }
        final List<Tag> tags = new ArrayList<>();
        final List<String> tagNames = request.getTags();
        for (String tagName : tagNames) {
            tags.addAll(tagRepository.findByNameLike(tagName));
        }
        final List<Offering> offerings;
        if (SearchType.ALL.equals(request.getSearchType())) {
            if (!request.getTags().isEmpty() && !request.getIngredients().isEmpty()) {
                offerings = offeringRepository.findDistinctByNameLikeAndOfferingTypeNameLikeAndTagsContainsAndIngredientsIsIn(request.getName(), request.getType(), tags, ingredients);
            } else if (request.getTags().isEmpty() && !request.getIngredients().isEmpty()) {
                offerings = offeringRepository.findDistinctByNameLikeAndOfferingTypeNameLikeAndIngredientsIsIn(request.getName(), request.getType(), ingredients);
            } else if (!request.getTags().isEmpty() && request.getIngredients().isEmpty()) {
                offerings = offeringRepository.findDistinctByNameLikeAndOfferingTypeNameLikeAndTagsIsIn(request.getName(), request.getType(), tags);
            } else {
                offerings = offeringRepository.findDistinctByNameLikeAndOfferingTypeNameLike(request.getName(), request.getType());
            }
        } else {
            if (!request.getName().equals("%%") && !request.getType().equals("%%")) {
                offerings = offeringRepository.findDistinctByNameLikeOrOfferingTypeNameLikeOrTagsIsInOrIngredientsIsIn(request.getName(), request.getType(), tags, ingredients);
            } else if (request.getName().equals("%%") && !request.getType().equals("%%")) {
                offerings = offeringRepository.findDistinctByOfferingTypeNameLikeOrTagsIsInOrIngredientsIsIn(request.getType(), tags, ingredients);
            } else if (!request.getName().equals("%%") && request.getType().equals("%%")) {
                offerings = offeringRepository.findDistinctByNameLikeOrTagsIsInOrIngredientsIsIn(request.getName(), tags, ingredients);
            } else {
                offerings = offeringRepository.findDistinctByTagsIsInOrIngredientsIsIn(tags, ingredients);
            }
        }
        final List<SearchResultItem> list = new ArrayList<>();
        for (Offering offering : offerings) {
            final Iterable<OfferingPrice> prices = offeringPriceRepository.findByOffering(offering);
            final Set<Integer> locations = new HashSet<>();
            for (OfferingPrice price : prices) {
                locations.addAll(price.getLocations().stream().map(Location::getId).collect(Collectors.toSet()));
            }
            final SearchResultItem item = new SearchResultItem();
            item.setId(offering.getId());
            item.setName(offering.getName());
            item.setDescription(offering.getDescription());
            item.setLocations(locations.size());
            list.add(item);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/{id}", "/{id}/"})
    public OfferingDto getOne(@PathVariable("id") Integer id) {
        final Offering offering = offeringRepository.findOne(id);
        final Iterable<OfferingPrice> offeringPrices = offeringPriceRepository.findByOffering(offering);
        final Set<Location> locations = new HashSet<>();
        for (OfferingPrice offeringPrice : offeringPrices) {
            locations.addAll(offeringPrice.getLocations());
        }
        final OfferingDto dto = new OfferingDto();
        dto.setName(offering.getName());
        dto.setDescription(offering.getDescription());
        dto.setType(offering.getOfferingType().getName());
        dto.setIngredients(new ArrayList<>());
        dto.setTags(new ArrayList<>());
        dto.setLocations(new ArrayList<>());
        for (Location location : locations) {
            final LocationDto locationDto = new LocationDto();
            locationDto.setId(location.getId());
            locationDto.setName(location.getName());
            locationDto.setAddress(location.getAddress());
            locationDto.setPhone(location.getPhone());
            dto.getLocations().add(locationDto);
        }
        for (Tag tag : offering.getTags()) {
            final TagDto tagDto = new TagDto();
            tagDto.setId(tag.getId());
            tagDto.setName(tag.getName());
            dto.getTags().add(tagDto);
        }
        for (Ingredient ingredient : offering.getIngredients()) {
            final IngredientDto ingredientDto = new IngredientDto();
            ingredientDto.setId(ingredient.getId());
            ingredientDto.setName(ingredient.getName());
            dto.getIngredients().add(ingredientDto);
        }
        return dto;
    }

}
