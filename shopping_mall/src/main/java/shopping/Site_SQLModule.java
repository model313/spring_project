package shopping;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("site_sql_module")
public class Site_SQLModule {
	@Resource(name="template2")
	private SqlSessionTemplate sst;
	
}
