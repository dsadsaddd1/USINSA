package kr.co.tj.replyservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String content;	
	private Date createDate;
	private Date updateDate;
	private Long bid;
	
	public ReplyEntity toReplyEntity() {
		return ReplyEntity.builder()
				.id(id)
				.username(username)
				.content(content)
				.createDate(createDate)
				.updateDate(updateDate)
				.bid(bid)
				.build();
	}
	
	public static ReplyDTO toReplyDTO(ReplyEntity replyEntity) {
		return ReplyDTO.builder()
				.id(replyEntity.getId())
				.username(replyEntity.getUsername())
				.content(replyEntity.getContent())
				.createDate(replyEntity.getCreateDate())
				.updateDate(replyEntity.getUpdateDate())
				.bid(replyEntity.getBid())
				.build();
	}

	
	//request는 클라이언트에서 서버로 전송되는 메시지이다.
	public static ReplyDTO toReplyDTO(ReplyRequest replyRequest) {
		// TODO Auto-generated method stub
		return ReplyDTO.builder()
				.username(replyRequest.getUsername())
				.content(replyRequest.getContent())
				.bid(replyRequest.getBid())
				.build();
	}
	
}
