package shopping_admin;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product_DTO {
	int pr_idx;
	String pr_caname, pr_code, pr_name, pr_info;
	int pr_price, pr_dc, pr_dcprice, pr_stock;
	String pr_selluse,  pr_earlystockuse;
	String pr_file1name, pr_file1url, pr_file2name, 
	pr_file2url, pr_file3name, pr_file3url;
	String pr_desc, pr_adminid;
	
	MultipartFile pr_file1, pr_file2, pr_file3;
}
