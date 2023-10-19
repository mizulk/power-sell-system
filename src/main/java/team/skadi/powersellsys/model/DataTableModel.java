package team.skadi.powersellsys.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * 对表格模型实现基础的增删改查操作
 * <p>
 * 如何实现可以参考{@link team.skadi.powersellsys.model.manager.UserTableModel}
 * </p>
 * <p>
 * 如何使用可以参考{@link team.skadi.powersellsys.components.manager.ManageUserPanel}
 * </p>
 *
 * @param <T> pojo里面的类
 */
public abstract class DataTableModel<T> extends AbstractTableModel {

	protected List<T> data;

	@Override
	public int getRowCount() {
		return data == null ? 0 : data.size();
	}

	/**
	 * 更新表格数据
	 *
	 * @param data 新的数据
	 */
	public void updateData(List<T> data) {
		this.data = data;
		fireTableDataChanged();// 让整个表格重新渲染
	}

	/**
	 * 添加一行新记录，当当前页数是最后一页并且每页满时添加
	 *
	 * @param t 需要添加的新记录
	 */
	public void addRow(T t) {
		data.add(t);
		fireTableRowsInserted(data.size(), data.size());// 让表格渲染新增加的记录
	}

	/**
	 * 删除当前页的一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @return true 删除成功
	 */
	public boolean delRow(int rowIndex) {
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);// 让表格移除删除的那一列
		return true;
	}

	/**
	 * 修改当前页的指定一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @param t        修改后的记录
	 */
	public void modifyRow(int rowIndex, T t) {
		data.set(rowIndex, t);
		fireTableRowsUpdated(rowIndex, rowIndex);// 让表格更新修改的行
	}

	/**
	 * 获取当前页中的一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @return 指定行的记录
	 */
	public T getRow(int rowIndex) {
		return data.get(rowIndex);
	}

	/**
	 * 是否有数据
	 *
	 * @return true 有数据
	 */
	public boolean hasData() {
		return getRowCount() != 0;// getRowCount()中进行了非空判断
	}
}
