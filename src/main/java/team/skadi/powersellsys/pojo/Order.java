package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
	Integer id;
	Integer userId;
	Integer powerId;
	Integer sum;
	Double amount;
	LocalDateTime createTime;
}
