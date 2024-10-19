package kr.co.shopbag.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.dto.DTO_Agree;
import kr.co.shopbag.dto.DTO_User;

@Repository("admin_shopuser_module")
public class Admin_ShopUserModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	public List<DTO_User> selUserView(){
		List<DTO_User> li = sst.selectList("shop_project.usersAll");
		return li;
	}
	
	public List<DTO_Agree> selAgreeView () {
		List<DTO_Agree> li = sst.selectList("shop_project.agreeAll");
		return li;
	}
}
