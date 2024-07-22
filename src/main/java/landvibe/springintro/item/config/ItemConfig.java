package landvibe.springintro.item.config;

import jakarta.persistence.EntityManager;
import landvibe.springintro.item.repository.*;
import landvibe.springintro.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ItemConfig {
    private final DataSource dataSource;
//
//    @Autowired
//    public ItemConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    public ItemConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

/*    private final ItemRepository itemRepository;
    public ItemConfig(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }*/
    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        //return new MemoryItemRepository();
        //return new JdbcItemRepository(dataSource);
        return new JdbcTemplateItemRepository(dataSource);
        //return new JpaItemRepository(em);
    }
}
