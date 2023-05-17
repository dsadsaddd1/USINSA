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
public class MemberRequest  {
	
	
	private String username;
	
	private String name;
	
	private String password;
	private String password2;
	private String orgPassword;



}
