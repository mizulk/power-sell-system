package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.*;

public class PutOnDialog extends BasicDialog {


    private JPanel panel;
    private JLabel jLabel1;
    private JTextField nameTextField;
    private JLabel jLabel2;
    private JTextField typeTextField;
    private JLabel jLabel3;
    private JTextField capTextField;
    private JLabel jLabel4;
    private JTextField stockTextField;


    public PutOnDialog(JFrame frame, String title) {
        super(frame, title);
    }

    @Override
    protected JComponent getCenterLayout() {
        panel = new JPanel(new GridLayout(0, 1));
        jLabel1 = new JLabel("电源名称");
        nameTextField = new JTextField(20);
        panel.add(jLabel1);
        panel.add(nameTextField);

        jLabel2 = new JLabel("电源类型");
        typeTextField = new JTextField(20);
        panel.add(jLabel2);
        panel.add(typeTextField);

        jLabel3 = new JLabel("电源容量");
        capTextField = new JTextField(20);
        panel.add(jLabel3);
        panel.add(capTextField);

        jLabel4 = new JLabel("库存");
        stockTextField = new JTextField(20);
        panel.add(jLabel4);
        panel.add(stockTextField);

        return panel;
    }

    @Override
    protected boolean onConfirmButtonClick() {
        Goods goods = new Goods();
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        if (nameTextField.getText() != "" &&
                typeTextField.getText() != "" &&
                capTextField.getText() != "" &&
                stockTextField.getText() != ""
        ) {
            goodsService.putOnShelf(goods);
            return super.onConfirmButtonClick();
        }
        return false;
    }
}
