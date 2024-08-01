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
	
	@GetMapping("/admin/admin_list")
	public String adminListPage(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		boolean loginStatusCondition = lsi.statusInspect(req);
		//System.out.println(loginStatusCondition);
		try {
			this.pw = res.getWriter();
			if(loginStatusCondition==false) {
				this.pw.print("<script>"
						+ "alert('로그인 하셔야 해당 페이지 접속 가능합니다');"
						+ "location.href='./index';"
						+ "</script>");
			}
			else {
				this.pw.print("<script>"
						+ "location.href='./admin_list.do';"
						+ "</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('서버 문제로 인해 해당 페이지 접속 가능합니다');"
					+ "location.href='./index';"
					+ "</script>");
		} finally {
			this.pw.close();
		}
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
	
}
