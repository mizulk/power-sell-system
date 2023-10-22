package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.Objects;

public class CommentDialog extends UserCommentDialog {

	protected JTextField userIdField;

	public CommentDialog(JFrame frame, int mode) {
		super(frame, mode);
	}

	@Override
	protected void buildInputLayout() {
		userIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("用户id(必填)：", userIdField);

		super.buildInputLayout();
	}

	@Override
	public void setData(Comment data) {
		super.setData(data);
		userIdField.setText(String.valueOf(data.getUserId()));

		if (mode == MODIFY_MODE) {
			userIdField.setEditable(false);
		}
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (isInputted()) {
			return error("请输入必填项");
		} else {
			CommentService commentService = ServiceUtil.getService(CommentService.class);

			Comment comment = createData();

			if (!ServiceUtil.getService(UserService.class).isUserExist(comment.getUserId())) {
				return error("不存在id为" + comment.getUserId() + "的用户！");
			}

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(comment.getPowerId())) {
				return error("不存在id为" + comment.getPowerId() + "的电源商品");
			}

			if (Objects.nonNull(data)) {
				modifyData(comment);
				commentService.updateComment(data);
				return successAndExit("修改成功");
			} else {
				data = comment;
			}
			commentService.addNewComment(comment);
			return successAndExit("添加成功");
		}
	}

	@Override
	protected Comment createData() {
		Comment comment = super.createData();
		comment.setUserId(Integer.valueOf(userIdField.getText()));
		return comment;
	}

	@Override
	protected boolean isInputted() {
		return super.isInputted() && userIdField.getText().equals("");
	}

	@Override
	protected boolean isModify(Comment comment) {
		return super.isModify(comment) && comment.getUserId().equals(data.getUserId());
	}

	@Override
	protected void modifyData(Comment comment) {
		data.setUserId(comment.getUserId());
		super.modifyData(comment);
	}
}
