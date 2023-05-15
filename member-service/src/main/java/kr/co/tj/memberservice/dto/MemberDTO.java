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

	public static MemberDTO toMemberDTO(MemberRequest memberRequest) {
		return MemberDTO.builder()
				.username(memberRequest.getUsername())
				.name(memberRequest.getName())
				.password(memberRequest.getPassword())
				.build();
	}

	public MemberResponse toMemberResponse() {
		return MemberResponse.builder()
				.username(username)
				.name(name)
				.createAt(createAt)
				.updateAt(updateAt)
				.build();
	}

	public MemberEntity toMemberEntity() {
		return MemberEntity.builder()
				.username(username)
				.name(name)
				.password(password)
				.createAt(createAt)
				.updateAt(updateAt)
				.build();
	}

	public MemberDTO toMemberDTO(MemberEntity memberEntity) {
		this.username = memberEntity.getUsername();
		this.name = memberEntity.getName();
		this.createAt = memberEntity.getCreateAt();
		this.updateAt = memberEntity.getUpdateAt();
		
		return this;
	}

}
