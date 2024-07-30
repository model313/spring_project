package shopping_admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("admin_module")
public class Admin_SQLModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	public String searchData(String ad_id){
		String ad = sst.selectOne("shop_project.adminCtn", ad_id);
		return ad;
	}
	
	@Resource(name="MD5Encoder")
	private PassEncoder pe;
	
	public int addAdmin(Admin_DTO dto) {
		String encresult = pe.encodePass(dto.getAd_pass());
		int result = 0;
		//System.out.println(encresult);
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
		System.out.println("modeule " + ad_approve);
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
