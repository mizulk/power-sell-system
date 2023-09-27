package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supply {
	private Integer id;
	private Integer supplierId;
	private Integer powerId;
	private Integer sum;
	private LocalDateTime createTime;
	private LocalDateTime supplyTime;
}
