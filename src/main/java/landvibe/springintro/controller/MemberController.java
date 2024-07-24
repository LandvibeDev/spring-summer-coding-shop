package landvibe.springintro.controller;

import landvibe.springintro.domain.Item;
import landvibe.springintro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class MemberController {

    private final ItemService itemService;

    @Autowired
    public MemberController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }



    @PostMapping("/members/new")
    public String create(@ModelAttribute MemberCreateForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setName(form.getId());
        item.setPw(form.getPw());
        itemService.create(item);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberlist(Model model){
        List<Item> members = itemService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
