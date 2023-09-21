package team.skadi.powersellsys.pojo;

import lombok.Data;

@Data
public class Comment {
	Integer userId;
	Integer powerId;
	String content;
	Byte star;
}
