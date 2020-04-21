package cn.com.blackboard.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**      
 * <b>RestDemoApplication</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDemoApplication.class, args);
	}
	
}
