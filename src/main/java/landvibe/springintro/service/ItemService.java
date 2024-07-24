package landvibe.springintro.service;

import jakarta.transaction.Transactional;
import landvibe.springintro.domain.Item;
import landvibe.springintro.repository.ItemRepository;

import java.util.List;
@Transactional
public class ItemService {

    private final ItemRepository repository;


    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Long create(Item item) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateItem(item.getName());
            repository.save(item);
            return item.getId();
        }finally {
            long end= System.currentTimeMillis();
            long timeMs=end-start;
            System.out.println("create::timeMs = "+ timeMs);
        }

    }



    /**
     *
     상품 전체조회
     */
    public List<Item> findItems() {

        long start = System.currentTimeMillis();
        try {
            return repository.findAll();
        }finally {
            long end= System.currentTimeMillis();
            long timeMs=end-start;
            System.out.println("create::timeMs = "+ timeMs);
        }
    }

    public List<Item> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return repository.findAll();
        }finally {
            long end= System.currentTimeMillis();
            long timeMs=end-start;
            System.out.println("create::timeMs = "+ timeMs);
        }
    }

    private void validateDuplicateItem(String itemName) {
        repository.findByName(itemName)
                .ifPresent(i -> {
                    throw new IllegalArgumentException("이미 존재하는 상품입니다.");
                });
    }
    }
