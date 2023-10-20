package team.skadi.powersellsys.components.supplier;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;

public abstract class SupplierPanel extends BasicComponent {

    public SupplierPanel(App app) {
        super(app);
    }

    abstract public void initData();

    abstract public void refreshData();
}
