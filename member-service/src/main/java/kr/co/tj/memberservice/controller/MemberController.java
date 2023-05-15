package kr.co.tj.memberservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
