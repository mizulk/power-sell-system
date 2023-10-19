package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Comment;

import java.util.ArrayList;

public class CommentTableModel extends DataTableModel<Comment> {

	private final String[] columnName = new String[]{"用户id", "电源id", "星", "内容"};

	public CommentTableModel() {
		data = new ArrayList<>();
	}

	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Comment comment = data.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> comment.getUserId();
			case 1 -> comment.getPowerId();
			case 2 -> comment.getStar();
			case 3 -> comment.getContent();
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		return super.delRow(rowIndex);
	}
}
