package kr.co.shopbag.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.devices.FileProcessor;
import kr.co.shopbag.devices.LoginStatusCaller;
import kr.co.shopbag.dto.DTO_Cate;
import kr.co.shopbag.dto.DTO_Product;

@Repository("admin_content_module")
public class Admin_ContentModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	@Resource(name="session_call")
	private LoginStatusCaller lsc;
	
	@Resource(name="file_process")
	private FileProcessor fp;
	
	
	public List<DTO_Cate> selCateList(){
		List<DTO_Cate> result = sst.selectList("shop_project.cateAll");
		return result;
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
	

	public List<DTO_Product> selProductList(){
		List<DTO_Product> li = sst.selectList("shop_project.productAll");
		return li;
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

}
