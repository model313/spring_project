package kr.co.shopbag.module;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.devices.FileProcessor;
import kr.co.shopbag.devices.LoginStatusCaller;
import kr.co.shopbag.devices.PassEncoder;
import kr.co.shopbag.dto.DTO_Admin;

@Repository("admin_login_module")
public class Admin_LoginModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	@Resource(name="session_call")
	private LoginStatusCaller lsc;
	
	@Resource(name="md5_encoder")
	private PassEncoder pe;
	
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
						+ "location.href = '/admin/login';"
						+ "</script>";
				sessionCondition ="N";
			}
			else if (dto.getAd_approve().equals("N")) {
				responseScript = "<script>"
						+ "alert('" + dto.getAd_name() + "님 현재 로그인할 수 없습니다. 권한을 받을 때까지 기다려 주십시오');"
						+ "location.href = '/admin/login';"
						+ "</script>";
				sessionCondition ="N";
			}
			else {
				responseScript = "<script>"
						+ "alert('" + dto.getAd_name() + "님 환영합니다.');"
						+ "location.href = '/admin/admin_list';"
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
	
}
