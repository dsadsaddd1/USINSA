package kr.co.tj.itemservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String itemName;
	
	private int price;
	
	private int discount;
	
	private int salePrice;
	
	private int totalPrice;
	
	private String username;
	
	private int ea;
	
	private String itemDescribe;
	
	private String itemType;
	
	private Date createDate;
	private Date updateDate;

}
