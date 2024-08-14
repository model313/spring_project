package shopping_admin;

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
		
	public int addAdmin(Admin_DTO dto) {
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
	
	
	public List<Admin_DTO> selAdminList(){
		List<Admin_DTO> li = new ArrayList<Admin_DTO>();
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
		
		Admin_DTO dto = sst.selectOne("shop_project.adminSearch", ad_id);
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
	
	public int addSite(Site_DTO dto) {
		int result = sst.insert("shop_project.addSite", dto);
		return result;
	}
	
	public List<Cate_DTO> selCateList(HttpServletRequest req){
		List<Cate_DTO> li = new ArrayList<Cate_DTO>();
		Map<String, String> sessionData = lsc.statusCall(req);
		li = sst.selectList("shop_project.cateAll", sessionData.get("activeLoginID"));
		return li;
	}
	
	public int addCate(Cate_DTO dto, HttpServletRequest req) {
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
	
	public int addProduct(Product_DTO dto, HttpServletRequest req) throws Exception {
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
		int updateCondition = sst.selectOne("shop_project.productCondition", ca_name);
		if (updateCondition==0) {
			int updateResult = sst.update("shop_project.cateUpdateExists", ca_name);
			System.out.println(updateResult);
		}
		
		int result = sst.insert("shop_project.addProduct", dto);
		return result;
	}
	
	public List<Product_DTO> selProductList(HttpServletRequest req){
		List<Product_DTO> li = new ArrayList<Product_DTO>();
		Map<String, String> sessionData = lsc.statusCall(req);
		li = sst.selectList("shop_project.productAll", sessionData.get("activeLoginID"));
		return li;
	}
	
	public int delProd(String[] prIdxList, HttpServletRequest req) {
		Map<String, String> sessionData = lsc.statusCall(req);
		String activeID = sessionData.get("activeLoginID");
		//dto.setPr_adminid(activeID);
		
		
		int result = sst.delete("shop_project.delProd",prIdxList);
		
		/*
		int updateCondition = sst.selectOne("shop_project.productCondition", activeID);
		if (updateCondition==0) {
			String ca_name = dto.getPr_caname();
			int updateResult = sst.update("shop_project.cateUpdateExists", ca_name);
			System.out.println(updateResult);
		}
		*/
		return result;
	}
	
	
}
