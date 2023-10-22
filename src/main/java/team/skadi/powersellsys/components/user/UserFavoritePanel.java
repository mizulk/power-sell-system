package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.FavoriteTableModel;
import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserFavoritePanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel, ListSelectionListener {

	private FavoritePower favoritePower;
	private FavoriteTableModel favoriteTableModel;
	private PaginationPanel paginationPanel;
	private JTable favoriteTable;
	private JButton delfavoriteBtn;

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
		favoriteTable = new JTable(favoriteTableModel);
		favoriteTable.setRowHeight(30);
		favoriteTable.getTableHeader().setReorderingAllowed(false);
		favoriteTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		favoriteTable.getSelectionModel().addListSelectionListener(this);
		add(new JScrollPane(favoriteTable), BorderLayout.CENTER);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weightx = 1;

		delfavoriteBtn = new ImageButton("移出收藏", "/images/delete.png");
		delfavoriteBtn.setEnabled(false);
		btnPanel.add(delfavoriteBtn, gbc);

		gbc.gridy++;
		gbc.gridwidth = 2;
		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		btnPanel.add(paginationPanel, gbc);
		add(btnPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户id", "电源id"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		FavoritePowerService favoriteService = ServiceUtil.getService(FavoritePowerService.class);
		PageBean<FavoritePower> favoritePageBean = favoriteService.queryFavorite(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(favoritePageBean);
		favoriteTableModel.updateData(favoritePageBean.getData());

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
		delfavoriteBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == delfavoriteBtn) {
			delFavorite();
		}
	}

	public void delFavorite() {
		FavoritePower row = favoriteTableModel.getRow(favoriteTable.getSelectedRow());
		int option = JOptionPane.showConfirmDialog(app, "是否要删除 " + row.getName(), "warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (option == JOptionPane.YES_OPTION){
			favoriteTableModel.delRow(favoriteTable.getSelectedRow());
			JOptionPane.showMessageDialog(app, String.format("移除电源%s成功", row.getName()));
		} else {
			JOptionPane.showMessageDialog(app,"已取消");
		}
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = favoriteTable.getSelectedRow() != -1;
		delfavoriteBtn.setEnabled(b);
	}
}
