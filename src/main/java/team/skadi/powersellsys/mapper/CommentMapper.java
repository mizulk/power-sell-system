package team.skadi.powersellsys.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.Judge;

import java.util.List;

public interface CommentMapper {

	List<Comment> page(Comment comment);

	@Insert("INSERT INTO comments(user_id, power_id, content, star, create_time)" +
			" VALUES(#{userId}, #{powerId}, #{content}, #{star}, #{createTime})")
	void insertNewComment(Comment comment);

	void updateComment(Comment comment);

	@Delete("DELETE FROM comments WHERE id = #{id}")
	void deleteComment(Comment comment);

	List<Judge> pageJudge(Judge judge);
}
