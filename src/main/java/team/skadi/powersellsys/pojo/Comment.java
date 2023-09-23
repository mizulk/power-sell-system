package team.skadi.powersellsys.pojo;

import lombok.Data;

@Data
public class Comment {
	private Integer userId;
	private Integer powerId;
	private String content;
	private Byte star;
}
