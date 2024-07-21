package landvibe.springintro.item.service;

import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.repository.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.standard.expression.Each;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    ItemService service;
    MemoryItemRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MemoryItemRepository();
        service = new ItemService(repository);
    }

    @AfterEach
    void tearDown(){
        repository.clearStore();
    }

    @Test
    public void 상품생성() throws Exception {
        Item item = createItem("눈을감자", 10, 1000);

        Long id = service.create(item);

        Item foundItem = repository.findById(id).get();
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void 중복이름_상품예외(){
        Item item = createItem("눈을감자", 10, 1000);
        Item duplicationItem = createItem("눈을감자", 10, 1000);
        service.create(item);

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> service.create(duplicationItem));
        assertThat(ex.getMessage()).isEqualTo("이미 존재하는 상품입니다.");
    }

    private static Item createItem(String name, int count, int price) {
        Item item = new Item();
        item.setName(name);
        item.setCount(count);
        item.setPrice(price);
        return item;
    }

}