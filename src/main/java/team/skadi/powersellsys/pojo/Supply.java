package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Supply {
	Integer id;
	Integer supplierId;
	Integer powerId;
	Integer sum;
	LocalDateTime supplyTime;
}
