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
    public ItemService(ItemRepository repository){
        this.repository = repository;
    }

    /**
     * 상품 등록
     */
    public Long create(Item item){
        long start = System.currentTimeMillis();
        try {
            validateDuplicateItem(item.getName());
            repository.save(item);
            return item.getId();
        } finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("create::timeMs = " + timeMs);
        }
    }
    public List<Item> findItems(){
        return repository.findAll();
    }
    public void validateDuplicateItem(String itemName){
        repository.findByName(itemName)
                .ifPresent(i->{
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
}
