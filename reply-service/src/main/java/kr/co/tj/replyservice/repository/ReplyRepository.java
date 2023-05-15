package kr.co.tj.replyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.tj.replyservice.dto.ReplyEntity;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long>{

}
