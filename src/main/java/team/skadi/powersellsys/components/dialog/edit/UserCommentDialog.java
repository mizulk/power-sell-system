package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;

public class UserCommentDialog extends EditDialog<Comment> {

	protected JTextField powerIdField;
	protected JTextField contentField;
	protected JSpinner starSpinner;

	public UserCommentDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加评论" : "修改评论", mode);
	}

	@Override
	protected void buildInputLayout() {

		powerIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("电源id(必填)：", powerIdField);

		contentField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("评论内容(必填)：", contentField);

		starSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		addField("分数：", starSpinner);
	}

	@Override
	public void setData(Comment data) {
		super.setData(data);
		powerIdField.setText(String.valueOf(data.getPowerId()));
		if (data.getContent() != null)
			contentField.setText(data.getContent());
		if (data.getStar() != null)
			starSpinner.setValue(data.getStar());

		if (mode == MODIFY_MODE) {
			powerIdField.setEditable(false);
		}
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			CommentService commentService = ServiceUtil.getService(CommentService.class);
			Comment comment = createData();

			if (isModify(comment)) return error("信息未修改");

			modifyData(comment);
			if (mode == MODIFY_MODE)
				commentService.updateComment(data);
			else
				commentService.addNewComment(data);
			return super.onConfirmButtonClick();
		}
	}

	@Override
	protected Comment createData() {
		Comment comment = new Comment();
		comment.setPowerId(Integer.valueOf(powerIdField.getText()));
		comment.setContent(contentField.getText());
		if (starSpinner.getValue() instanceof Byte b) {
			comment.setStar(b);
		} else {
			comment.setStar(((Integer) starSpinner.getValue()).byteValue());
		}
		return comment;
	}

	@Override
	protected boolean isInputted() {
		return powerIdField.getText().equals("")
				&& contentField.getText().equals("");
	}

	@Override
	protected boolean isModify(Comment comment) {
		return data != null
				&& comment.getPowerId().equals(data.getPowerId())
				&& comment.getContent().equals(data.getContent())
				&& comment.getStar().equals(data.getStar());
	}

	@Override
	protected void modifyData(Comment comment) {
		data.setPowerId(comment.getPowerId());
		data.setContent(comment.getContent());
		data.setStar(comment.getStar());
	}

}
