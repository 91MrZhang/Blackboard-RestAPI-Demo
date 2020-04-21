package cn.com.blackboard.training;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**      
 * <b>ServletInitializer</b>
 * 
 * @author: zhangyuting <br>
 * @date:   2020-01-21  <br>  
 */  
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestDemoApplication.class);
	}

}
