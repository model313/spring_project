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
		//id 도 추가 dao.setattribute
		
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
	
	@GetMapping("/admin/cate_list.do")
	public String cateList(Model m, HttpServletRequest req) {
		List<Cate_DTO> li = null;
		li = sm.selCateList(req);
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/cate_list";
	}
	
	@GetMapping("/admin/cate_write.do")
	public String cateWrite(Model m, HttpServletRequest req) {
		List<Cate_DTO> li = null;
		li = sm.selCateList(req);
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/cate_write";
	}
	
	@PostMapping("/admin/cate_add")
	public String cateAdd(@ModelAttribute("catedo") Cate_DTO dto, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addCate(dto, req);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./cate_list';"
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
	
	@GetMapping("/admin/cate_delete")
	public String cateDelete(String[] caIdxList, HttpServletResponse res) throws Exception{
		int result = sm.delCate(caIdxList);
		String resultString = String.valueOf(result);
		this.pw = res.getWriter();
		this.pw.print(resultString);
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/product_write.do")
	public String productWrite(Model m, HttpServletRequest req) {
		List<Cate_DTO> cateLi = sm.selCateList(req);
		
		m.addAttribute("cateResultList",cateLi);
		m.addAttribute("cateListSize",cateLi.size());
		return "/admin/product_write";
	}
	
	@GetMapping("/admin/product_code_check")
	public String productCodeCheck(String pr_code, HttpServletResponse res) throws Exception{
		this.pw = res.getWriter();
		String result = sm.selProductCtn(pr_code); 
		this.pw.print(result);
		this.pw.close();
		return null;
	}
	
	@PostMapping("/admin/product_add")
	public String productAdd(@ModelAttribute("productdto") Product_DTO dto, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addProduct(dto, req);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./product_list';"
						+ "</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.pw.print("<script>"
					+ "alert('DB 오류로 인하여 등록되지 않았습니다');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/admin/product_list.do")
	public String productList(Model m, HttpServletRequest req) {
		List<Product_DTO> li = sm.selProductList(req);
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/product_list";
	}
	
	@GetMapping("/admin/prod_delete")
	public String prodDelete(String[] prIdxList, String[] caNameList, HttpServletResponse res, HttpServletRequest req) throws Exception{
		//System.out.println(Arrays.toString(caNameList));
		int result = sm.delProd(prIdxList,caNameList,req);
		String resultString = String.valueOf(result);
		this.pw = res.getWriter();
		this.pw.print(resultString);
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/notice_write.do")
	public String noticeWrite(Model m, HttpServletRequest req) {
		Map<String, String> activeUserInfo = sm.callActiveUser(req);
		m.addAttribute("activeLoginUser",activeUserInfo.get("activeLoginUser"));
		return "/admin/notice_write";
	}
	
	@PostMapping("/admin/notice_add")
	public String noticeAdd(@ModelAttribute("noticedto") Notice_DTO dto, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			int callback = sm.addNotice(dto, req);
			if(callback > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 등록 완료 되었습니다');"
						+ "location.href='./notice_list';"
						+ "</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.pw.print("<script>"
					+ "alert('DB 오류로 인하여 등록되지 않았습니다');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/admin/notice_list.do")
	public String noticeList(Model m, HttpServletRequest req) {
		List<Notice_DTO> li = sm.selNoticeList(req);
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/notice_list";
	}
	
	@GetMapping("/admin/notice_delete")
	public String noticeDelete(String[] anIdxList, HttpServletResponse res) throws Exception{
		int result = sm.delNotice(anIdxList);
		String resultString = String.valueOf(result);
		this.pw = res.getWriter();
		this.pw.print(resultString);
		this.pw.close();
		return null;
	}
	
	@GetMapping("/admin/notice_view")
	public String noticeView(String an_idx, Model m, HttpServletRequest req) {
		List<Notice_DTO> li = sm.selNoticeView(an_idx);
		m.addAttribute("resultList",li);
		return "/admin/notice_view";
	}

}
