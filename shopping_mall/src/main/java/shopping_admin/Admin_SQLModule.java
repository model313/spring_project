package shopping_admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("sql_module")
public class Admin_SQLModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	public String searchData(String ad_id){
		String ad = sst.selectOne("shop_project.adminCtn", ad_id);
		return ad;
	}
	
	@Resource(name="md5_encoder")
	private PassEncoder pe;
	
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
	
	
}
