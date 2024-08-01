package shopping_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Site_DTO {
	//홈페이지 가입환경 설정
	int si_idx, si_joinpoint;
	String si_title, si_ademail, si_pointuse, si_userlevel;
	
	//기본 환경 설정
	String si_coname, si_coregno, si_ceoname, si_ceotel;
	String si_mosregno, si_vatbregno;
	String si_copostal, si_coaddr, si_imadminname, si_imadminemail;
	
	//결제 시본환경 설정
	String si_nbkname, si_bkaccno;
	String si_cardpayuse, si_telpayuse, si_dcmcpayuse;
	int si_pointusemin, si_pointusemax, si_delprice;
	String si_cashrcptuse, si_delconame, si_deldateuse;
	
	String si_indate;
	
}
