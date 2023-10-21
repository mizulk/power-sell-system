package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.JudgeTableModel;
import team.skadi.powersellsys.model.supplier.VisitTableModel;
import team.skadi.powersellsys.pojo.Judge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowDescribePanel extends BasicComponent implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {
    public ShowDescribePanel(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        JudgeTableModel judgeTableModel = new JudgeTableModel();
        JTable table = new JTable(judgeTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        add(new JScrollPane(table),BorderLayout.CENTER);

        PaginationPanel paginationPanel = new PaginationPanel(app,false);
        paginationPanel.addOnclickListener(this);
        add(paginationPanel,BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app,new String[]{"用户账号","商品名称"});
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
    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        return null;
    }

    @Override
    public void onCloseButtonCLick() {

    }
}
