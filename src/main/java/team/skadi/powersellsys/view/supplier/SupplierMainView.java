package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.components.supplier.*;
import team.skadi.powersellsys.model.supplier.*;
import team.skadi.powersellsys.pojo.Supplier;
import team.skadi.powersellsys.router.PanelRouter;
import team.skadi.powersellsys.service.SupplierService;
import team.skadi.powersellsys.store.SupplierStore;
import team.skadi.powersellsys.utils.LayoutUtil;
import team.skadi.powersellsys.utils.ServiceUtil;
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
    private JLabel nameLabel;
    private ShowViewPanel jPanel4;
    private ShowGoodsPanel jPanel3;
    private ShowOrdersPanel jPanel5;
    private ShowStatementPanel jPanel6;
    private ShowPricePanel jPanel7;
    private ShowDescribePanel jPanel8;

    public SupplierMainView(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        nameLabel = new JLabel();
        nameLabel.setFont(Main.MIDDLE_FONT);
        button = new JButton("退出");

        jPanel.add(Box.createHorizontalStrut(50));
        jPanel.add(nameLabel);
        jPanel.add(Box.createHorizontalGlue());
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

        add(LayoutUtil.createWrapper(jPanel1,15), BorderLayout.WEST);

        jPanel2 = new JPanel();
        router = new PanelRouter(jPanel2);

        jPanel3 = new ShowGoodsPanel(app);
        jPanel2.add("panel3", jPanel3);

        jPanel4 = new ShowViewPanel(app);
        jPanel2.add("panel4", jPanel4);

        jPanel5 = new ShowOrdersPanel(app);
        jPanel2.add("panel5", jPanel5);

        jPanel6 = new ShowStatementPanel(app);
        jPanel2.add("panel6", jPanel6);

        jPanel7 = new ShowPricePanel(app);
        jPanel2.add("panel7", jPanel7);

        jPanel8 = new ShowDescribePanel(app);
        jPanel2.add("panel8", jPanel8);

        jPanel2.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        add(LayoutUtil.createWrapper(jPanel2,15,0,15,15), BorderLayout.CENTER);
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
	public void onShow() {
        SupplierService supplierService = ServiceUtil.getService(SupplierService.class);
        Supplier supplier = supplierService.querySupplier(app.useStore().supplierStore.account());
        nameLabel.setText("姓名：" + supplier.getName());
        jPanel3.initData();
    }

	@Override
	public void onHide() {
        app.useStore().supplierStore = null;
        router.showPanel("panel3");
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
            app.useRouter().showPreviousView();
        } else if (source == button1) {
            router.showPanel("panel3");
            jPanel3.initData();
        } else if (source == button2) {
            router.showPanel("panel4");
            jPanel4.initData();
        } else if (source == button3) {
            router.showPanel("panel5");
            jPanel5.initData();
        } else if (source == button4) {
            router.showPanel("panel6");
        } else if (source == button5) {
            router.showPanel("panel7");
            jPanel7.initData();
        } else if (source == button6) {
            router.showPanel("panel8");
        }
    }
}