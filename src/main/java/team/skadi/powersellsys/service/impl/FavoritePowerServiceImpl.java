package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.FavoritePowerMapper;
import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

@Slf4j
public class FavoritePowerServiceImpl implements FavoritePowerService {
	@Override
	public PageBean<FavoritePower> queryFavorite(int page, int pageSize, FavoritePower favoritePower) {
		log.info("收藏列表查询，page: {}, pageSize: {}, Favorite: {}", page, pageSize, favoritePower);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
		Page<FavoritePower> favoritePowers = PageHelper.startPage(page, pageSize).doSelectPage(() -> favoritePowerMapper.page(favoritePower == null ? new FavoritePower() : favoritePower));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(favoritePowers.getTotal(), favoritePowers.getResult());
	}

	@Override
	public FavoritePower queryUserId(Integer userId) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
		FavoritePower favoritePower = favoritePowerMapper.getFavoritePowerByUserid(userId);
		return favoritePower;
	}

	@Override
	public FavoritePower queryPowerId(Integer powerId) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
		FavoritePower favoritePower = favoritePowerMapper.getFavoritePowerByUserid(powerId);
		return favoritePower;
	}

	@Override
	public void updateFavorite(FavoritePower favoritePower) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
			favoritePowerMapper.updateFavorite(favoritePower);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("更新收藏时出错，数据库回滚。",e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public boolean delFavorite(Integer powerId) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
			favoritePowerMapper.delFavoriteByPowerId(powerId);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("删除收藏时出错，数据库回滚。",e);
			sqlSession.rollback();
			return false;
		} finally {
			sqlSession.close();
		}
		return true;
	}

	@Override
	public void addNewFavorite(FavoritePower favoritePower) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		try {
			FavoritePowerMapper favoritePowerMapper = sqlSession.getMapper(FavoritePowerMapper.class);
			favoritePowerMapper.insertNewFavorite(favoritePower);
			sqlSession.commit();
		} catch (Exception e) {
			log.error("添加新收藏时出错，数据库回滚。",e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
}
