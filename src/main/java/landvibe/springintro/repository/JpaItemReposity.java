package landvibe.springintro.repository;
import jakarta.persistence.EntityManager;
import landvibe.springintro.domain.Item;
import java.util.List;
import java.util.Optional;

public class JpaItemReposity implements ItemRepository {
    private final EntityManager em;
    public JpaItemReposity(EntityManager em) {
        this.em = em;
    }
    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }
    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }
    @Override
    public Optional<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name = :name",
                        Item.class)
                .setParameter("name", name)
                .getResultStream()
                .findAny();
    }
    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
