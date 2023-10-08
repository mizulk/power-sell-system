package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.view.BasicView;
import team.skadi.powersellsys.view.LoginView;

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
    private CardLayout cardLayout;
    private JPanel jPanel2;

    public SupplierMainView(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        setLayout(new BorderLayout());

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        JLabel label = new JLabel("123");

        JLabel label1 = new JLabel("321");

        label.setFont(Main.TITLE_FONT);
        button = new JButton("退出");

        jPanel.add(Box.createHorizontalStrut(50));
        jPanel.add(label);
        jPanel.add(Box.createHorizontalGlue());
        jPanel.add(label1);
        jPanel.add(Box.createHorizontalGlue());
        jPanel.add(button);
        add(jPanel,BorderLayout.NORTH);

        JPanel jPanel1 = new JPanel(new GridLayout(6,1,10,20));

        button1 = new JButton("上架商品");
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
        add(jPanel1,BorderLayout.WEST);

        jPanel2 = new JPanel();

        cardLayout = new CardLayout();
        jPanel2.setLayout(cardLayout);

        JButton button7 = new JButton("iii");
        jPanel2.add("button7",button7);


        JButton button8 = new JButton("pppp");
        jPanel2.add("button8",button8);

        add(jPanel2,BorderLayout.CENTER);

    }

    @Override
    protected void addListener() {
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button){
            app.useRouter().showPreviousView();
        } else if (source == button1) {
            cardLayout.show(jPanel2,"button8");
        } else if (source == button2) {
            cardLayout.show(jPanel2,"button7");
        }
    }
}
