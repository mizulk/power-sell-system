package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.VisitTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowViewPanel extends SupplierPanel
        implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, ListSelectionListener {


    private Goods goods;
    private PaginationPanel paginationPanel;
    private JTable table;
    private VisitTableModel visitTableModel;

    public ShowViewPanel(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        visitTableModel = new VisitTableModel();
        table = new JTable(visitTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getSelectionModel().addListSelectionListener(this);
        add(new JScrollPane(table),BorderLayout.CENTER);

        paginationPanel = new PaginationPanel(app,false);
        paginationPanel.addOnclickListener(this);
        add(paginationPanel,BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app,new String[]{"商品名称"});
        searchPanel.addOnClickListener(this);
        add(searchPanel,BorderLayout.NORTH);
    }

    @Override
    protected void init(){
        goods=new Goods();
        super.init();
    }

    @Override
    public void initData() {
        if (!visitTableModel.hasData()) {
            GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
            PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
            paginationPanel.setPageBean(goodsPageBean);
            visitTableModel.updateData(goodsPageBean.getData());
        }
    }

    @Override
    public void refreshData() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
        paginationPanel.setPageBean(goodsPageBean);
        visitTableModel.updateData(goodsPageBean.getData());
        JOptionPane.showMessageDialog(app, "刷新成功！");
    }

    @Override
    protected void addListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
    }

    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        goods = new Goods();// 每次搜索清空内容（待定）
        switch (optionIndex) {
            
        }
        PageBean<Goods> goodss = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
        visitTableModel.updateData(goodss.getData());
        paginationPanel.setPageBean(goodss); // 更新分页面板
        return goodss.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
    }

    @Override
    public void onCloseButtonCLick() {
        PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), null);
        visitTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void firstPage(int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
        visitTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void nextPage(int curPage, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
        visitTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void previousPage(int curPage, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
        visitTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void jumpTo(int page, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(page, pageSize, goods);
        visitTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean b = table.getSelectedRow() != -1;

    }

}


