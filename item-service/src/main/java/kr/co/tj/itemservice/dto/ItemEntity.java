package kr.co.tj.itemservice.dto;

<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Entity
@Builder
public class ItemEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
=======
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class ItemEntity {
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String itemName;
	
	@Column(nullable = false)
<<<<<<< HEAD
	private int price;
=======
	private long price;
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf
	
	private int discount;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private int ea;
	
	@Column(nullable = false)
	private String itemDescribe;
	
	@Column(nullable = false)
	private String itemType;
	
	private Date createDate;
	private Date updateDate;
<<<<<<< HEAD


=======
>>>>>>> f63d22a65ba4fe3fc7ff3a586cf37797a5e3c3cf
}
