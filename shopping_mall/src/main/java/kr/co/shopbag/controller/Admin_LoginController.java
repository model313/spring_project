package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.shopbag.devices.LoginStatusInspector;
import kr.co.shopbag.dto.DTO_Admin;
import kr.co.shopbag.module.Admin_LoginModule;

@Controller
public class Admin_LoginController {
	@Resource (name="admin_login_module")
	private Admin_LoginModule sm;
	
	@Resource(name="session_inspect")
	private LoginStatusInspector lsi;
	
	@Autowired
	private HttpSession session;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/login")
	public String indexPage() {
		return "/admin/index";
	}
	
	@PostMapping("/admin/ad_login_check")
	public String adLoginCheck(@ModelAttribute("admindto") DTO_Admin dto, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		Map<String, Object> callbackMap = sm.loginAdmin(dto.getAd_id(), dto.getAd_pass());
		try {
			this.pw = res.getWriter();
			session = req.getSession();
			if(callbackMap.get("sessionCondition")=="Y") {
				session.setAttribute("activeLoginID", dto.getAd_id());
				session.setAttribute("activeLoginUser", callbackMap.get("ad_name")); //ㅋㅋ
				session.setMaxInactiveInterval(20*60); //20분
			}
			this.pw.print(callbackMap.get("responseScript"));
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('서버 오류로 인해 현재 로그인이 불가능합니다');"
					+ "window.reload();"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/admin/add_master")
	public String addMasterPage() {
		return "/admin/add_master";
	}
	
	@GetMapping("/admin/id_check.do")
	public String idCheck(@RequestParam(defaultValue="",required=false) String ad_id, HttpServletResponse res) {
		String test2 = null;
		String result = null;
		try {
			this.pw = res.getWriter();
			if(ad_id==null) {
				this.pw.print("error");
			}
			else {
				result = sm.searchData(ad_id);
				this.pw.print(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.pw.print("error2");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@PostMapping("/admin/admin_add")
	public String adminAdd(@ModelAttribute("admindto") DTO_Admin dto, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addAdmin(dto);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./login';"
						+ "</script>");
			}
			else if(callback==-1){
				this.pw.print("<script>"
						+ "alert('패스워드 보안 문제로 인해 등록 되지않았습니다');"
						+ "history.go(-1);"
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
	
	@GetMapping("/admin/login_status_ck")
	public String login_status_ck(HttpServletRequest req, HttpServletResponse res) throws Exception {
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

	@GetMapping("/admin/admin_logout")
	public String adminLogout(String id, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			session.invalidate();
			this.pw.print("<script>"
					+ "alert('로그아웃 하셨습니다');"
					+ "location.href='./login';"
					+ "</script>");
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('서버 문제로 인해 로그아웃 처리 못했습니다');"
					+ "</script>");
		}
		return null;
	}
	
	
}
