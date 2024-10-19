package kr.co.shopbag.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.shopbag.devices.FileProcessor;
import kr.co.shopbag.devices.LoginStatusCaller;
import kr.co.shopbag.dto.DTO_Notice;

@Repository("admin_notice_module")
public class Admin_NoticeModule {
	@Resource(name="template")
	private SqlSessionTemplate sst;
	
	@Resource(name="session_call")
	private LoginStatusCaller lsc;
	
	@Resource(name="file_process")
	private FileProcessor fp;
	

	
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
	
	public List<DTO_Notice> selNoticeList () {
		List<DTO_Notice> li = sst.selectList("shop_project.noticeAll");
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
}
