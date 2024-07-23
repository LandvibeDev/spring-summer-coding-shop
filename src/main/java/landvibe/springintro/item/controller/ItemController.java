package landvibe.springintro.item.controller;

import landvibe.springintro.item.domain.Item;
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
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    //회원등록

    @GetMapping("/members/new")
    public String createMemberForm(){
        return "members/createMemberForm";
    }
    @PostMapping("members/new")
    public String create(@ModelAttribute MemberForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getId());
        item.setCount(form.getPwd());
        itemService.create(item);
        return "redirect:/";
    }
    @GetMapping("/members")
    public String memberList(Model model){
        List<Item> members = itemService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

//상품등록
    @GetMapping("/items/new")
    public String createItemForm(){
        return "items/createForm";
    }
    @PostMapping("/items/new")
    public String create(@ModelAttribute ItemCreateForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setCount(form.getCount());
        itemService.create(item);
        return "redirect:/";
    }
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

}
