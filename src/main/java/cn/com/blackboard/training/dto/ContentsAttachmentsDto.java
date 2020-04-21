package cn.com.blackboard.training.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**      
 * <b>ContentsAttachmentsDto</b>
 * 
 * @author: zhangyuting  <br>
 * @date:   2020-01-21  <br>  
 */  
@Data
@Accessors(chain = true)
public class ContentsAttachmentsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String fileName;
	private String url;
	private String publicUrl;
}
