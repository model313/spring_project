package shopping_admin;


import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice_DTO {
	int an_idx, an_viewcount;
	String an_showtop, an_title, an_adname;
	String an_filename, an_fileurl, an_cktext;
	String an_adminid, an_indate;
	
	MultipartFile an_file;
}
