package kr.co.shopbag.module;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.devices.LoginStatusCaller;
import kr.co.shopbag.devices.PassEncoder;
import kr.co.shopbag.dto.DTO_Admin;

@Repository("admin_user_module")
public class Admin_UserModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	@Resource(name="session_call")
	private LoginStatusCaller lsc;
	
	@Resource(name="md5_encoder")
	private PassEncoder pe;
	
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
	
	
}
