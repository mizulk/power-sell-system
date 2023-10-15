package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.components.dialog.BasicDialog;
import java.awt.GridBagConstraints;
import javax.swing.*;
import java.awt.*;

public class PutOnDialog extends BasicDialog {


    public PutOnDialog(JFrame frame, String title) {
        super(frame, title);
    }

    @Override
    protected JComponent getCenterLayout() {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JLabel jLabel1 = new JLabel("电源名称：");
        JTextField nameTextField = new JTextField(20);
        JLabel jLabel2 = new JLabel("电源类型");
        JTextField typeTextField = new JTextField(20);
        JLabel jLabel3 = new JLabel("库存");
        JTextField stockTextField = new JTextField(20);
        panel.add(jLabel1);
        panel.add(nameTextField);
        panel.add(jLabel2);
        panel.add(typeTextField);
        panel.add(jLabel3);
        panel.add(stockTextField);
        return panel;
    }

    @Override
    protected boolean onConfirmButtonClick() {
        return super.onConfirmButtonClick();
    }
}
