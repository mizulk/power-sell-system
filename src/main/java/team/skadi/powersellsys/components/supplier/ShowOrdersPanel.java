package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.supplier.OrdersTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowOrdersPanel extends BasicComponent implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {

    private JButton btn;

    public ShowOrdersPanel(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        OrdersTableModel ordersTableModel = new OrdersTableModel();
        JTable table = new JTable(ordersTableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnpanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.weightx = 1;
        btn = new JButton("添加库存");
        btnpanel.add(btn, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        PaginationPanel paginationPanel = new PaginationPanel(app, false);
        paginationPanel.addOnclickListener(this);
        btnpanel.add(paginationPanel, gbc);
        add(btnpanel, BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app, new String[]{"电源类型", "电源型号", "订购日期"});
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
