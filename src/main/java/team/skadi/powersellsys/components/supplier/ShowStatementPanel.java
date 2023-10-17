package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.GoodsTableModel;
import team.skadi.powersellsys.model.supplier.StatementTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowStatementPanel extends BasicComponent implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {
    public ShowStatementPanel(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        StatementTableModel statementTableModel = new StatementTableModel();
        JTable table = new JTable(statementTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        PaginationPanel paginationPanel = new PaginationPanel(app,false);
        paginationPanel.addOnclickListener(this);
        add(paginationPanel,BorderLayout.SOUTH);

        SearchPanel searchPanel =new SearchPanel(app,new String[]{"电源类型","电源型号","订购日期"});
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
