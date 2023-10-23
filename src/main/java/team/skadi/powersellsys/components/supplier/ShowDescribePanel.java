package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.JudgeTableModel;
import team.skadi.powersellsys.model.supplier.SupplyTableModel;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.pojo.Judge;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class ShowDescribePanel extends SupplierPanel implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {
    private Judge judge;

    private PaginationPanel paginationPanel;
    private JudgeTableModel judgeTableModel;

    public ShowDescribePanel(App app) {
        super(app);
    }

    @Override
    public void initData() {
        CommentService service = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = service.queryJudge(1, paginationPanel.getPageSize(), null);
        paginationPanel.setPageBean(supplyPageBean);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void refreshData() {
        CommentService service = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = service.queryJudge(1, paginationPanel.getPageSize(), null);
        paginationPanel.setPageBean(supplyPageBean);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        judgeTableModel = new JudgeTableModel();
        JTable table = new JTable(judgeTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        add(new JScrollPane(table),BorderLayout.CENTER);

        paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
        add(paginationPanel,BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app,new String[]{"电源名称","用户账号","评分","评论内容","评论时间"});
        searchPanel.addOnClickListener(this);
        add(searchPanel,BorderLayout.NORTH);
    }

    @Override
    protected void addListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        judge = new Judge();
        try {
            switch (optionIndex) {
                case 0 -> judge.setPowerName(content);
                case 1 -> judge.setUserAccount(content);
                case 2 -> judge.setStar(Byte.parseByte(content));
                case 3 -> judge.setContent(content);
                case 4 -> judge.setCreateTime(LocalDateTime.parse(content));
            }
        } catch (NumberFormatException e) {
            return SearchPanel.SearchResult.NAN;
        }
        PageBean<Judge> supplys = ServiceUtil.getService(CommentService.class).queryJudge(1, paginationPanel.getPageSize(), judge);
        judgeTableModel.updateData(supplys.getData());
        paginationPanel.setPageBean(supplys); // 更新分页面板
        return supplys.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
    }

    @Override
    public void onCloseButtonCLick() {
        PageBean<Judge> supplyPageBean = ServiceUtil.getService(CommentService.class).queryJudge(1, paginationPanel.getPageSize(), null);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void firstPage(int pageSize) {
        CommentService commentService = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = commentService.queryJudge(1, pageSize, judge);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void nextPage(int curPage, int pageSize) {
        CommentService commentService = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = commentService.queryJudge(curPage, pageSize, judge);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void previousPage(int curPage, int pageSize) {
        CommentService commentService = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = commentService.queryJudge(curPage, pageSize, judge);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void jumpTo(int page, int pageSize) {
        CommentService commentService = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = commentService.queryJudge(page, pageSize, judge);
        judgeTableModel.updateData(supplyPageBean.getData());
    }

    @Override
    public void pageSizeChange(int pageSize) {
        CommentService commentService = ServiceUtil.getService(CommentService.class);
        PageBean<Judge> supplyPageBean = commentService.queryJudge(1, pageSize, judge);
        judgeTableModel.updateData(supplyPageBean.getData());
    }
}
