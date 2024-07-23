package landvibe.springintro.item.repository;

import landvibe.springintro.item.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    Optional<Item> findById(Long id);

    Optional<Item> findByName(String name);

    List<Item> findAll();

}