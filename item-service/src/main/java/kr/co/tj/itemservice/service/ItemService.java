package kr.co.tj.itemservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.tj.itemservice.dto.ItemDTO;
import kr.co.tj.itemservice.dto.ItemEntity;
import kr.co.tj.itemservice.dto.ItemResponse;
import kr.co.tj.itemservice.dto.ReplyResponse;
import kr.co.tj.itemservice.feign.ReplyFeign;
import kr.co.tj.itemservice.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ReplyFeign replyFeign;
	
	@Transactional
	public String updateEaByProductId(ItemEntity itemEntity) {
		// TODO Auto-generated method stub

		try {
			itemRepository.save(itemEntity);
			return "ok";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	public ItemDTO createItem(ItemDTO itemDTO) {
		
		itemDTO = getDate(itemDTO);
		
		itemDTO.setSalePrice(itemDTO.getPrice()-(itemDTO.getPrice() * (itemDTO.getDiscount()/100)));
		itemDTO.setTotalPrice(itemDTO.getSalePrice() * itemDTO.getEa());
		
		ItemEntity itemEntity = itemDTO.toItemEntity();
		
		itemEntity = itemRepository.save(itemEntity);
		
//		통신작업
//		ItemResponse itemResponse = itemDTO.toItemResponse();
		
		return itemDTO;
	}

	private ItemDTO getDate(ItemDTO itemDTO) {
		Date now = new Date();
		
		if(itemDTO.getCreateDate() == null) {
			itemDTO.setCreateDate(now);
		}
		
		itemDTO.setUpdateDate(now);
		
		return itemDTO;
	}

	public List<ItemDTO> findAll() {
		List<ItemDTO> list_dto = new ArrayList<>();
		List<ItemEntity> list_entity = itemRepository.findAll();
		
		for (ItemEntity entity : list_entity) {
			list_dto.add(ItemDTO.toItemDTO(entity));
		}
		
			
		return list_dto;
	}

	public List<ItemDTO> findByUsername(String username) {
		
		List<ItemDTO> list_dto = new ArrayList<>();
		
		List<ItemEntity> list_entity = itemRepository.findByUsername(username);
		
		for (ItemEntity entity : list_entity) {
			list_dto.add(ItemDTO.toItemDTO(entity));
		}
		
		return list_dto;
	}

	public ItemDTO findById(Long id) {
		Optional<ItemEntity> optional = itemRepository.findById(id);
		
		ItemEntity entity = optional.get();
		
		ItemDTO dto = ItemDTO.toItemDTO(entity);
		
		return dto;
	}

	@Transactional
	public ItemDTO updateItem(ItemDTO itemDTO) {
		
		Optional<ItemEntity> optional = itemRepository.findById(itemDTO.getId());
		
		if(!optional.isPresent()) {
			throw new RuntimeException("잘못된 정보에용1");
		}
		
		ItemEntity entity = optional.get();
		
		if(entity ==null) {
			throw new RuntimeException("잘못된 정보에용");
		}
		
		entity.setItemName(itemDTO.getItemName());
		entity.setPrice(itemDTO.getPrice());
		entity.setDiscount(itemDTO.getDiscount());
		entity.setEa(itemDTO.getEa());
		entity.setItemDescribe(itemDTO.getItemDescribe());
		entity.setUpdateDate(new Date());
		entity.setItemType(itemDTO.getItemType());
		entity = itemRepository.save(entity);
		itemDTO = ItemDTO.toItemDTO(entity);
		
		return itemDTO;
	}

	public List<ItemDTO> findByItemType(String itemType) {
		List<ItemDTO> list_dto = new ArrayList<>();
		List<ItemEntity> list_entity = itemRepository.findByItemType(itemType);
		

		for (ItemEntity entity : list_entity) {
			list_dto.add(ItemDTO.toItemDTO(entity));
		}
		return list_dto;
	}
	
	@Transactional
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

	public ItemDTO getReplys(Long id) {
		
		Optional<ItemEntity> optional = itemRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("안돼");
		}
		
		ItemEntity itemEntity= optional.get();
		
		//ItemEntity itemEntity = itemRepository.findById(id);
		
		
		ItemDTO itemDTO = new ItemDTO();
		itemDTO = ItemDTO.toItemDTO(itemEntity);
		
		List<ReplyResponse> replyList = replyFeign.getReplysByBid(id);
		
		itemDTO.setReplyList(replyList);
		
		return itemDTO;
	}

}
