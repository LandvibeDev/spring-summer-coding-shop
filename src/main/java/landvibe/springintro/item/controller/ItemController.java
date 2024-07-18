package landvibe.springintro.item.controller;

import landvibe.springintro.item.domain.Item;
import landvibe.springintro.item.dto.ItemCreateForm;
import landvibe.springintro.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemController {
    private final ItemService service;
    @Autowired
    public ItemController(ItemService itemService) {
        this.service = itemService;
    }
    @GetMapping("/items/new")
    public String createForm(){
        return "items/createForm";
    }
    @PostMapping("/items/new")
    public String create(@ModelAttribute ItemCreateForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setCount(form.getCount());
        service.create(item);
        return "redirect:/";
    }
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = service.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}
