package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.CommentMapper;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

import java.time.LocalDateTime;

@Slf4j
@SuppressWarnings("unused")
public class CommentServiceImpl implements CommentService {
	@Override
	public PageBean<Comment> queryComment(int page, int pageSize, Comment comment) {
		log.info("分页查询评论，page；{}，pageSize：{}，comment：{}", page, pageSize, comment);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		Page<Comment> p = PageHelper.startPage(page, pageSize).doSelectPage(() -> commentMapper.page(comment == null ? new Comment() : comment));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(p.getTotal(), p.getResult());
	}

	@Override
	public void updateComment(Comment comment) {
		log.info("更新评论：{}", comment);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			commentMapper.updateComment(comment);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("更新评论时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void addNewComment(Comment comment) {
		log.info("添加评论：{}", comment);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			comment.setCreateTime(LocalDateTime.now());
			commentMapper.insertNewComment(comment);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("添加新评论时出错，数据库回滚。", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public boolean delComment(Comment comment) {
		log.info("删除评论，comment：{}", comment);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			commentMapper.deleteComment(comment);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("删除评论时出错，数据库回滚。", e);
			sqlSession.rollback();
			return false;
		} finally {
			sqlSession.close();
		}
		return true;
	}
}
