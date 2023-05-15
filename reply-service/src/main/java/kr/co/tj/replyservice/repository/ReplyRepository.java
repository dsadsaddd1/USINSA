package kr.co.tj.replyservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.tj.replyservice.dto.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>{

	List<ReplyEntity> findByBid(Long bid);

	List<ReplyEntity> findByUsername(String username);

}
