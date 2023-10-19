package team.skadi.powersellsys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import team.skadi.powersellsys.mapper.FavoriteMapper;
import team.skadi.powersellsys.pojo.Favorite;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoriteService;
import team.skadi.powersellsys.utils.SqlSessionUtil;

@Slf4j
public class FavoriteServiceImpl implements FavoriteService {
	@Override
	public PageBean<Favorite> queryFavorite(int page, int pageSize, Favorite favorite) {
		log.info("收藏列表查询，page: {}, pageSize: {}, Favorite: {}", page, pageSize, favorite);
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoriteMapper favoriteMapper = sqlSession.getMapper(FavoriteMapper.class);
		Page<Favorite> favorites = PageHelper.startPage(page, pageSize).doSelectPage(() -> favoriteMapper.page(favorite == null ? new Favorite() : favorite));
		sqlSession.commit();
		sqlSession.close();
		return new PageBean<>(favorites.getTotal(), favorites.getResult());
	}

	@Override
	public Favorite queryUserid(Integer userId) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoriteMapper favoriteMapper = sqlSession.getMapper(FavoriteMapper.class);
		Favorite favorite = favoriteMapper.getFavoriteByUserid(userId);
		return favorite;
	}

	@Override
	public Favorite queryPowerid(Integer powerId) {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		FavoriteMapper favoriteMapper = sqlSession.getMapper(FavoriteMapper.class);
		Favorite favorite = favoriteMapper.getFavoriteByUserid(powerId);
		return favorite;
	}
}
