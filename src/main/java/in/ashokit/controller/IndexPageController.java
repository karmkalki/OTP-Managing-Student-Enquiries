package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {
	/*to display or home page*/
@GetMapping("/")
	public String indexPage() {
	return "index";
}
	
}
