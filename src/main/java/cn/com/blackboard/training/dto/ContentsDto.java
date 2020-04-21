package cn.com.blackboard.training.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

/**      
 * <b>ContentsDto</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
@Data
@Accessors(chain = true)
public class ContentsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String parentId;
	private String title;
	private String body;
	private Date created;
	private Integer position;
	private Map<String,Object> availability;
	private Map<String,Object> contentHandler;
	private List<ContentsAttachmentsDto> attachments;
	private String type;
	
	public String getType() {
		try {
			String id = (String) contentHandler.get("id");
			type = id.substring(id.lastIndexOf("-") + 1,id.length());
			return type;
		} catch (Exception e) {
			return type;
		}
	}
}
