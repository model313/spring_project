package kr.co.shopbag.devices;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository("md5_encoder")
public class PassEncoder {
	public String encodePass (String pass) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(pass.getBytes());
			for(byte bt : md.digest()) {
				sb.append(String.format("%x", bt));
			}
		} catch (Exception e) {
			sb.append("ERROR");
		}
		return sb.toString();
	}
}
