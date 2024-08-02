package shopping_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Admin_SQLController {
	@Resource (name="sql_module")
	private Admin_SQLModule sm;
	
	@Resource(name="session_inspect")
	private LoginStatusInspector lsi;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/id_check.do")
	public String idCheck(@RequestParam(defaultValue="",required=false) String ad_id, HttpServletResponse res) {
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
	public String adminAdd(@ModelAttribute("admindto") Admin_DTO dto, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addAdmin(dto);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./admin_main';"
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
	
	
	
	@GetMapping("/admin/admin_list.do")
	public String adminList(Model m) {
		List<Admin_DTO> li = null;
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
	
	//로그인
	@Autowired
	private HttpSession session;
	
	@PostMapping("/admin/ad_login_check")
	public String adLoginCheck(@ModelAttribute("admindto") Admin_DTO dto, HttpServletResponse res, HttpServletRequest req) {
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
	
	@PostMapping("/admin/site_add")
	public String siteAdd(@ModelAttribute("sitedto") Site_DTO dto, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addSite(dto);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./admin_siteview';"
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
	
	@PostMapping("/admin/cate_add")
	public String cateAdd(@ModelAttribute("catedo") Cate_DTO dto, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		//데이터 저장
		return null;
	}

}
