package kr.co.tj.memberservice.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse implements Serializable {
	

	private static final long serialVersionUID = 1L;

	
	private String username;
	
	private String name;
		
	private Date createAt;
	
	private Date updateAt;
	
	

}
