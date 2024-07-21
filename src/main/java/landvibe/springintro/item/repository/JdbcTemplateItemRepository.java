package landvibe.springintro.item.repository;

import landvibe.springintro.item.domain.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateItemRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        jdbcInsert.withTableName("item").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", item.getName());
        parameters.put("price", item.getPrice());
        parameters.put("count", item.getCount());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        item.setId(key.longValue());
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        List<Item> result = jdbcTemplate.query("select * from item where id = ?",rowMaper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Item> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Item> findAll() {
        return List.of();
    }

    private RowMapper<Item> rowMaper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setCount(rs.getInt("count"));
            return item;
        };
    }
}
