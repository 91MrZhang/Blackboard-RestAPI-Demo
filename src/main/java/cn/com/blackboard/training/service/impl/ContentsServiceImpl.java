package cn.com.blackboard.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.blackboard.training.config.DemoConfig;
import cn.com.blackboard.training.dto.ContentsDto;
import cn.com.blackboard.training.service.ContentsService;

/**      
 * <b>Content</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-07  <br>  
 */  
@Service
public class ContentsServiceImpl implements ContentsService{

	@Autowired
	@Qualifier("RestTemplateForBBRest")
	private RestTemplate restTemplateForBBRest;
	
	
	
	@Override
	public List<ContentsDto> getAllContents(String courseId) {
		String url = DemoConfig.HOST_REST_URl + "/v1/courses/" + courseId + "/contents";
		String jsonResult = restTemplateForBBRest.getForObject(url, String.class);
		JSONObject parseObject = JSONObject.parseObject(jsonResult);
		JSONArray parseArray = parseObject.getJSONArray("results");
		List<ContentsDto> courseContentsDtoList = parseArray.toJavaList(ContentsDto.class);
		return courseContentsDtoList;
	}
}
