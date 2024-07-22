package landvibe.springintro.item.service;
import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;
    @Autowired
    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }
    /**
     * 상품 등록 */
    public Long create(Item item) {
        validateDuplicateItem(item.getName());
        repository.save(item);

        return item.getId();
    }
    private void validateDuplicateMember(Item item) {
        repository.findByName(item.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 상품 전체조회 */
    public List<Item> findItems() {
        return repository.findAll();
    }
    private void validateDuplicateItem(String itemName) {
        repository.findByName(itemName)
                .ifPresent(i -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
    public List<Item> findMembers() {
        return repository.findAll();
    }
    private void validateDuplicateMember(String itemName) {
        repository.findByName(itemName)
                .ifPresent(i -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
}
