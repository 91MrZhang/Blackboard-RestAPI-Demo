package cn.com.blackboard.training.http;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**      
 * <b>Rest Config</b>
 * @author: zhangyuting <br>
 * @date:   2020-01-07  <br>  
 */  
@Configuration
public class RestConfig {
	
	@Autowired
	private RestRequestInterceptorForBBRest restRequestInterceptorForBBRest;
	
	@Bean(value="RestTemplateForBBRest")
	public RestTemplate restTemplateForBBRest() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Collections.singletonList(restRequestInterceptorForBBRest));
		return restTemplate;
	}
}
