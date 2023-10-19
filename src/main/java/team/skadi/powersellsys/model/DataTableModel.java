package team.skadi.powersellsys.model;

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
public interface DataTableModel<T> {

	/**
	 * 更新表格数据
	 *
	 * @param data 新的数据
	 */
	void updateData(List<T> data);

	/**
	 * 添加一行新记录，当当前页数是最后一页并且每页满时添加
	 *
	 * @param t 需要添加的新记录
	 */
	void addRow(T t);

	/**
	 * 删除当前页的一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @return true 删除成功
	 */
	boolean delRow(int rowIndex);

	/**
	 * 修改当前页的指定一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @param t        修改后的记录
	 */
	void modifyRow(int rowIndex, T t);

	/**
	 * 获取当前页中的一行记录
	 *
	 * @param rowIndex 当前页的那一行
	 * @return 指定行的记录
	 */
	T getRow(int rowIndex);

	/**
	 * 是否有数据
	 *
	 * @return true 有数据
	 */
	boolean hasData();
}
