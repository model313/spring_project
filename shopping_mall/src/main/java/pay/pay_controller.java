package pay;

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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String coupon_list(Model m) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int dataperpage = 2;
		
		
		try {
			con = new dbinfo().info();
			String sql = "select * from coupon order by cidx desc limit ?,?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 0);
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
			m.addAllAttributes(all);
			
		} catch(Exception e) {
			System.out.println("DB 연결 실패");
		} finally {
			rs.close();
			ps.close();
			con.close();
		}
		return "/pay/coupon_list";
	}
	
	
}
