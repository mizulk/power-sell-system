package team.skadi.powersellsys.view.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.user.*;
import team.skadi.powersellsys.pojo.User;
import team.skadi.powersellsys.router.PanelRouter;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.LayoutUtil;
import team.skadi.powersellsys.utils.ServiceUtil;
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
    private JButton evaluationButton;
    private JLabel nameLabel;
    private JLabel balanceLabel;
    private PanelRouter router;
    private UserPersonalPanel userPersonalPanel;
    private UserFavoritePanel userFavoritePanel;
    private UserDetailPanel userDetailPanel;
    private UserOrderPanel userOrderPanel;
    private UserCustomPanel userCustomPanel;
    private UserEvaluationPanel userEvaluationPanel;


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
        favoriteButton = new JButton("我的收藏");
        detailButton = new JButton("商品详情");
        orderButton = new JButton("提交订单");
        customButton = new JButton("我的定制");
        evaluationButton = new JButton("我的评价");
        panel.add(personalButton);
        panel.add(favoriteButton);
        panel.add(detailButton);
        panel.add(orderButton);
        panel.add(customButton);
        panel.add(evaluationButton);
        JPanel wrapper = LayoutUtil.createWrapper(panel, 20);
        wrapper.setBorder(BorderFactory.createRaisedBevelBorder());
        return wrapper;
    }

    private JPanel getCenterPanel() {
        JPanel panel = new JPanel();
        router = new PanelRouter(panel);
        userPersonalPanel = new UserPersonalPanel(app);
        userFavoritePanel = new UserFavoritePanel(app);
        userDetailPanel = new UserDetailPanel(app);
        userOrderPanel = new UserOrderPanel(app);
        userCustomPanel = new UserCustomPanel(app);
        userEvaluationPanel = new UserEvaluationPanel(app);
        panel.add("personal", getWrapper(userPersonalPanel));
        panel.add("favorite", getWrapper(userFavoritePanel));
        panel.add("detail", getWrapper(userDetailPanel));
        panel.add("order", getWrapper(userOrderPanel));
        panel.add("custom", getWrapper(userCustomPanel));
        panel.add("evaluation", getWrapper(userEvaluationPanel));

        return panel;
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
        evaluationButton.addActionListener(this);
    }

	@Override
	public void onShow() {
        UserService userService = ServiceUtil.getService(UserService.class);
        User user = userService.queryUser(app.useStore().userStore.account());
        nameLabel.setText("昵称：" + user.getName());
        balanceLabel.setText("余额：" + user.getBalance());
        userPersonalPanel.initData();
	}

	@Override
	public void onHide() {
        app.useStore().userStore = null;
        router.showPanel("personal");
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
            userFavoritePanel.initData();
        } else if (source == detailButton) {
            router.showPanel("detail");
            userDetailPanel.initData();
        } else if (source == orderButton) {
            router.showPanel("order");
            userOrderPanel.initData();
        } else if (source == customButton) {
            router.showPanel("custom");
            userCustomPanel.initData();
        } else if (source == evaluationButton) {
            router.showPanel("evaluation");
            userEvaluationPanel.initData();
        }
    }
}
