package kr.co.shopbag.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.shopbag.dto.DTO_Notice;
import kr.co.shopbag.module.Admin_NoticeModule;

@Controller
public class Admin_NoticeController {
	@Resource(name="admin_notice_module")
	private Admin_NoticeModule sm;
	
	PrintWriter pw = null;

	@GetMapping("/admin/notice_list")
	public String noticeList(Model m, HttpServletRequest req) {
		List<DTO_Notice> li = sm.selNoticeList();
		m.addAttribute("resultList",li);
		m.addAttribute("listSize",li.size());
		return "/admin/notice_list";
	}

	@GetMapping("/admin/notice_write")
	public String noticeWrite(Model m, HttpServletRequest req) {
		Map<String, String> activeUserInfo = sm.callActiveUser(req);
		m.addAttribute("activeLoginUser",activeUserInfo.get("activeLoginUser"));
		return "/admin/notice_write";
	}
	
	@PostMapping("/admin/notice_add")
	public String noticeAdd(@ModelAttribute("noticedto") DTO_Notice dto, HttpServletResponse res, HttpServletRequest req) {
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
	public String noticeView(String an_idx, Model m) {
		List<DTO_Notice> li = sm.selNoticeView(an_idx);
		m.addAttribute("resultList",li);
		
		int result = sm.updateNoticeCount(an_idx);
		return "/admin/notice_view";
	}
	
	@GetMapping("/admin/notice_mod")
	public String noticeModify(String an_idx, Model m) {
		List<DTO_Notice> li = sm.selNoticeView(an_idx);
		m.addAttribute("resultList",li);
		return "/admin/notice_mod";
	}
	
	@PostMapping("/admin/notice_update")
	public String noticeUpdate (@ModelAttribute("noticedto") DTO_Notice dto, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		if(dto.getAn_filename()==null) {
			dto.setAn_filename("");
			dto.setAn_fileurl("");
		}
		
		try {
			this.pw = res.getWriter();
			int callback = sm.updateNotice(dto, req);
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
}
