package kr.co.tj.itemservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.tj.itemservice.dto.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	List<ItemEntity> findByUsername(String username);

	List<ItemEntity> findByItemType(String itemType);

}
