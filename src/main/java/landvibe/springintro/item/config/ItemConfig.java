package landvibe.springintro.item.config;


import landvibe.springintro.item.repository.ItemRepository;
import landvibe.springintro.item.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {

    private final ItemRepository itemRepository;

    public ItemConfig(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Bean
    public ItemService itemService(){
        return new ItemService(itemRepository);
    }
    /*    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public ItemConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }*/
/*    @Bean
    public ItemRepository itemRepository(){
        //return new MemoryItemRepository();
        //return new JdbcItemRepository(dataSource);
        //return new JdbcTemplateItemRepository(dataSource);
        return new JpaItemRepository(em);
    }*/
}
