package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.shopbag.dto.DTO_Admin;
import kr.co.shopbag.module.Admin_UserModule;

@Controller
public class Admin_UserController {
	@Resource(name="admin_user_module")
	private Admin_UserModule sm;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/admin_list")
	public String adminList(Model m) {
		List<DTO_Admin> li = null;
		li = sm.selAdminList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/admin_list";
	}

	@GetMapping("/admin/admin_update")
	public String adminUpdate(String ad_idx, String ad_approve, HttpServletResponse res) {
		try {
			this.pw = res.getWriter();
			int callback = sm.updateAdmin(ad_idx, ad_approve);
			this.pw.print(callback);
		} catch (Exception e) {
			this.pw.print("-1");
		} finally {
			this.pw.close();
		}
		return null;
	}
}
