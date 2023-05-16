package kr.co.tj.itemservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.tj.itemservice.dto.ReplyResponse;

@FeignClient(name = "reply-service")
public interface ReplyFeign {
	
	@GetMapping("/relply-service/all/{bid}")
	public List<ReplyResponse> findByBId(@PathVariable("bid") Long bid);
		

}
