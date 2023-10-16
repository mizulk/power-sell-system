package team.skadi.powersellsys.view.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.router.PanelRouter;
import team.skadi.powersellsys.utils.LayoutUtil;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainView extends BasicView implements ActionListener {

    private ImageButton exitButton;
    private JButton personalButton;
    private JButton favoriteButton;
    private JButton detailButton;
    private JButton orderButton;
    private JButton customButton;
    private JButton evaluateButton;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private PanelRouter router;


    public UserMainView(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());
        add(getNorthPanel(), BorderLayout.NORTH);
        add(getWestPanel(), BorderLayout.WEST);
        add(getCenterPanel(), BorderLayout.CENTER);
    }

    private JPanel getNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(25));

        nameLabel = new JLabel("昵称：");
        panel.add(nameLabel);
        panel.add(Box.createHorizontalGlue());

        balanceLabel = new JLabel("余额：");
        panel.add(balanceLabel);
        panel.add(Box.createHorizontalGlue());

        exitButton = new ImageButton("/images/logout.png");
        exitButton.setTextPosition(ImageButton.RIGHT, ImageButton.CENTER);
        panel.add(exitButton);

        panel.add(Box.createHorizontalStrut(25));

        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        return panel;
    }

    private JPanel getWestPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 20));

        personalButton = new JButton("个人信息");
        favoriteButton = new JButton("收藏");
        detailButton = new JButton("商品详情");
        orderButton = new JButton("提交订单");
        customButton = new JButton("定制");
        evaluateButton = new JButton("评价");
        panel.add(personalButton);
        panel.add(favoriteButton);
        panel.add(detailButton);
        panel.add(orderButton);
        panel.add(customButton);
        panel.add(evaluateButton);
        JPanel wrapper = LayoutUtil.createWrapper(panel, 20);
        wrapper.setBorder(BorderFactory.createRaisedBevelBorder());
        return wrapper;
    }

    private JPanel getCenterPanel() {
        JPanel panel = new JPanel();
        router = new PanelRouter(panel);

        panel.add("personal", getPersonalPanel());
        panel.add("favorite", getFavoritePanel());
        panel.add("detail", getDetailPanel());
        panel.add("order", getOrderPanel());
        panel.add("custom", getCustomPanel());
        panel.add("evaluate", getEvaluatePanel());

        return panel;
    }

    private JPanel getEvaluatePanel() {
        JPanel evaluatePanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        evaluatePanel.add(table, BorderLayout.CENTER);
        return getWrapper(evaluatePanel);
    }

    private JPanel getCustomPanel() {
        JPanel customPanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        customPanel.add(table, BorderLayout.CENTER);
        return getWrapper(customPanel);
    }

    private JPanel getOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        orderPanel.add(table, BorderLayout.CENTER);
        return getWrapper(orderPanel);
    }

    private JPanel getDetailPanel() {
        JPanel detailPanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        detailPanel.add(table, BorderLayout.CENTER);
        return getWrapper(detailPanel);
    }

    private JPanel getFavoritePanel() {
        JPanel favoritePanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        favoritePanel.add(table, BorderLayout.CENTER);
        return getWrapper(favoritePanel);
    }


    private JPanel getPersonalPanel() {
        JPanel personPanel = new JPanel(new BorderLayout());
        JTable table = new JTable();
        personPanel.add(table, BorderLayout.CENTER);
        return getWrapper(personPanel);
    }

    private JPanel getWrapper(JPanel Panel) {
        JPanel wrapper = LayoutUtil.createWrapper(Panel, 20);
        wrapper.setBorder(BorderFactory.createRaisedBevelBorder());
        return wrapper;
    }

    @Override
    protected void addListener() {
        exitButton.addActionListener(this);
        personalButton.addActionListener(this);
        favoriteButton.addActionListener(this);
        detailButton.addActionListener(this);
        orderButton.addActionListener(this);
        customButton.addActionListener(this);
        evaluateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exitButton) {
            app.useRouter().showPreviousView();
        } else if (source == personalButton) {
            router.showPanel("personal");
        } else if (source == favoriteButton) {
            router.showPanel("favorite");
        } else if (source == detailButton) {
            router.showPanel("detail");
        } else if (source == orderButton) {
            router.showPanel("order");
        } else if (source == customButton) {
            router.showPanel("custom");
        } else if (source == evaluateButton) {
            router.showPanel("evaluate");
        }
    }
}