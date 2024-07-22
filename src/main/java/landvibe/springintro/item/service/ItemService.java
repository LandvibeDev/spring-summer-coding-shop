package landvibe.springintro.item.service;
import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.ItemRepository;
import landvibe.springintro.item.repository.MemoryItemRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class ItemService {
    private final ItemRepository repository;
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }
    /**
     * 상품 등록
     */
    public Long create(Item item) {
        validateDuplicateItem(item.getName());
        repository.save(item);
        return item.getId();
    }
    /**
     * 상품 전체조회
     */
    public List<Item> findItems() {
        return repository.findAll();
    }
    private void validateDuplicateItem(String itemName) {
        repository.findByName(itemName)
                .ifPresent(i -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
}