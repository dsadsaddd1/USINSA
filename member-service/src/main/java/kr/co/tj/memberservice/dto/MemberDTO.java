package kr.co.tj.memberservice.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String username;
	
	private String name;
	
	private String password;
	
	private Date createAt;
	
	private Date updateAt;

}
