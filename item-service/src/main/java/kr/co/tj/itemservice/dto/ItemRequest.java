package kr.co.tj.itemservice.dto;

import lombok.Data;

@Data
public class ItemRequest {

	private long id;

	private String itemName;
	
	private int price;
	
	private int discount;
	
	private String username;
	
	private int ea;
	
	private String itemDescribe;
	
	private String itemType;
	
}
