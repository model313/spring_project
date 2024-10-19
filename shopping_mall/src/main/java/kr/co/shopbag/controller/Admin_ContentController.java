package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.shopbag.dto.DTO_Cate;
import kr.co.shopbag.dto.DTO_Product;
import kr.co.shopbag.module.Admin_ContentModule;

@Controller
public class Admin_ContentController {
	@Resource(name="admin_content_module")
	private Admin_ContentModule sm;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin/cate_list")
	public String cateList(Model m, HttpServletRequest req) {
		List<DTO_Cate> li = null;
		li = sm.selCateList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/cate_list";
	}
	
	@GetMapping("/admin/cate_write")
	public String cateWrite(Model m, HttpServletRequest req) {
		List<DTO_Cate> li = null;
		li = sm.selCateList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/cate_write";
	}
	
	@PostMapping("/admin/cate_add")
	public String cateAdd(@ModelAttribute("catedo") DTO_Cate dto, HttpServletResponse res, HttpServletRequest req) {
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
	
	@GetMapping("/admin/product_list")
	public String productList(Model m, HttpServletRequest req) {
		List<DTO_Product> li = sm.selProductList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/product_list";
	}
	
	@GetMapping("/admin/product_write")
	public String productWrite(Model m, HttpServletRequest req) {
		List<DTO_Cate> cateLi = sm.selCateList();
		
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
	public String productAdd(@ModelAttribute("productdto") DTO_Product dto, HttpServletResponse res, HttpServletRequest req) {
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
	
}
