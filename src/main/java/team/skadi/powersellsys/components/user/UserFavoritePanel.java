package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.model.user.FavoriteTableModel;
import team.skadi.powersellsys.pojo.Favorite;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoriteService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserFavoritePanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {
//TODO
	private Favorite favorite;
	private FavoriteTableModel favoriteTableModel;
	private PaginationPanel paginationPanel;
	private JTable favoriteTable;

	public UserFavoritePanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		favoriteTableModel = new FavoriteTableModel();
		favorite = new Favorite();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		FavoriteTableModel favoriteTableModel = new FavoriteTableModel();
		JTable table = new JTable(favoriteTableModel);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);


		PaginationPanel paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户id", "电源id"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	public void initdate() {
		if (!favoriteTableModel.hasData()) {
			FavoriteService favoriteService = ServiceUtil.getService(FavoriteService.class);
			PageBean<Favorite> favoritePageBean = favoriteService.queryFavorite(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(favoritePageBean);
			favoriteTableModel.updateData(favoritePageBean.getData());
		}
	}

//	public void showFavorite() {
//		BasicDialog favoriteInformationDialog = new BasicDialog(app, "收藏") {
//			@Override
//			protected JComponent getCenterLayout() {
//				FavoriteInformationPanel favoriteInformationPanel = new FavoriteInformationPanel(app);
//				favoriteInformationPanel.showFavorite(favoriteTableModel.getRow(favoriteTable.getSelectedRow()));
//				return favoriteInformationPanel;
//			}
//		};
//		favoriteInformationDialog.getOption();
//	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void firstPage(int pageSize) {

	}

	@Override
	public void nextPage(int curPage, int pageSize) {

	}

	@Override
	public void previousPage(int curPage, int pageSize) {

	}

	@Override
	public void jumpTo(int page, int pageSize) {

	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		return null;
	}

	@Override
	public void onCloseButtonCLick() {

	}
}
