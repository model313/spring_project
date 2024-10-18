package shopping;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import shopping_admin.Admin_SQLModule;

@Controller
public class Site_SQLController {
	
	@Resource (name="site_sql_module")
	private Site_SQLModule ssm;
	
	
}
