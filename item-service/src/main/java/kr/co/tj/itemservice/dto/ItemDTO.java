package kr.co.tj.itemservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
	
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
	
	
	public static ItemDTO toItemDTO(ItemRequest itemRequest) {
		// TODO Auto-generated method stub
		return ItemDTO.builder()
				.id(itemRequest.getId())
				.itemName(itemRequest.getItemName())
				.price(itemRequest.getPrice())
				.discount(itemRequest.getDiscount())
				.username(itemRequest.getUsername())
				.ea(itemRequest.getEa())
				.itemDescribe(itemRequest.getItemDescribe())
				.itemType(itemRequest.getItemType())
				.build();
	}




	public ItemEntity toItemEntity() {
		
		return ItemEntity.builder()
				.id(id)
				.itemName(itemName)
				.price(price)
				.discount(discount)
				.username(username)
				.ea(ea)
				.itemDescribe(itemDescribe)
				.itemType(itemType)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}




	public ItemResponse toItemResponse() {
		
		return ItemResponse.builder()
				.id(id)
				.itemName(itemName)
				.price(price)
				.discount(discount)
				.salePrice(salePrice)
				.totalPrice(totalPrice)
				.username(username)
				.ea(ea)
				.itemDescribe(itemDescribe)
				.itemType(itemType)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}




	public static ItemDTO toItemDTO(ItemEntity entity) {

		return ItemDTO.builder()
				.id(entity.getId())
				.itemName(entity.getItemName())
				.price(entity.getPrice())
				.discount(entity.getDiscount())
				.username(entity.getUsername())
				.ea(entity.getEa())
				.itemDescribe(entity.getItemDescribe())
				.itemType(entity.getItemType())
				.createDate(entity.getCreateDate())
				.updateDate(entity.getUpdateDate())
				.build();
	}


	

}
