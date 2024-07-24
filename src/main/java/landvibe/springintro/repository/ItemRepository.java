package landvibe.springintro.repository;

import landvibe.springintro.domain.Item;
import java.util.List;
import java.util.Optional;
public interface ItemRepository {
    Item save(Item item);
    Optional<Item> findById(Long id);
    Optional<Item> findByName(String name);
    List<Item> findAll();
}