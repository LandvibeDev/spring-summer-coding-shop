package landvibe.springintro.item.repository;

import landvibe.springintro.item.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryItemRepository implements ItemRepository {
    private static Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    @Override
    public Optional<Item> findById(Long id) {
        Item item = store.get(id);
        return Optional.ofNullable(item);
    }
    @Override
    public Optional<Item> findByName(String name) {
        return store.values().stream()
                .filter(item -> item.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
