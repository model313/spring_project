package shopping_admin;

import java.io.PrintWriter;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin_IndexController {
	@GetMapping("/admin/add_master")
	public String addMasterPage() {
		return "/admin/add_master";
	}
	
	@GetMapping("/admin/index")
	public String indexPage() {
		return "/admin/index";
	}
	
}
