package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.FavoriteTableModel;
import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserFavoritePanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel {

	private FavoritePower favoritePower;
	private FavoriteTableModel favoriteTableModel;
	private PaginationPanel paginationPanel;
	private JTable favoriteTable;

	public UserFavoritePanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		favoriteTableModel = new FavoriteTableModel();
		favoritePower = new FavoritePower();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		favoriteTableModel = new FavoriteTableModel();
		JTable table = new JTable(favoriteTableModel);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);

		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户id", "电源id"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!favoriteTableModel.hasData()) {
			FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
			PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(favoritePageBean);
			favoriteTableModel.updateData(favoritePageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(paginationPanel.getCurPage(), paginationPanel.getPageSize(), favoritePower);
		paginationPanel.setPageBean(favoritePageBean);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void firstPage(int pageSize) {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, pageSize, favoritePower);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, pageSize, favoritePower);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, pageSize, favoritePower);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, pageSize, favoritePower);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		favoritePower = new FavoritePower();
		try {
			switch (optionIndex) {
				case 0 -> favoritePower.setUserId(Integer.parseInt(content));
				case 1 -> favoritePower.setPowerId(Integer.parseInt(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<FavoritePower> favorites = ServiceUtil.getService(FavoritePowerService.class).queryFavorite(1, paginationPanel.getPageSize(), favoritePower);
		favoriteTableModel.updateData(favorites.getData());
		paginationPanel.setPageBean(favorites);
		return favorites.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<FavoritePower> favoritePageBean = ServiceUtil.getService(FavoritePowerService.class).queryFavorite(1, paginationPanel.getPageSize(), null);
		favoriteTableModel.updateData(favoritePageBean.getData());
	}
}
