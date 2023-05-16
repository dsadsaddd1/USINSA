package kr.co.tj.itemservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tj.itemservice.dto.ItemDTO;
import kr.co.tj.itemservice.dto.ItemEntity;
import kr.co.tj.itemservice.dto.ItemRequest;
import kr.co.tj.itemservice.dto.ItemResponse;
import kr.co.tj.itemservice.dto.OrderResponse;
import kr.co.tj.itemservice.service.ItemService;

@RestController
@RequestMapping("/item-service")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PutMapping("/item/productid")
	public ResponseEntity<?> updateStockByProductId(@RequestBody OrderResponse orderResponse) {

		ItemDTO itemDTO = itemService.findById(orderResponse.getProductId());
		// ItemEntity itemEntity = itemService.findById(orderResponse.getProductId());
		ItemEntity itemEntity = ItemDTO.toItemEntity(itemDTO);

		if (itemEntity == null) {
			return ResponseEntity.status(HttpStatus.OK).body("0: 존재하지 않는 상품");
		}

		if (itemEntity.getEa() < orderResponse.getQty()) {
			return ResponseEntity.status(HttpStatus.OK).body("0: 재고가 부족합니다.");
		}

		itemEntity.setEa(itemEntity.getEa() - orderResponse.getQty());

		String result;

		try {
			result = itemService.updateEaByProductId(itemEntity);
			if (result.equalsIgnoreCase("ok")) {
				return ResponseEntity.status(HttpStatus.OK).body("1: 성공");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("0: 재고 갱신 실패");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK).body("0: 재고 갱신 실패");
		}

	}
	
	@GetMapping("/item/id/{id}/replys")
	public ResponseEntity<?> getReplys(@PathVariable() Long id){
		
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		ItemDTO dto = itemService.getReplys(id);
		
		ItemResponse itemResponse = dto.toItemResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(itemResponse);
	}
	
	
	@DeleteMapping("")
	public ResponseEntity<?> delete(@RequestBody ItemRequest itemRequest) {
		
		if(itemRequest == null
				|| itemRequest.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		ItemDTO itemDTO = ItemDTO.toItemDTO(itemRequest);

		Long id = itemDTO.getId();

		itemService.deleteItem(id);
		
		ItemResponse itemResponse = itemDTO.toItemResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(itemResponse);
	}

	
	@GetMapping("/list/itemtype/{itemType}")
	public ResponseEntity<?> findByItemType(@PathVariable("itemType") String itemType) {
		
		if(itemType == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		List<ItemDTO> list = itemService.findByItemType(itemType);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	
	@PutMapping("/item/update")
	public ResponseEntity<?> update(@RequestBody ItemRequest itemRequest){
		
		if (itemRequest == null 
				|| itemRequest.getItemName() == null 
				|| itemRequest.getPrice() == 0 
				|| itemRequest.getEa() == 0
				|| itemRequest.getItemDescribe() == null 
				|| itemRequest.getItemDescribe() == "" 
				|| itemRequest.getItemType() == null
				|| itemRequest.getItemType() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		ItemDTO itemDTO = ItemDTO.toItemDTO(itemRequest);
		
		itemDTO = itemService.updateItem(itemDTO);
		
		ItemResponse itemResponse = itemDTO.toItemResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(itemResponse);
		
	}
	
	@GetMapping("/item/id/{id}")
	public ResponseEntity<?> findById(@PathVariable() Long id){
		
		if(id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		ItemDTO dto = itemService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("/item/username/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable() String username){
		
		if(username == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 정보");
		}
		
		List<ItemDTO> list = itemService.findByUsername(username);
		
//		List<ItemResponse> responsesList = new ArrayList<>();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/items/all")
	public ResponseEntity<?> findAll(){
		
		List<ItemDTO> list = itemService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@PostMapping("/items")
	public ResponseEntity<?> createItem(@RequestBody ItemRequest itemRequest) {

		if (itemRequest.getItemName() == null
				|| itemRequest.getPrice() == 0
				|| itemRequest.getDiscount() == 0
				|| itemRequest.getUsername() == null
				|| itemRequest.getUsername().equals("")
				|| itemRequest.getEa() <= 0
				|| itemRequest.getItemDescribe() == null
				|| itemRequest.getItemDescribe().equals("")
				|| itemRequest.getItemType() == null
				|| itemRequest.getItemType().equals("")
				) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패1");
		}
		
		ItemDTO itemDTO = ItemDTO.toItemDTO(itemRequest);
		
		itemDTO = itemService.createItem(itemDTO);
		
		ItemResponse itemResponse = itemDTO.toItemResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(itemResponse);
		
	}
	
	@GetMapping("/health_check")
	private String status() {
		return "item 서비스";
	}

}
