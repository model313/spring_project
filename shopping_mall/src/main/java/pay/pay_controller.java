package pay;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
public class pay_controller {
	@GetMapping("/pay/pay1")
	public String pay1page() {
		return "/pay/pay1";
	}
	
	
	@PostMapping("/pay/pay2.do")
	public String pay2(@ModelAttribute("product") pay_dao dao, Model m) throws Exception {
		//List로 전송
		List<String> as = new ArrayList<String>();
		as.add(dao.getProduct_code());
		as.add(dao.getProduct_nm());
		as.add(dao.getProduct_money());
		as.add(dao.getProduct_ea());
		as.add(dao.getPrice());
		
		//Collection으로 전송
		Collection<String> cl = new ArrayList<String>();
		cl.add(null);
		
		//Map으로 전송
		Map<String, String> mp = new HashMap<String, String>();
		mp.put("pdcode", dao.getProduct_code());
		
		//만든 배열을 전송
		m.addAllAttributes(Arrays.asList(as));
		return "/pay/pay2";
	}
	
	
	@PostMapping("/pay/pay3.do")
	public String pay3(@ModelAttribute("payinfo") pay_dao dao, HttpServletRequest req) throws Exception{
		req.setAttribute("goodcode", dao.getGoodcode());		//상품코드
		req.setAttribute("goodname", dao.getGoodname());		//상품명
		req.setAttribute("goodea", dao.getGoodea());			//상품개수
		req.setAttribute("goodprice", dao.getGoodprice());		//상품가격
		req.setAttribute("price", dao.getPrice());				//결제금액
		req.setAttribute("buyername", dao.getBuyername());		//결제자이름
		req.setAttribute("buyertel", dao.getBuyertel());		//결제자연락처
		req.setAttribute("buyeremail", dao.getBuyeremail());	//결제자이메일
		req.setAttribute("rec_post", dao.getRec_post());		//우편번호
		req.setAttribute("res_way", dao.getRes_way());			//도로명
		req.setAttribute("rec_addr", dao.getRec_addr());		//상세주소
		req.setAttribute("gopaymethod", dao.getGopaymethod());	//결제방식
		return "/pay/pay3";
	}
	
	@GetMapping("/pay/return_url")
	public String return_url_page() {
		return "/pay/return_url";
	}
	
	@GetMapping("/pay/close")
	public String close_page() {
		return "/pay/close";
	}
	
	//한페이지당 데이터 2개식
	@GetMapping("/pay/coupon_list.do")
	public String coupon_list(Model m, @RequestParam(value="", required=false) Integer page) throws Exception{
		
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		int dataperpage = 2;
		int startpg = 0;
		
		
		try {
			if(page==null || page ==1) {
				startpg = 0;
			}
			else {
				startpg = (page-1) * dataperpage;
			}
			con = new dbinfo().info();
			
			//데이터 총개수
			String count = "select count(*) as ctn from coupon";
			ps = con.prepareStatement(count);
			rs2 = ps.executeQuery();
			rs2.next();
			m.addAttribute("startpg", startpg);		//가공된 페이지 번호 전송
			m.addAttribute("total", rs2.getString("ctn"));
			
			String sql = "select * from coupon order by cidx desc limit ?,?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startpg);
			ps.setInt(2, dataperpage);
			rs = ps.executeQuery();
			
			List<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
			while(rs.next()) {
				ArrayList<String> al = new ArrayList<String>();
				al.add(rs.getString(1));
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				all.add(al);
			}
			//m.addAllAttributes(all); //2차 배열시 JSP로 전달 문제 생김
			
			m.addAttribute("all",all);
		} catch(Exception e) {
			System.out.println("DB 연결 실패");
		} finally {
			rs.close();
			rs2.close();
			ps.close();
			con.close();
		}
		return "/pay/coupon_list";
	}
	
	@GetMapping("/pay/coupon_listapi")
	public String coupon_listapipage() {
		return "/pay/coupon_listapi";
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/pay/coupon_api.do")
	public String coupon_api(HttpServletResponse res, HttpServletRequest req) throws Exception{
		/*
		String ips = req.getRemoteAddr();
		//HttpServletRequest req2 = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes());
		String ips2 = req.getHeader("X-FORWARDED=FOR");
		System.out.println(ips2);
		*/
		
		String ips = req.getRemoteAddr();
		String browser = req.getHeader("User-Agent");
		if(browser.contains("Edg")){
			System.out.println("Edge 접속 확인");
		}
		else if (browser.contains("Chrome")){
			System.out.println("Chrome 접속 확인");
		}
		System.out.println(browser);
		
		PrintWriter pw = null;
		JSONObject jo = null;
		JSONArray ja = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		pw = res.getWriter();
		
		try {
			con = new dbinfo().info();
			String sql = "select * from coupon order by cidx desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ja = new JSONArray();
			while(rs.next()) {
				jo = new JSONObject();
				jo.put("cidx", rs.getString(1));
				jo.put("cpname", rs.getString(2));
				jo.put("cprate", rs.getString(3));
				jo.put("cpuse", rs.getString(4));
				jo.put("cpdate", rs.getString(5));
				ja.put(jo);
			}
			
			pw.print(ja);
			
		} catch(Exception e) {
			pw.print("error");
		} finally {
			pw.close();
			rs.close();
			ps.close();
			con.close();
		}
		
		
		return null;
	}
	
}
