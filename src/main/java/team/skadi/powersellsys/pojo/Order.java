package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private Integer id;
	private Integer userId;
	private Integer powerId;
	private Integer sum;
	private Double amount;
	private LocalDateTime createTime;
}
