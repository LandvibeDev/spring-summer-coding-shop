package landvibe.springintro.item.repository;

import landvibe.springintro.item.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
class MemoryItemRepositoryTest {
    MemoryItemRepository repository = new MemoryItemRepository();
    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = createItem("과자", 10, 1000);

        // when
        repository.save(item);

        // then
        Item foundItem = repository.findById(item.getId()).get();
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void findByName() {
        // given
        Item 눈을감자 = createItem("눈을감자", 10, 1000);
        Item 프링글스 = createItem("프링글스", 10, 1000);
        repository.save(눈을감자);
        repository.save(프링글스);

        // when
        Item foundItem = repository.findByName("눈을감자").get();

        // then
        assertThat(foundItem).isEqualTo(눈을감자);
    }

    @Test
    void findAll() {
        // given
        Item 눈을감자 = createItem("눈을감자", 10, 1000);
        Item 프링글스 = createItem("프링글스", 10, 1000);
        repository.save(눈을감자);
        repository.save(프링글스);

        // when
        List<Item> foundItems = repository.findAll();

        // then
        assertThat(foundItems.size()).isEqualTo(2);
        assertThat(foundItems).contains(눈을감자, 프링글스);
    }

    private static Item createItem(String name, int count, int price) {
        Item item = new Item();
        item.setName(name);
        item.setCount(count);
        item.setPrice(price);
        return item;
    }
}
