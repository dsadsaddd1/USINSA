package kr.co.tj.itemservice.dto;

<<<<<<< HEAD
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
	
=======
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
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf
	private String itemType;
	
}
