package cn.com.blackboard.training.service;

import java.util.List;

import cn.com.blackboard.training.dto.ContentsDto;


/**      
 * <b>ContentsService</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-04-21  <br>  
 */  
public interface ContentsService {
	
	public List<ContentsDto> getAllContents(String courseId);

}
