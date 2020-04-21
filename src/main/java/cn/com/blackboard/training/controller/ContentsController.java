package cn.com.blackboard.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.blackboard.training.dto.ContentsDto;
import cn.com.blackboard.training.service.ContentsService;

/**      
 * <b>ContentsController</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
@Controller
@RequestMapping("/bb/")
public class ContentsController {
	
	@Autowired
	private ContentsService courseContentsService;
	
	@GetMapping(value = "/{coruseId}/contents")
	@ResponseBody
	public List<ContentsDto> getContents(@PathVariable("coruseId") String coruseId) {
		return courseContentsService.getAllContents(coruseId);
	}

}
