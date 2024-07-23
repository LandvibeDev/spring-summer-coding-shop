package landvibe.springintro.item.service;
import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberService {
    private ItemRepository repository;
    @Autowired
    public memberService(ItemRepository repository) {
        this.repository = repository;
    }


    /**
     * 회원 등록 */
    public Long create(Item item) {
        validateDuplicateItem(item.getName());
        repository.save(item);
        return item.getId();
    }
    /**
     * 회원 전체조회 */
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
