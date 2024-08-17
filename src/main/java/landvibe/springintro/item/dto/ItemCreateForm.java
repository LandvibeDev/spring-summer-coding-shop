package landvibe.springintro.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateForm {
    private String name;
    private Integer price;
    private Integer count;

}