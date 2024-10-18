package kr.co.shopbag.devices;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository("session_call")
public class LoginStatusCaller {
	public Map<String, String> statusCall(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Map<String, String> sessionData = new HashMap<String, String>();
		String id = (String) session.getAttribute("activeLoginID");
		String name = (String) session.getAttribute("activeLoginUser");
		
		sessionData.put("activeLoginID",id);
		sessionData.put("activeLoginUser",name);
		return sessionData;
	}
}
