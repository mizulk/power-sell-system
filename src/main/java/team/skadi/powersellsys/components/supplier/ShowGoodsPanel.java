package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.GoodsDialog;
import team.skadi.powersellsys.model.supplier.GoodsTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ShowGoodsPanel extends SupplierPanel
        implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, ListSelectionListener {

    private JButton btn1;
    private JButton btn2;
    private JTable table;
    private GoodsTableModel goodsTableModel;
    private PaginationPanel paginationPanel;
    private Goods goods;

    public ShowGoodsPanel(App app) {
        super(app);
    }

    protected void init() {
        goods = new Goods();
        super.init();
    }

    @Override
    public void initData() {
        if (!goodsTableModel.hasData()) {
            GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
            PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, paginationPanel.getPageSize(), null);
            paginationPanel.setPageBean(goodsPageBean);
            goodsTableModel.updateData(goodsPageBean.getData());
        }
    }

    @Override
    public void refreshData() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(paginationPanel.getCurPage(), paginationPanel.getPageSize(), goods);
        paginationPanel.setPageBean(goodsPageBean);
        goodsTableModel.updateData(goodsPageBean.getData());
        JOptionPane.showMessageDialog(app, "刷新成功！");
    }


    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        goodsTableModel = new GoodsTableModel();
        table = new JTable(goodsTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        table.getSelectionModel().addListSelectionListener(this);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        gbc.weightx = 1;

        btn1 = new JButton("添加商品");
        btnPanel.add(btn1, gbc);

        btn2 = new JButton("下架商品");
        btnPanel.add(btn2, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        paginationPanel = new PaginationPanel(app, false);
        paginationPanel.addOnclickListener(this);
        btnPanel.add(paginationPanel, gbc);
        add(btnPanel, BorderLayout.SOUTH);

        SearchPanel searchPanel = new SearchPanel(app, new String[]{"商品名称", "商品型号", "商品库存", "商品单价", "商品状态"});
        searchPanel.addOnClickListener(this);
        add(searchPanel, BorderLayout.NORTH);

    }

    @Override
    protected void addListener() {
        btn1.addActionListener(e -> {
            GoodsDialog goodsDialog = new GoodsDialog(app, EditDialog.ADD_MODE);
            if (goodsDialog.getOption() == BasicDialog.CONFIRM_OPTION
            && paginationPanel.isLastPage() && paginationPanel.getLeftRecord() < paginationPanel.getPageSize()){
                goodsTableModel.addRow(goodsDialog.getData());
            }
        });
        btn2.addActionListener(e -> {
//            int option = new PutOffDialog(app, "下架商品").getOption();
            if (table.getSelectedRow() != -1) {
                GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
                for (int i : table.getSelectedRows()) {
                    Goods row = goodsTableModel.getRow(i);
                    if (btn2.getText().equals("下架商品"))
                        goodsService.putOffShelf(row);
                    else
                        goodsService.putOnShelf(row);
                    goodsTableModel.modifyRow(i, row);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }

    public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
        goods = new Goods();
        try {
            switch (optionIndex) {
                case 0 -> goods.setName(content);
                case 1 -> goods.setModel(content);
                case 2 -> goods.setStock(Integer.parseInt(content));
                case 3 -> goods.setPrice(Float.parseFloat(content));
                case 4 -> goods.setStatus(Integer.parseInt(content));
            }
        } catch (NumberFormatException e) {
            return SearchPanel.SearchResult.NAN;
        }
        PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), goods);
        goodsTableModel.updateData(goodsPageBean.getData());
        paginationPanel.setPageBean(goodsPageBean);
        return goodsPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
    }

    @Override
    public void onCloseButtonCLick() {
        PageBean<Goods> goodsPageBean = ServiceUtil.getService(GoodsService.class).queryGoods(1, paginationPanel.getPageSize(), null);
        goodsTableModel.updateData(goodsPageBean.getData());
        paginationPanel.setPageBean(goodsPageBean);
    }

    @Override
    public void firstPage(int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(1, pageSize, goods);
        goodsTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void nextPage(int curPage, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
        goodsTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void previousPage(int curPage, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(curPage, pageSize, goods);
        goodsTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void jumpTo(int page, int pageSize) {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        PageBean<Goods> goodsPageBean = goodsService.queryGoods(page, pageSize, goods);
        goodsTableModel.updateData(goodsPageBean.getData());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean b = table.getSelectedRow() != -1;
        if (b) {
            int bin = 0;
            for (int i : table.getSelectedRows()) {
                Goods row = goodsTableModel.getRow(i);
                bin |= row.getStatus();
            }
            btn2.setText(bin == 1 ? "下架商品" : "上架商品");
        }
    }
}
