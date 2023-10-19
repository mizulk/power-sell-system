package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.components.dialog.BasicDialog;

import javax.swing.*;
import java.awt.*;

public class PutOffDialog extends BasicDialog {

    private JPanel panel;
    private JLabel jLabel1;
    private JTextField nameTextField;
    private JLabel jLabel2;
    private JTextField stockTextField;

    public PutOffDialog(JFrame frame, String title) {
        super(frame, title);
    }

    @Override
    protected JComponent getCenterLayout() {
        panel = new JPanel(new GridLayout(0, 1));

        jLabel1 = new JLabel("电源名称：");
        nameTextField = new JTextField(20);
        panel.add(jLabel1);
        panel.add(nameTextField);

        jLabel2 = new JLabel("库存：");
        stockTextField = new JTextField(20);
        panel.add(jLabel2);
        panel.add(stockTextField);

        return panel;
    }

    @Override
    protected boolean onConfirmButtonClick() {

        return false;
    }
}
