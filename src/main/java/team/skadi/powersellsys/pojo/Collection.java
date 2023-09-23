package team.skadi.powersellsys.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Collection {
	private Integer userId;
	private Integer powerId;
	private LocalDateTime createTime;
}
