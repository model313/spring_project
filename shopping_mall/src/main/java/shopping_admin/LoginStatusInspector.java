package shopping_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

@Repository("session_inspect")
public class LoginStatusInspector {
	public boolean statusInspect(HttpServletRequest req) {
		boolean result = false;
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("activeLoginID");
		if (id==null) {
			result = false;
		}
		else {
			result = true;
		}
		return result;
	}
}
