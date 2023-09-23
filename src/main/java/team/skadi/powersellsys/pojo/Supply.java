package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Supply {
	private Integer id;
	private Integer supplierId;
	private Integer powerId;
	private Integer sum;
	private LocalDateTime createTime;
	private LocalDateTime supplyTime;
}
