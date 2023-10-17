package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.model.supplier.GoodsTableModel;
import team.skadi.powersellsys.model.supplier.VisitTableModel;
import team.skadi.powersellsys.view.supplier.PutOnDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowGoodsPanel extends BasicComponent implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener {


    private JButton btn1;

    public ShowGoodsPanel(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        GoodsTableModel goodsTableModel = new GoodsTableModel();
        JTable table = new JTable(goodsTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.weightx = 1;
        btn1 = new JButton("上架商品");

        btnPanel.add(btn1,gbc);
        JButton btn2 = new JButton("下架商品");
        btnPanel.add(btn2,gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        PaginationPanel paginationPanel = new PaginationPanel(app,false);
        paginationPanel.addOnclickListener(this);
        btnPanel.add(paginationPanel,gbc);
        add(btnPanel, BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app, new String[]{"商品名称","商品库存","商品单价"});
        searchPanel.addOnClickListener(this);
        add(searchPanel, BorderLayout.NORTH);

    }

    @Override
    protected void addListener() {
        btn1.addActionListener(e -> {
            int option = new PutOnDialog(app,"上架商品").getOption();

        });
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
