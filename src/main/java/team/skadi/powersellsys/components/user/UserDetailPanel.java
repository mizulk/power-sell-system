package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.FavoriteDialog;
import team.skadi.powersellsys.components.dialog.edit.UserCommentDialog;
import team.skadi.powersellsys.components.information.GoodsInformationPanel;
import team.skadi.powersellsys.model.user.DetailTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.FavoritePower;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.FavoritePowerService;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

public class UserDetailPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel, ListSelectionListener {

	private Goods goods;
	private DetailTableModel detailTableModel;
	private PaginationPanel paginationPanel;
	private JTable detailTable;
	private JButton favoriteBtn;
	private JButton evaluationBtn;
	private ImageButton viewBtn;


	public UserDetailPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		detailTableModel = new DetailTableModel();
		goods = new Goods();
		goods.setStatus(1);
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		detailTableModel = new DetailTableModel();
		detailTable = new JTable(detailTableModel);
		detailTable.setRowHeight(30);
		detailTable.getTableHeader().setReorderingAllowed(false);
		detailTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		detailTable.getSelectionModel().addListSelectionListener(this);
		add(new JScrollPane(detailTable), BorderLayout.CENTER);

		JPanel btnPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 1;
		gbc.weightx = 1;

		favoriteBtn = new ImageButton("收藏", "/images/collect.png");
		favoriteBtn.setEnabled(false);
		btnPanel.add(favoriteBtn, gbc);

		evaluationBtn = new ImageButton("评论", "/images/comment.png");
		evaluationBtn.setEnabled(false);
		btnPanel.add(evaluationBtn, gbc);

		viewBtn = new ImageButton("查看详细", "/images/view.png");
		viewBtn.setEnabled(false);
		btnPanel.add(viewBtn, gbc);

		gbc.gridy++;
		gbc.gridwidth = 3;
		paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
		btnPanel.add(paginationPanel, gbc);
		add(btnPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"名称", "型号", "容量"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void refreshData() {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	protected void addListener() {
		favoriteBtn.addActionListener(this);
		evaluationBtn.addActionListener(this);
		viewBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == favoriteBtn) {
			addFavorite();
		} else if (source == evaluationBtn) {
			addEvalution();
		} else if (source == viewBtn) {
			viewGoods();
		}
	}

	private void viewGoods() {
		Goods row = detailTableModel.getRow(detailTable.getSelectedRow());
		BasicDialog basicDialog = new BasicDialog(app, "商品详细") {
			@Override
			protected JComponent getCenterLayout() {
				GoodsInformationPanel goodsInformationPanel = new GoodsInformationPanel(app);
				goodsInformationPanel.showGoods(row);
				return goodsInformationPanel;
			}
		};
		basicDialog.getOption();
		row.setSum(row.getSum() + 1);
		ServiceUtil.getService(GoodsService.class).updateGoods(row);
	}

	public void addFavorite() {
		if (detailTable.getSelectedRow() == -1) {
			FavoriteDialog favoriteDialog = new FavoriteDialog(app);
			favoriteDialog.getOption();
		} else {
			Goods row = detailTableModel.getRow(detailTable.getSelectedRow());
			FavoritePower favoritePower = new FavoritePower();
			favoritePower.setUserId(app.useStore().userStore.id());
			favoritePower.setPowerId(row.getId());
			FavoritePowerService service = ServiceUtil.getService(FavoritePowerService.class);
			service.addNewFavorite(favoritePower);
			JOptionPane.showMessageDialog(app, String.format("添加电源%s到收藏夹成功", row.getName()));
		}
	}

	public void addEvalution() {
		UserCommentDialog userCommentDialog = new UserCommentDialog(app, UserCommentDialog.ADD_MODE);
		Comment comment = new Comment();
		comment.setPowerId(detailTableModel.getRow(detailTable.getSelectedRow()).getId());
		comment.setUserId(app.useStore().userStore.id());
		userCommentDialog.setData(comment);
		if (userCommentDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			JOptionPane.showMessageDialog(app, "评论成功");
		} else {
			JOptionPane.showMessageDialog(app, "已取消");
		}

	}

	@Override

	public void firstPage(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(page, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		goods.setStatus(1);
		try {
			switch (optionIndex) {
				case 0 -> goods.setName(content);
				case 1 -> goods.setModel(content);
				case 2 -> goods.setCapacity(Integer.parseInt(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		detailTableModel.updateData(goodsPageBean.getData());
		paginationPanel.setPageBean(goodsPageBean);
		return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		goods = new Goods();
		goods.setStatus(1);
		PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
		paginationPanel.setPageBean(goodsPageBean);
		detailTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = detailTable.getSelectedRow() != -1;
		favoriteBtn.setEnabled(b);
		evaluationBtn.setEnabled(b);
		viewBtn.setEnabled(b);
	}
}
