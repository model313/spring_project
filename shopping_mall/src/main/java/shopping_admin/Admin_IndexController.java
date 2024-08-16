package shopping_admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Admin_IndexController {
	@Resource(name="session_inspect")
	private LoginStatusInspector lsi;
	
	PrintWriter pw = null;
	
	
	@GetMapping("/admin/add_master")
	public String addMasterPage() {
		return "/admin/add_master";
	}
	
	@GetMapping("/admin/index")
	public String indexPage() {
		return "/admin/index";
	}
	
	//Session 확인 > 로그인 아닌 상태 차단 (admin_top.jsp)
	@GetMapping("/admin/login_status_ck")
	public String login_status_ck(HttpServletRequest req, HttpServletResponse res) {
		boolean loginStatusCondition = lsi.statusInspect(req);
		try {
			this.pw = res.getWriter();
			if(loginStatusCondition==false) {
				this.pw.print("0");
			}
			else {
				this.pw.print("1");
			}
		} catch (Exception e) {
			this.pw.print("-1");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/admin/admin_list")
	public String adminListPage(HttpServletRequest req, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./admin_list.do';"
				+ "</script>");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/admin_siteinfo")
	public String adminSiteInfoPage() {
		return "/admin/admin_siteinfo";
	}
	
	@Autowired
	private HttpSession session;
	@GetMapping("/admin/admin_logout")
	public String adminLogout(String id, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			session.invalidate();
			this.pw.print("<script>"
					+ "alert('로그아웃 하셨습니다');"
					+ "location.href='./index';"
					+ "</script>");
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('서버 문제로 인해 로그아웃 처리 못했습니다');"
					+ "</script>");
		}
		return null;
	}
	
	@GetMapping("/admin/cate_list")
	public String cateListPage(HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./cate_list.do';"
				+ "</script>");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/cate_write")
	public String cateWritePage(HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./cate_write.do';"
				+ "</script>");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/product_write")
	public String productWritePage(HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./product_write.do';"
				+ "</script>");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/product_list")
	public String productListPage(HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "location.href='./product_list.do';"
				+ "</script>");
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/notice_write")
	public String noticeWritePage() {
		return "/admin/notice_write";
	}
	
}
