package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.PriceTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class ShowPricePanel extends SupplierPanel implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {

    private Goods goods;
    private PaginationPanel paginationPanel;
    private PriceTableModel priceTableModel;
    private JTable table;

    public ShowPricePanel(App app) {
        super(app);
    }

    @Override
    protected void init() {
        goods = new Goods();
        super.init();
    }

    @Override
    public void initData() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> rank = goodsService.getRank(1, paginationPanel.getPageSize(), null);
        paginationPanel.setPageBean(rank);
        priceTableModel.updateData(rank.getData());
    }

    @Override
    public void refreshData() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> rank = goodsService.getRank(1, paginationPanel.getPageSize(), goods);
        paginationPanel.setPageBean(rank);
        priceTableModel.updateData(rank.getData());
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        priceTableModel = new PriceTableModel();
        table = new JTable(priceTableModel);
        table.setRowHeight(30);
        add(new JScrollPane(table), BorderLayout.CENTER);

        paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
        add(paginationPanel, BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app, new String[]{"商品名称", "商品价格"});
        searchPanel.addOnClickListener(this);
        add(searchPanel, BorderLayout.NORTH);
    }

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void pageChange(int curPage, int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> rank = goodsService.getRank(curPage, pageSize, goods);
		priceTableModel.updateData(rank.getData());
	}

	@Override
	public void firstPage(int pageSize) {
		GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> rank = goodsService.getRank(1, pageSize, goods);
		priceTableModel.updateData(rank.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		goods = new Goods();
		try {
			switch (optionIndex) {
                case 0 -> goods.setName(content);
                case 1 -> goods.setPrice(Float.parseFloat(content));
            }
        } catch (NumberFormatException e) {
            return SearchPanel.SearchResult.NAN;
        }
        PageBean<Goods> goodss = ServiceUtil.getService(GoodsService.class).getRank(1, paginationPanel.getPageSize(), goods);
        priceTableModel.updateData(goodss.getData());
        paginationPanel.setPageBean(goodss); // 更新分页面板
        return goodss.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;

    }

    @Override
    public void onCloseButtonCLick() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> rank = goodsService.getRank(1, paginationPanel.getPageSize(), null);
        priceTableModel.updateData(rank.getData());
    }
}
