package kr.co.shopbag.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTO_Admin {
	int ad_idx;
	String ad_id, ad_pass, ad_name, ad_email, ad_tel, ad_dep, ad_pos, ad_approve, ad_indate;
}
