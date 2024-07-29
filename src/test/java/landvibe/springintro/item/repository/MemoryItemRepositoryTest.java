package landvibe.springintro.item.repository;

import landvibe.springintro.item.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryItemRepositoryTest {

    MemoryItemRepository repository = new MemoryItemRepository();

    @AfterEach
    void afterEach(){
        repository.clearStore();
    }

    @Test
    void save(){

        Item item = createItem("과자", 10, 1000);

        repository.save(item);

        Item foundItem = repository.findById(item.getId()).get();
        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void findByName(){

        Item 눈을감자 = createItem("눈을감자", 10, 1000);
        Item 프링글스 = createItem("프링글스", 10, 1000);
        repository.save(눈을감자);
        repository.save(프링글스);

        Item foundItem = repository.findByName("눈을감자").get();

        assertThat(foundItem).isEqualTo(눈을감자);
    }

    @Test
    void findAll(){

        Item 눈을감자 = createItem("눈을감자", 10, 1000);
        Item 프링글스 = createItem("프링글스", 10, 1000);
        repository.save(눈을감자);
        repository.save(프링글스);

        List<Item> foundItems = repository.findAll();
        assertThat(foundItems).contains(눈을감자, 프링글스);
    }

    private static Item createItem(String name, int price, int count) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setCount(count);
        return item;
    }
}