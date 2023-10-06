package team.skadi.powersellsys.view.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.Main;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SupplierMainView extends BasicView {

    JButton button;

    public SupplierMainView(App app) {
        super(app);
    }

    @Override
    protected void buildLayout() {
        JLabel label = new JLabel("124");
        label.setFont(Main.TITLE_FONT);
        add(label);

        button = new JButton("abc");

        add(button);
    }

    @Override
    protected void addListener() {
        button.addActionListener((ActionEvent e)->{
            app.useRouter().showPreviousView();
        });
    }
}
