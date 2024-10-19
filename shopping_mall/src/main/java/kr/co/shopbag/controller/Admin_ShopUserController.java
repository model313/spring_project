package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.shopbag.dto.DTO_Agree;
import kr.co.shopbag.dto.DTO_User;
import kr.co.shopbag.module.Admin_ShopUserModule;

@Controller
public class Admin_ShopUserController {
	@Resource (name="admin_shopuser_module")
	private Admin_ShopUserModule sm;
	
	@Autowired
	private HttpSession session;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/shop_member_list")
	public String shopMemberList (Model m, HttpServletRequest req) {
		List<DTO_User> userList = sm.selUserView();
		List<DTO_Agree> agreeList = sm.selAgreeView();
		
		m.addAttribute("userListResults",userList);
		m.addAttribute("userListSize",userList.size());
		m.addAttribute("agreeListResults",agreeList);
		m.addAttribute("agreeListSize",agreeList.size());
		return "/admin/shop_member_list";
	}
	
}
