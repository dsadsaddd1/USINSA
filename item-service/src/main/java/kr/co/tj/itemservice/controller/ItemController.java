package kr.co.tj.itemservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.tj.itemservice.dto.ItemDTO;
import kr.co.tj.itemservice.dto.ItemRequest;
import kr.co.tj.itemservice.dto.ItemResponse;
import kr.co.tj.itemservice.service.ItemService;

@RestController
@RequestMapping("/item-service")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@DeleteMapping("")
	public ResponseEntity<?> delete(@RequestBody ItemRequest itemRequest) {
		
		ItemDTO itemDTO = ItemDTO.toItemDTO(itemRequest);

		Long id = itemDTO.getId();

		itemService.deleteItem(id);
		
		ItemResponse itemResponse = itemDTO.toItemResponse();
		
		return ResponseEntity.status(HttpStatus.OK).body(itemResponse);
	}

	
	@GetMapping("/list/itemtype/{itemType}")
	public ResponseEntity<?> findByItemType(@PathVariable("itemType") String itemType) {
		
			List<ItemDTO> list = itemService.findByItemType(itemType);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}
	
	@PutMapping("/item/update")
	public ResponseEntity<?> update(@RequestBody ItemRequest itemRequest){
		
		if (itemRequest == null || itemRequest.getItemName() == null || itemRequest.getPrice() == 0 || itemRequest.getEa() == 0
				|| itemRequest.getItemDescribe() == null || itemRequest.getItemDescribe() == "" || itemRequest.getItemType() == null
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

		if (itemRequest.getItemName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패1");
		}
		if(itemRequest.getPrice() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패2");
		}
		if(itemRequest.getDiscount() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패3");
		}
		if(itemRequest.getUsername() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패4");
		} 
		if(itemRequest.getUsername().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패5");
		}
		if(itemRequest.getEa() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패6");
		}
		if(itemRequest.getItemDescribe() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패7");
		}
		if(itemRequest.getItemDescribe().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패8");
		} 
		if(itemRequest.getItemType() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패9");
		}
		if(itemRequest.getItemType().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("상품 입력 실패10");
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
