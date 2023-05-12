package kr.co.tj.itemservice.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
