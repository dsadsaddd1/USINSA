package kr.co.tj.itemservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
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

=======
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private long id;
	private String itemName;
	private long ea;
	private long price;
	private int discount;
	private int salePrice;
	private String itemType;
	private String itemDescribe;
	private Date createAt;
	private Date updateAt;
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf
}
