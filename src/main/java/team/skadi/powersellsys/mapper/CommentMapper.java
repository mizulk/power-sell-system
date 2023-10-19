package team.skadi.powersellsys.mapper;

import team.skadi.powersellsys.pojo.Comment;

import java.util.List;

public interface CommentMapper {

	List<Comment> page(Comment comment);
}
