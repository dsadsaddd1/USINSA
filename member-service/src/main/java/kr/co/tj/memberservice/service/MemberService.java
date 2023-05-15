package kr.co.tj.memberservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.tj.memberservice.dto.MemberDTO;
import kr.co.tj.memberservice.dto.MemberEntity;
import kr.co.tj.memberservice.dto.MemberResponse;
import kr.co.tj.memberservice.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
// // // 	

	public MemberDTO createMember(MemberDTO memberDTO) {
		memberDTO = getDate(memberDTO);
		
		MemberEntity memberEntity = memberDTO.toMemberEntity();
		
		memberEntity = memberRepository.save(memberEntity);
		
		return memberDTO.toMemberDTO(memberEntity);
	}

	private MemberDTO getDate(MemberDTO memberDTO) {
		Date date = new Date();
		
		if(memberDTO.getCreateAt() == null) {
			memberDTO.setCreateAt(date);
		}
		
		memberDTO.setUpdateAt(date);
		return memberDTO;
	}

	public MemberEntity getMemberByUsername(String username) {
		// TODO Auto-generated method stub
		return memberRepository.findByUsername(username);
	}

	@Transactional
	public MemberResponse updateMember(MemberResponse memberResponse) {
		MemberEntity memberEntity = memberRepository.findByUsername(memberResponse.getUsername());
		
		if(memberEntity == null) {
			throw new RuntimeException("");
		}
		
		memberEntity.setName(memberResponse.getName());
		memberEntity.setUpdateAt(new Date());
		memberEntity = memberRepository.save(memberEntity);

		return memberResponse;
	}

	public void deleteMember(MemberResponse memberResponse) {
		MemberEntity memberEntity = memberRepository.findByUsername(memberResponse.getUsername());
		
		if(memberEntity == null) {
			throw new RuntimeException("삭제 실패");
		}
		
		memberRepository.delete(memberEntity);
	}

	public List<MemberResponse> findAll() {
		List<MemberEntity> memberEntity = memberRepository.findAll();
		List<MemberResponse> memberResponse = new ArrayList<>();
		
		for (MemberEntity e : memberEntity) {
			memberResponse.add(new ModelMapper().map(e, MemberResponse.class));;
		}

		return memberResponse;
	}
	
}
