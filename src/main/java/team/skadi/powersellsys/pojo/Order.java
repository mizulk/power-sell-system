package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
	private Integer id;
	private Integer userId;
	private Integer powerId;
	private Integer sum;
	private Double amount;
	private LocalDateTime createTime;
}
