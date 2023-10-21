package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Comment;

import java.util.List;

public interface CommentMapper {

	List<Comment> page(Comment comment);

	@Insert("INSERT INTO comments(user_id, power_id, content, create_time)"+
			" VALUES(#{userId}, #{powerId}, #{content}, #{createTime})")
	void insertNewComment(Comment comment);

	void updateComment(Comment comment);
}
