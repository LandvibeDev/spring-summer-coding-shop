package landvibe.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/") //localhost:8080으로 들어오면 아래 호출
    public String home() {
        return "home"; //home.html이 호출됨
    }
}

/*
기존에 있던 index.html 말고 home.html이 보여지는 이유는
톰캣 서버에 요청이 오면 먼저 스프링 컨테이너에서 해당되는 컨트롤러가 있는지를 우선적으로 확인.
따라서, 컨트롤러가 있으므로 index.html은 호출되지 않고 종료.
 */