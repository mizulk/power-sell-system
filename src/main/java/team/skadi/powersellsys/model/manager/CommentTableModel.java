package team.skadi.powersellsys.model.manager;

import team.skadi.powersellsys.model.DataTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.DateUtil;
import team.skadi.powersellsys.utils.ServiceUtil;

import java.util.ArrayList;

public class CommentTableModel extends DataTableModel<Comment> {

	private final String[] columnName = new String[]{"用户id", "电源id", "星", "内容", "评论时间"};

	@Override
	public String getColumnName(int column) {
		return columnName[column];
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
			case 4 -> DateUtil.replaceT(comment.getCreateTime());
			default -> null;
		};
	}

	@Override
	public boolean delRow(int rowIndex) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		boolean b = commentService.delComment(getRow(rowIndex));
		super.delRow(rowIndex);
		return b;
	}
}
