package kr.co.shopbag.dto;

import lombok.Data;

@Data
public class DTO_User {
	int mm_idx;
	String mm_id, mm_pass, mm_name; 
	String mm_email, mm_tel;
	String mm_emailuse, mm_smsuse, mm_locked;
	String mm_indate;
}
