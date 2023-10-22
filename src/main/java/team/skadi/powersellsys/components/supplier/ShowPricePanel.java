package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.PriceTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowPricePanel extends SupplierPanel implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {

    private PaginationPanel paginationPanel;
    private PriceTableModel priceTableModel;
    private JTable table;

    public ShowPricePanel(App app) {
        super(app);
    }

    @Override
    public void initData() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> rank = goodsService.getRank(1, paginationPanel.getPageSize());
        paginationPanel.setPageBean(rank);
        priceTableModel.updateData(rank.getData());
    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        priceTableModel = new PriceTableModel();
        table = new JTable(priceTableModel);
        table.setRowHeight(30);
        add(new JScrollPane(table),BorderLayout.CENTER);

        paginationPanel = new PaginationPanel(app,false);
        paginationPanel.addOnclickListener(this);
        add(paginationPanel,BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app,new String[]{"商品名称","商品价格"});
        searchPanel.addOnClickListener(this);
        add(searchPanel,BorderLayout.NORTH);
    }

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
	public void pageSizeChange(int pageSize) {

	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		return null;
	}

	@Override
	public void onCloseButtonCLick() {

	}
}
