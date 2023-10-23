package team.skadi.powersellsys.components.dialog.select;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.information.GoodsInformationPanel;
import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Goods;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class SelectPowerDialog extends SelectDialog<Goods> {

	public SelectPowerDialog(JFrame frame) {
		super(frame, "选择电源");
	}

	public SelectPowerDialog(JDialog owner) {
		super(owner, "选择电源");
	}

	@Override
	protected DataTableModel<Goods> getTableModel() {
		return new DataTableModel<>() {
			private static final String[] columnName = new String[]{"电源id", "电源名称", "电源型号"};

			@Override
			public int getColumnCount() {
				return columnName.length;
			}

			@Override
			public String getColumnName(int column) {
				return columnName[column];
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Goods goods = data.get(rowIndex);
				return switch (columnIndex) {
					case 0 -> goods.getId();
					case 1 -> goods.getName();
					case 2 -> goods.getModel();
					default -> null;
				};
			}
		};
	}

	@Override
	protected BasicComponent getInformationPanel() {
		GoodsInformationPanel goodsInformationPanel = new GoodsInformationPanel((App) getParent());
		goodsInformationPanel.showGoods(dataTableModel.getRow(dataTable.getSelectedRow()));
		return goodsInformationPanel;
	}

	@Override
	protected int getSelectedValue() {
		return dataTableModel.getRow(dataTable.getSelectedRow()).getId();
	}

	@Override
	protected void initData() {
		GoodsService service = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = service.queryGoods(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(goodsPageBean);
		dataTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		GoodsService service = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = service.queryGoods(curPage, paginationPanel.getPageSize(), null);
		dataTableModel.updateData(goodsPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		GoodsService service = ServiceUtil.getService(GoodsService.class);
		PageBean<Goods> goodsPageBean = service.queryGoods(curPage, paginationPanel.getPageSize(), null);
		dataTableModel.updateData(goodsPageBean.getData());
	}
}
