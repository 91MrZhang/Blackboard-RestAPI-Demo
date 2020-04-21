package cn.com.blackboard.training.http;

import java.io.IOException;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.com.blackboard.training.config.DemoConfig;
import cn.com.blackboard.training.utils.RedisUtil;

/**      
 * <b>Rest Interceptor</b>
 * 
 * @author: zhangyuting <br>
 * @date:   2020-01-07  <br>  
 */  
@Component
public class RestRequestInterceptorForBBRest implements ClientHttpRequestInterceptor{
	
	public static String TOKEN;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", "Bearer " + getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);	
		return execution.execute(request, body);
	}
	
	/**
	 * <b>拿到即时的Token</b>
	 * @return
	 */
	private String getToken() {
		if(!isEffective()) {
			generateToken();
		}
		return TOKEN;
	}
	
	
	/**
	 * <b>生成BB RestApi的Token</b>
	 */
	private void generateToken() {
		// 前面的是AppId,后面的是AppId的密码
		String auth = DemoConfig.APP_ID + ":" + DemoConfig.APP_SECRET;
		String hash = Base64.getEncoder().encodeToString(auth.getBytes());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + hash);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		String url = DemoConfig.HOST_REST_URl + "/v1/oauth2/token";
		Token token = restTemplate.postForObject(url, httpEntity, Token.class);
		token.setGenerate_time(System.currentTimeMillis());

		// 存到redis
		RedisUtil.opsSet("BB::API_TOKEN", token);
		TOKEN = token.getAccess_token();
	}

	/**
	 * <b>有效性</b>
	 * @return Boolean
	 */
	private Boolean isEffective() {
		Token token = (Token) RedisUtil.opsGet("BB::API_TOKEN");
		if (token == null) {
			return false;
		}
		Long currentTime = System.currentTimeMillis();
		Long generateTime = token.getGenerate_time();
		if ((currentTime - generateTime)/1000 > token.getExpires_in()) {
			return false;
		}
		TOKEN = token.getAccess_token();
		return true;
	}
}
