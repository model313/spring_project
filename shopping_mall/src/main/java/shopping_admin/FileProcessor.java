package shopping_admin;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("file_process")
public class FileProcessor {
	private InputStream is = null;
	private FileOutputStream fos = null;
	
	public Map<String, String> fileProcess(MultipartFile file, HttpServletRequest req) throws Exception {
		Map<String, String> resultMap = new HashMap<String,String>();		
		String url = req.getServletContext().getRealPath("/upload/");
		
		String filename = file.getOriginalFilename();
		String result = "";
		if(!filename.equals("")) {
			this.is = file.getInputStream();
			result = rename(filename);
			
			this.fos = new FileOutputStream(url+result);
			byte[] bt = new byte[1024*2];
			int size = 0;
			do {
				this.fos.write(bt,0,size);
				this.fos.flush();
			}while((size=this.is.read(bt))!=-1);
			this.fos.close();
			this.is.close();
			
		}
		resultMap.put("filename", filename);
		resultMap.put("fileurl", result);
		
		return resultMap;
	}
	
	private String rename(String z) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fdt = sdf.format(dt);
		String rnd = String.valueOf((int)Math.ceil(Math.random()*1500));
		
		String filenm = z;
		
		int period = filenm.lastIndexOf(".");
		String filetype = filenm.substring(period+1);
		
		filenm = fdt + rnd + "." + filetype;
		
		return filenm;
	}

}
