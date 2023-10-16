package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.model.supplier.*;
import team.skadi.powersellsys.router.PanelRouter;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupplierMainView extends BasicView implements ActionListener {


    JButton button;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;

    private PanelRouter router;
    private JPanel jPanel2;

    public SupplierMainView(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        JLabel label = new JLabel("supplier");

//        JLabel label1 = new JLabel("321");

        label.setFont(Main.TITLE_FONT);
        button = new JButton("退出");

        jPanel.add(Box.createHorizontalStrut(50));
        jPanel.add(label);
        jPanel.add(Box.createHorizontalGlue());
//        jPanel.add(label1);
        jPanel.add(Box.createHorizontalGlue());
        jPanel.add(button);
        add(jPanel, BorderLayout.NORTH);

        JPanel jPanel1 = new JPanel(new GridLayout(6, 1, 10, 20));

        button1 = new JButton("查看商品");
        button2 = new JButton("访问量");
        button3 = new JButton("查看订单");
        button4 = new JButton("统计报表");
        button5 = new JButton("价格排名");
        button6 = new JButton("顾客评价");
        jPanel1.add(button1);
        jPanel1.add(button2);
        jPanel1.add(button3);
        jPanel1.add(button4);
        jPanel1.add(button5);
        jPanel1.add(button6);
        add(jPanel1, BorderLayout.WEST);

        jPanel2 = new JPanel();
        router = new PanelRouter(jPanel2);

        JPanel jPanel3 = getjPanel3();
        jPanel2.add("panel3", jPanel3);

        JPanel jPanel4 = getjPanel4();
        jPanel2.add("panel4", jPanel4);

        JPanel jPanel5 = getjPanel5();
        jPanel2.add("panel5", jPanel5);

        JPanel jPanel6 = getjPanel6();
        jPanel2.add("panel6", jPanel6);

        JPanel jPanel7 = getjPanel7();
        jPanel2.add("panel7", jPanel7);

        JPanel jPanel8 = getjPanel8();
        jPanel2.add("panel8", jPanel8);

        add(jPanel2, BorderLayout.CENTER);
    }

    //顾客评价
    private JPanel getjPanel8() {
        JPanel jPanel8 = new JPanel(new BorderLayout());
        JudgeTableModel judgeTableModel = new JudgeTableModel();
        JTable table = new JTable(judgeTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        jPanel8.add(new JScrollPane(table));
        return jPanel8;
    }


    //价格排名
    private JPanel getjPanel7() {
        PriceTableModel priceTableModel = new PriceTableModel();
        JPanel jPanel7 = new JPanel(new BorderLayout());
        JTable table = new JTable(priceTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        jPanel7.add(new JScrollPane(table));
        return jPanel7;
    }


    //统计报表
    private JPanel getjPanel6() {
        JPanel jPanel6 = new JPanel(new BorderLayout());
        JTable table = new JTable();//TODO： 添加数据模型
//        table.getTableHeader().setReorderingAllowed(false);
        jPanel6.add(new JScrollPane(table));
        return jPanel6;
    }


    //查看订单
    private JPanel getjPanel5() {
        OrdersTableModel ordersTableModel = new OrdersTableModel();
        JPanel jPanel5 = new JPanel(new BorderLayout());
        JTable table = new JTable(ordersTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        jPanel5.add(new JScrollPane(table), BorderLayout.CENTER);
        JButton btn = new JButton("添加库存");
        jPanel5.add(btn, BorderLayout.SOUTH);
        return jPanel5;
    }


    //访问量
    private JPanel getjPanel4() {
        VisitTableModel visitTableModel = new VisitTableModel();
        JPanel jPanel4 = new JPanel(new BorderLayout());
        JTable table = new JTable(visitTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        jPanel4.add(new JScrollPane(table));
        return jPanel4;
    }

    //查看商品
    private JPanel getjPanel3() {
        JPanel jPanel3 = getjPanel4();
        GoodsTableModel goodsTableModel = new GoodsTableModel();
        JTable table = new JTable(goodsTableModel);
        table.getTableHeader().setReorderingAllowed(false);
        JPanel btnPanel = new JPanel(new GridLayout());
        JButton btn1 = new JButton("上架商品");
        btn1.addActionListener( e -> {
            int option = new PutOnDialog(app,"上架商品").getOption();
        });
        btnPanel.add(btn1);
        JButton btn2 = new JButton("下架商品");
        btnPanel.add(btn2);
        jPanel3.add(btnPanel, BorderLayout.SOUTH);
        jPanel3.add(new JScrollPane(table), BorderLayout.CENTER);
        return jPanel3;
    }

    @Override
    protected void addListener() {
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
            app.useRouter().showPreviousView();
        } else if (source == button1) {
            router.showPanel("panel3");
        } else if (source == button2) {
            router.showPanel("panel4");
        } else if (source == button3) {
            router.showPanel("panel5");
        } else if (source == button4) {
            router.showPanel("panel6");
        } else if (source == button5) {
            router.showPanel("panel7");
        } else if (source == button6) {
            router.showPanel("panel8");
        }
    }
}