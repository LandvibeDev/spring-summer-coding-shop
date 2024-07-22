package landvibe.springintro.item.repository;
import landvibe.springintro.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaItemRepository extends JpaRepository<Item,Long>, ItemRepository{
    @Override
    Optional<Item> findByName(String name);
}
