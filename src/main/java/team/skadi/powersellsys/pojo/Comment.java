package team.skadi.powersellsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Integer id;
	private Integer userId;
	private Integer powerId;
	private String content;
	private Byte star;
	private LocalDateTime createTime;
}
