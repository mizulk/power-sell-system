package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class PutOnDialog extends BasicDialog {


    private JPanel panel;
    private JLabel jLabel1;
    private JTextField nameTextField;
    private JLabel jLabel2;
    private JTextField modelTextField;
    private JLabel jLabel3;
    private JTextField capTextField;
    private JLabel jLabel4;
    private JTextField stockTextField;
    private JLabel jLabel5;
    private JTextField priceTextField;
    private JLabel jLabel6;
    private JTextField discountTextField;


    public PutOnDialog(JFrame frame, String title) {
        super(frame, title);
    }

    @Override
    protected JComponent getCenterLayout() {
        panel = new JPanel(new GridLayout(0, 1));

        jLabel1 = new JLabel("电源名称：");
        nameTextField = new JTextField(20);
        panel.add(jLabel1);
        panel.add(nameTextField);

        jLabel2 = new JLabel("电源类型：");
        modelTextField = new JTextField(20);
        panel.add(jLabel2);
        panel.add(modelTextField);

        jLabel3 = new JLabel("电源容量：");
        capTextField = new JTextField(20);
        panel.add(jLabel3);
        panel.add(capTextField);

        jLabel4 = new JLabel("库存：");
        stockTextField = new JTextField(20);
        panel.add(jLabel4);
        panel.add(stockTextField);

        jLabel5 = new JLabel("单价：");
        priceTextField = new JTextField(20);
        panel.add(jLabel5);
        panel.add(priceTextField);

        jLabel6 = new JLabel("折扣：");
        discountTextField = new JTextField(20);
        panel.add(jLabel6);
        panel.add(discountTextField);

        return panel;
    }

    @Override
    protected boolean onConfirmButtonClick() {
        GoodsService goodsService = ServiceUtil.getService(GoodsService.class);
        if (nameTextField.getText() != "" &&
                modelTextField.getText() != "" &&
                capTextField.getText() != "" &&
                stockTextField.getText() != "" &&
                priceTextField.getText() != "" &&
                discountTextField.getText() != ""
        ) {
			Goods goods = new Goods();
			goods.setName(nameTextField.getText());
			goods.setModel(modelTextField.getText());
			goods.setCapacity(Integer.valueOf(capTextField.getText()));
			goods.setStock(Integer.valueOf(stockTextField.getText()));
			goods.setPrice(Float.parseFloat(priceTextField.getText()));
			goods.setDiscount(Byte.valueOf(discountTextField.getText()));
			goods.setCreateTime(LocalDateTime.now());
			goods.setUpdateTime(LocalDateTime.now());
			goodsService.putOnShelf(goods);
			return super.onConfirmButtonClick();
		} else {
            JOptionPane.showMessageDialog(getParent(), "请填写完整");
            return false;
        }
    }
}