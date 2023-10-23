package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.StatementTableModel;
import team.skadi.powersellsys.pojo.Statement;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Statement;
import team.skadi.powersellsys.service.OrderService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowStatementPanel extends SupplierPanel implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {

    private Statement statement;
    private PaginationPanel paginationPanel;
    private StatementTableModel statementTableModel;
    private JTable table;

    public ShowStatementPanel(App app) {
        super(app);
    }

    @Override
    protected void init() {
        statement = new Statement();
        super.init();
    }

    @Override
    public void initData() {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(1, paginationPanel.getPageSize(), null);
        paginationPanel.setPageBean(rank);
        statementTableModel.updateData(rank.getData());

    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        statementTableModel = new StatementTableModel();
        table = new JTable(statementTableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
        add(paginationPanel, BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app, new String[]{"电源id", "电源名称", "订购数量", "订购金额"});
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
    public void firstPage(int pageSize) {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(1, pageSize, statement);
        statementTableModel.updateData(rank.getData());
    }

    @Override
    public void nextPage(int curPage, int pageSize) {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(curPage, pageSize, statement);
        statementTableModel.updateData(rank.getData());
    }

    @Override
    public void previousPage(int curPage, int pageSize) {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(curPage, pageSize, statement);
        statementTableModel.updateData(rank.getData());
    }

    @Override
    public void jumpTo(int page, int pageSize) {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(page, pageSize, statement);
        statementTableModel.updateData(rank.getData());
    }

    @Override
    public void pageSizeChange(int pageSize) {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(1, pageSize, statement);
        statementTableModel.updateData(rank.getData());
    }

    @Override
    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        statement = new Statement();
        try {
            switch (optionIndex) {
                case 0 -> statement.setPowerId(Integer.parseInt(content));
                case 1 -> statement.setPowerName(content);
                case 2 -> statement.setSum(Integer.parseInt(content));
                case 3 -> statement.setTotalPrice(Float.parseFloat(content));
            }
        } catch (NumberFormatException e) {
            return SearchPanel.SearchResult.NAN;
        }
        PageBean<Statement> orders = ServiceUtil.getService(OrderService.class).queryStatement(1, paginationPanel.getPageSize(), statement);
        statementTableModel.updateData(orders.getData());
        paginationPanel.setPageBean(orders);
        return orders.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
    }


    @Override
    public void onCloseButtonCLick() {
        OrderService orderService = ServiceUtil.getService(OrderService.class);
        PageBean<Statement> rank = orderService.queryStatement(1, paginationPanel.getPageSize(), null);
        statementTableModel.updateData(rank.getData());
    }
}
