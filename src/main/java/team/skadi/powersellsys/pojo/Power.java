package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Power {
	Integer id;
	String name;
	String type;
	String model;
	Integer stock;
	Double price;
	Byte discount;
	Integer status;
	LocalDateTime createTime;
	LocalDateTime updateTime;
	String describe;
}
