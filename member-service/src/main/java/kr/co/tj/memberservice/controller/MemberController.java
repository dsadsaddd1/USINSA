package kr.co.tj.memberservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tj.memberservice.dto.MemberDTO;
import kr.co.tj.memberservice.dto.MemberEntity;
import kr.co.tj.memberservice.dto.MemberRequest;
import kr.co.tj.memberservice.dto.MemberResponse;
import kr.co.tj.memberservice.service.MemberService;

@RestController
@RequestMapping("/member-service")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
// // //

//status	
	@GetMapping("/health_check")
	public String status() {
		return "서버 확인";
	}
	
	
	@PostMapping("/members")
	public ResponseEntity<?> createMember(@RequestBody MemberRequest memberRequest){
		MemberDTO memberDTO = MemberDTO.toMemberDTO(memberRequest);
		
		memberDTO = memberService.createMember(memberDTO);
		MemberResponse memberResponse = memberDTO.toMemberResponse();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
	}
	
	@PutMapping("/members/username")
	public ResponseEntity<?> updateMember(@RequestBody MemberResponse memberResponse){
//		MemberEntity memberEntity = memberService.getMemberByUsername(memberResponse.getUsername());
		Map<String, Object> map = new HashMap<>();
		
		if(memberResponse == null) {
			return ResponseEntity.status(HttpStatus.OK).body("해당회원은 존재하지않습니다.");
		}
		
		try {
			memberResponse = memberService.updateMember(memberResponse);
			map.put("result", memberResponse);
			return ResponseEntity.ok().body(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "수정실패");
			return ResponseEntity.badRequest().body(map);
		}
		
	}
	
	@DeleteMapping("/members/delete")
	public ResponseEntity<?> deleteMember(@RequestBody MemberResponse memberResponse){
		Map<String, Object> map = new HashMap<>();
		
		if(memberResponse == null) {
			return ResponseEntity.status(HttpStatus.OK).body("해당회원은 존재하지않습니다.");
		}
		
		try {
			memberService.deleteMember(memberResponse);
			map.put("result", "삭제완료");
			return ResponseEntity.ok().body(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "삭제실패");
			return ResponseEntity.badRequest().body(map);
		}
		
	}
	
	@GetMapping("/members/all")
	public ResponseEntity<?> findAllMembers(){
		Map<String, Object> map = new HashMap<>();
		
		try {
			List<MemberResponse> memberList = memberService.findAll();
			map.put("result", memberList);
			return ResponseEntity.ok().body(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("result", "회원 리스트를 불러오지 못했습니다");
			return ResponseEntity.badRequest().body(map);
		}
	}

}
