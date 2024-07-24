package landvibe.springintro.item;

import landvibe.springintro.repository.ItemRepository;
import landvibe.springintro.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {
   /* private final DataSource dataSource;

    private final EntityManager em;

    @Autowired
    public ItemConfig(DataSource dataSource,EntityManager em) {
        this.dataSource = dataSource;
        this.em=em;
    }*/
    private final ItemRepository itemRepository;
    public ItemConfig(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository);
    }
   /* @Bean
    public ItemRepository itemRepository() {
        return new JpaItemReposity(em);
    }*/
}