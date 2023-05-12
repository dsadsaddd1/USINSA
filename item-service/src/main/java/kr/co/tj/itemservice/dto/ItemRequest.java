package kr.co.tj.itemservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequest {
	
	private String itemName;
	private long price;
	private int discount;
	private int ea;
	private String username;
	private String itemType;
	
}
