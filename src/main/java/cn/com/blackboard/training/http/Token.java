package cn.com.blackboard.training.http;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Token implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String access_token;
	private String token_type;
	private Long expires_in;
	private Long generate_time;
}
