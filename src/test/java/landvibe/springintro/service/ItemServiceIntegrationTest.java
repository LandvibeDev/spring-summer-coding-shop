package landvibe.springintro.service;


import landvibe.springintro.domain.Item;
import landvibe.springintro.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional

public class ItemServiceIntegrationTest {

    @Autowired
    ItemService service;
    @Autowired
    ItemRepository repository;
    @Test
    public void 상품생성() throws Exception {
        // given
        Item item = createItem("눈을감자", 10, 1000);
        // when
        Long id = service.create(item);
        // then
        Item foundItem = repository.findById(id).get();
        assertThat(foundItem.getName()).isEqualTo(item.getName());
        assertThat(foundItem.getPrice()).isEqualTo(item.getPrice());
        assertThat(foundItem.getCount()).isEqualTo(item.getCount());
    }
    @Test
    void 중복이름_상품예외() {
        // given
        Item item = createItem("눈을감자", 10, 1000);
        Item duplicatedItem = createItem("눈을감자", 10, 1000);
        service.create(item);
        // when & then
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class,
                        () -> service.create(duplicatedItem));// 예외 발생
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


