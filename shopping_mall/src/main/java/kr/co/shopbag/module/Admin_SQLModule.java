package kr.co.shopbag.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.devices.FileProcessor;
import kr.co.shopbag.devices.LoginStatusCaller;
import kr.co.shopbag.devices.PassEncoder;
import kr.co.shopbag.dto.DTO_Admin;
import kr.co.shopbag.dto.DTO_Agree;
import kr.co.shopbag.dto.DTO_Cate;
import kr.co.shopbag.dto.DTO_Notice;
import kr.co.shopbag.dto.DTO_Product;
import kr.co.shopbag.dto.DTO_Site;

@Repository("sql_module")
public class Admin_SQLModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	@Resource(name="session_call")
	private LoginStatusCaller lsc;
	
	@Resource(name="md5_encoder")
	private PassEncoder pe;
	
	@Resource(name="file_process")
	private FileProcessor fp;
	
	
	public String searchData(String ad_id){
		String ad = sst.selectOne("shop_project.adminCtn", ad_id);
		return ad;
	}
		
	public int addAdmin(DTO_Admin dto) {
		String encresult = pe.encodePass(dto.getAd_pass());
		int result = 0;
		if(encresult=="ERROR") {
			result = -1;
		}
		else {
			dto.setAd_pass(encresult);
			result = sst.insert("shop_project.addAdmin", dto);
		}
		return result;
	}
	
	
	public List<DTO_Admin> selAdminList(){
		List<DTO_Admin> li = new ArrayList<DTO_Admin>();
		li = sst.selectList("shop_project.adminAll");
		return li;
	}
	
	public int updateAdmin(String ad_idx, String ad_approve) {
		int result = 0;
		//System.out.println("modeule " + ad_approve);
		if(ad_approve.equals("Y")) {
			result = sst.update("shop_project.addAdminApprove", ad_idx);
		}else if(ad_approve.equals("N")){
			result = sst.update("shop_project.delAdminApprove", ad_idx);
		}
		else {
			result = -1;
		}
		return result;
	}
	
	public Map<String, Object> loginAdmin(String ad_id, String ad_pass) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String responseScript = "";
		String sessionCondition = "";
		String ad_name = ""; 
		
		String ad_passEncoded = pe.encodePass(ad_pass);
		
		DTO_Admin dto = sst.selectOne("shop_project.adminSearch", ad_id);
		try {
			if (!ad_passEncoded.equals(dto.getAd_pass())) {
				responseScript = "<script>"
						+ "alert('비밀번호 또는 아이디가 일치하지 않습니다');"
						+ "window.reload();"
						+ "</script>";
				sessionCondition ="N";
			}
			else if (dto.getAd_approve().equals("N")) {
				responseScript = "<script>"
						+ "alert('" + dto.getAd_name() + "님 현재 로그인할 수 없습니다. 권한을 받을 때까지 기다려 주십시오');"
						+ "window.reload();"
						+ "</script>";
				sessionCondition ="N";
			}
			else {
				responseScript = "<script>"
						+ "alert('" + dto.getAd_name() + "님 환영합니다.');"
						+ "location.href = '/admin/admin_main.do';"
						+ "</script>";
				sessionCondition ="Y";
				ad_name = dto.getAd_name();
			}
			
		} catch (Exception e) {
			responseScript = "<script>"
					+ "alert('존재하지않은 아이디입니다');"
					+ "window.reload();"
					+ "</script>";
			sessionCondition ="N";
		} finally {
			resultMap.put("responseScript", responseScript);
			resultMap.put("sessionCondition", sessionCondition);
			resultMap.put("ad_name", ad_name);
		}
		return resultMap;
	}
	
	public int addSite(DTO_Site dto) {
		int result = sst.insert("shop_project.addSite", dto);
		return result;
	}
	
	public List<DTO_Cate> selCateList(HttpServletRequest req){
		List<DTO_Cate> li = new ArrayList<DTO_Cate>();
		Map<String, String> sessionData = lsc.statusCall(req);
		li = sst.selectList("shop_project.cateAll", sessionData.get("activeLoginID"));
		return li;
	}
	
	public int addCate(DTO_Cate dto, HttpServletRequest req) {
		Map<String, String> sessionData = lsc.statusCall(req);
		dto.setCa_adminid(sessionData.get("activeLoginID"));
		int result = sst.insert("shop_project.addCate", dto);
		return result;
	}
	
	public int delCate(String[] caIdxList) {
		int result = sst.delete("shop_project.delCate",caIdxList);
		return result;
	}
	
	public String selProductCtn(String pr_code){
		String ad = sst.selectOne("shop_project.productCtn", pr_code);
		return ad;
	}
	
	public int addProduct(DTO_Product dto, HttpServletRequest req) throws Exception {
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		dto.setPr_adminid(activeID);
		
		Map<String,String> fileData1 = fp.fileProcess(dto.getPr_file1(), req);
		Map<String,String> fileData2 = fp.fileProcess(dto.getPr_file2(), req);
		Map<String,String> fileData3 = fp.fileProcess(dto.getPr_file3(), req);
		
		dto.setPr_file1name(fileData1.get("filename"));
		dto.setPr_file1url(fileData1.get("fileurl"));
		dto.setPr_file2name(fileData2.get("filename"));
		dto.setPr_file2url(fileData2.get("fileurl"));
		dto.setPr_file3name(fileData3.get("filename"));
		dto.setPr_file3url(fileData3.get("fileurl"));
		
		//site_cate 테이블 pd_exists 업데이트
		String ca_name = dto.getPr_caname();
		Map<String, String> selectMap = new HashMap<>();
		selectMap.put("ca_name", ca_name);
		selectMap.put("activeID", activeID);
		int updateCondition = sst.selectOne("shop_project.productCondition", selectMap);
		if (updateCondition==0) {
			int updateResult = sst.update("shop_project.cateUpdateExists", selectMap);
			//System.out.println(updateResult);
		}
		
		int result = sst.insert("shop_project.addProduct", dto);
		return result;
	}
	
	public List<DTO_Product> selProductList(HttpServletRequest req){
		List<DTO_Product> li = new ArrayList<DTO_Product>();
		Map<String, String> sessionData = lsc.statusCall(req);
		li = sst.selectList("shop_project.productAll", sessionData.get("activeLoginID"));
		return li;
	}
	
	public int delProd(String[] prIdxList, String[] caNameList, HttpServletRequest req) {
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		
		int result = sst.delete("shop_project.delProd",prIdxList);
		
		//해당 카테고리 안에 상품 없으면 업데이트
		for (String ca_name : caNameList) {
			Map<String, String> selectMap = new HashMap<>();
			selectMap.put("ca_name", ca_name);
			selectMap.put("activeID", activeID);
			int updateCondition = sst.selectOne("shop_project.productCondition", selectMap);
			//System.out.println(updateCondition);
			if (updateCondition==0) {
				int updateResult = sst.update("shop_project.cateUpdateNotExists", selectMap);
				//System.out.println(updateResult);
			}
		}
		return result;
		//return 0;
	}
	
	public Map<String, String> callActiveUser (HttpServletRequest req){
		Map<String, String> sessionData = lsc.statusCall(req);
		return sessionData;
	}
	
	public int addNotice(DTO_Notice dto, HttpServletRequest req) throws Exception{
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		dto.setAn_adminid(activeID);
		
		Map<String,String> fileData = fp.fileProcess(dto.getAn_file(), req);
		dto.setAn_filename(fileData.get("filename"));
		dto.setAn_fileurl(fileData.get("fileurl"));
		
		int result = sst.insert("shop_project.addNotice", dto);
		return result;
	}
	
	public List<DTO_Notice> selNoticeList (HttpServletRequest req) {
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		List<DTO_Notice> li = sst.selectList("shop_project.noticeAll",activeID);
		return li;
	}
	
	public int delNotice(String[] caIdxList) {
		int result = sst.delete("shop_project.delNotice",caIdxList);
		return result;
	}
	
	public List<DTO_Notice> selNoticeView (String an_idx) {
		List<DTO_Notice> li = sst.selectList("shop_project.noticeView",an_idx);
		//System.out.println(li);
		return li;
	}
	
	public int updateNotice (DTO_Notice dto, HttpServletRequest req) throws Exception {
		if(dto.getAn_file()!=null) {
			Map<String,String> fileData = fp.fileProcess(dto.getAn_file(), req);
			System.out.println(fileData.get("filename")+ "test");
			dto.setAn_filename(fileData.get("filename"));
			dto.setAn_fileurl(fileData.get("fileurl"));
		}
		
		int result = sst.update("shop_project.noticeUpdate",dto);
		return result;
	}
	
	public int updateNoticeCount (String an_idx) {
		int result = sst.update("shop_project.noticeCountUpdate",an_idx);
		return result;
	}
	
	public List<DTO_Agree> selAgreeView (HttpServletRequest req) {
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		List<DTO_Agree> li = sst.selectList("shop_project.agreeAll",activeID);
		return li;
	}
}
