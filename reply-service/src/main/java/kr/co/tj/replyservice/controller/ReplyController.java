package kr.co.tj.replyservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tj.replyservice.dto.ReplyDTO;
import kr.co.tj.replyservice.dto.ReplyRequest;
import kr.co.tj.replyservice.dto.ReplyResponse;
import kr.co.tj.replyservice.service.ReplyService;

@RestController
@RequestMapping("/reply-service")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	

//	댓글 페이지네이션 코드
//	@GetMapping("/bid")
//	public ResponseEntity<?> listByBid(@RequestParam Long bid, @RequestParam int pageNum) {
//	    Map<String, Object> map = new HashMap<>();
//	    Page<ReplyDTO> page = replyService.findByBid(bid, pageNum);
//	    map.put("result", page);
//	    return ResponseEntity.ok().body(map);
//	}
	
	@Autowired
	private Environment env;

	
	@GetMapping("/health_check")
	public String status() {
		return "reply service입니다."+env.getProperty("local.server.port");
	}

	
	
	// 댓글 입력
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody ReplyRequest replyRequest){

	Map<String, Object> map = new HashMap<>();
	
	if (replyRequest == null 
			|| replyRequest.getUsername() == null 
			|| replyRequest.getUsername().isEmpty()
			|| replyRequest.getContent() == null
			|| replyRequest.getContent().isEmpty()) {
		map.put("result", "잘못된 접근입니다. replyDTO값이 존재하지 않습니다.");
		return ResponseEntity.badRequest().body(map);
	}
		
		
	ReplyDTO replyDTO = ReplyDTO.toReplyDTO(replyRequest);
	replyDTO = replyService.insert(replyDTO);
	ReplyResponse replyResponse = replyDTO.toReplyResponse();
	
	return ResponseEntity.status(HttpStatus.CREATED).body(replyResponse);
}
	

	// 댓글 id로 검색해서 불러오기
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		
		Map<String, Object> map = new HashMap<>();
		
		if ( id == null) {
			map.put("result", "잘못된 접근입니다. 존재하지 않는 id입니다.");
			return ResponseEntity.badRequest().body(map);
		}
		
		try {
			ReplyDTO replyDTO = replyService.findById(id);
			ReplyResponse replyResponse = replyDTO.toReplyResponse();

			return ResponseEntity.status(HttpStatus.OK).body(replyResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("err", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		}
	}
	
	// 특정 게시글(bid)에 있는 모든 댓글 불러오기
	@GetMapping("/all/{bid}")
	public ResponseEntity<?> findByBId(@PathVariable("bid") Long bid){
		
		Map<String, Object> map = new HashMap<>();
		
		if ( bid == null) {
			map.put("result", "잘못된 접근입니다. 존재하지 않는 bid입니다.");
			return ResponseEntity.badRequest().body(map);
		}
		
		List<ReplyDTO> list = replyService.findByBId(bid);
		map.put("result", list);
		return ResponseEntity.ok().body(map);
		
	}
	

	// 특정 유저가 작성한 모든 댓글 불러오기
	@GetMapping("/username/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable("username") String username){
		Map<String, Object> map = new HashMap<>();
		
		if ( username == null) {
			map.put("result", "없는 username입니다.");
			return ResponseEntity.badRequest().body(map);
		}
		
		try {
			List<ReplyDTO> list = replyService.findByUsername(username);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("err", e.getMessage());
			return ResponseEntity.badRequest().body(map);
		}
		
	}
	

	// 댓글 수정하기
	@PutMapping({"", "/"})
	public ResponseEntity<?> update(@RequestBody ReplyRequest replyRequest){
		
		if (replyRequest == null 
				|| replyRequest.getId() == null
				|| replyRequest.getId() == 0L
				|| replyRequest.getContent() == null
				|| replyRequest.getContent().equals("")) {
			return ResponseEntity.status(HttpStatus.OK).body("잘못된 접근입니다.1");
		}

		try {
			ReplyDTO replyDTO = ReplyDTO.toReplyDTO(replyRequest);
			replyDTO = replyService.update(replyDTO);
			ReplyResponse replyResponse = replyDTO.toReplyResponse();
			
			return ResponseEntity.status(HttpStatus.OK).body(replyResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("잘못된 접근입니다.2");
		}	
	}
	
	
	
	// 댓글 삭제하기
	@DeleteMapping({"", "/"})
	public ResponseEntity<?> delete(@RequestBody ReplyRequest replyRequest){
//		Map<String, Object> map = new HashMap<>();
//		
//		Long id = replyDTO.getId();
//		
//		if (replyDTO == null
//				|| id == null
//				|| id == 0L) {
//			map.put("result", "잘못된 접근입니다. replyDTO값이 존재하지 않습니다.");
//			return ResponseEntity.badRequest().body(map);
//		}
//
//		try {
//			replyService.delete(id);
//			map.put("result", replyDTO);
//			return ResponseEntity.ok().body(map);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			map.put("err", e.getMessage());
//			return ResponseEntity.badRequest().body(map);
//		}

		ReplyDTO replyDTO = ReplyDTO.toReplyDTO(replyRequest);
		
		Long id = replyDTO.getId();
		
		replyService.delete(id);
		ReplyResponse replyResponse = replyDTO.toReplyResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(replyResponse);
	}
}
