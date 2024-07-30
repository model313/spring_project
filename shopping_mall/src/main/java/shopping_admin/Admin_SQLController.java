package shopping_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Admin_SQLController {
	@Resource (name="admin_module")
	private Admin_SQLModule am;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/id_check.do")
	public String idCheck(@RequestParam(defaultValue="",required=false) String ad_id, HttpServletResponse res) {
		//System.out.println(ad_id);
		//DB 실패 + callback으로 바꾸셈
		String result = null;
		try {
			this.pw = res.getWriter();
			if(ad_id==null) {
				this.pw.print("error");
			}
			else {
				result = am.searchData(ad_id);
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
			int callback = am.addAdmin(dto);
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
	
	@GetMapping("/admin/admin_list")
	public String adminList(Model m) {
		List<Admin_DTO> li = null;
		li = am.selAdminList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/admin_list";
	}
	
	@GetMapping("/admin/admin_update")
	public String adminUpdate(String ad_idx, String ad_approve, HttpServletResponse res) {
		try {
			this.pw = res.getWriter();
			int callback = am.updateAdmin(ad_idx, ad_approve);
			this.pw.print(callback);
		} catch (Exception e) {
			this.pw.print("-1");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//로그인
	@PostMapping("/admin/ad_login_check")
	public String adLoginCheck() {
		return null;
	}
}
