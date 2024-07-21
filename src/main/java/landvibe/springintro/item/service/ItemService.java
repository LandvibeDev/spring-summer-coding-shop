package landvibe.springintro.item.service;

import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.ItemRepository;
import landvibe.springintro.item.repository.MemoryItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Long create(Item item){
        validateDuplicationItem(item.getName());
        repository.save(item);
        return item.getId();
    }

    public List<Item> findItems(){
        return repository.findAll();
    }

    private void validateDuplicationItem(String itemName){
        repository.findByName(itemName)
                .ifPresent(item -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
}
