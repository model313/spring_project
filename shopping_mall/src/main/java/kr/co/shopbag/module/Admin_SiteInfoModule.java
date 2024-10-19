package kr.co.shopbag.module;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.dto.DTO_Site;

@Repository("admin_siteinfo_module")
public class Admin_SiteInfoModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	// 사이트 정보 출력
	public List<DTO_Site> selSite(){
		List<DTO_Site> result = sst.selectList("shop_project.selSite");
		return result;
	}
	
	// 사이트 정보 수정
	public int modSite(DTO_Site dto) {
		int result = sst.update("shop_project.modSite", dto);
		return result;
	}
}
