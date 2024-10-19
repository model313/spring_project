package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.shopbag.dto.DTO_Site;
import kr.co.shopbag.module.Admin_SiteInfoModule;

@Controller
public class Admin_SiteInfoController {
	@Resource (name="admin_siteinfo_module")
	private Admin_SiteInfoModule sm;
	
	@Autowired
	private HttpSession session;
	
	PrintWriter pw = null;
	
	// 사이트 환경설정 뷰
	@GetMapping("/admin/site_info")
	public String siteInfoPage(Model m) {
		List<DTO_Site> resultList = sm.selSite();
		m.addAttribute("resultList",resultList);
		return "/admin/site_info";
	}
	
	// 사이트 정보 수정 페이지 (데이터 출력)
	@GetMapping("/admin/site_mod")
	public String siteModPage(Model m) {
		List<DTO_Site> resultList = sm.selSite();
		m.addAttribute("resultList",resultList);
		return "/admin/site_mod";
	}
	
	// 사이트 정보 수정
	@PostMapping("/admin/site_update")
	public String siteAdd(@ModelAttribute("sitedto") DTO_Site dto, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		
		String activeLoginID = (String) session.getAttribute("activeLoginID");
		dto.setSi_adminid(activeLoginID);
		
		try {
			this.pw = res.getWriter();
			int callback = sm.modSite(dto);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 수정 완료 되었습니다');"
						+ "location.href='/admin/site_info';"
						+ "</script>");
			}
		} catch (Exception e) {
			//e.printStackTrace();
			this.pw.print("<script>"
					+ "alert('DB 오류로 인하여 등록되지 않았습니다');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		return null;
	}
}
