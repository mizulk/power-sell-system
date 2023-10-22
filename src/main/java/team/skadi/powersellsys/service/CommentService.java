package team.skadi.powersellsys.service;

import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.Judge;
import team.skadi.powersellsys.pojo.PageBean;

public interface CommentService extends Service {

	PageBean<Comment> queryComment(int page, int pageSize, Comment comment);

	void updateComment(Comment comment);

	void addNewComment(Comment comment);

	boolean delComment(Comment comment);

	PageBean<Judge> queryJudge(int page,int pageSize,Judge judge);
}
