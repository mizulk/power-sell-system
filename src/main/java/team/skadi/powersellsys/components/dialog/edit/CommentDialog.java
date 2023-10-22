package team.skadi.powersellsys.components.dialog.edit;

import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.util.Objects;

public class CommentDialog extends EditDialog<Comment> {

	private JTextField userIdField;
	private JTextField powerIdField;
	private JTextField contentField;
	private JSpinner starSpinner;

	public CommentDialog(JFrame frame, int mode) {
		super(frame, mode == ADD_MODE ? "添加评论" : "修改评论", mode);
	}

	@Override
	protected void buildInputLayout() {
		userIdField = new JTextField(TEXT_FIELD_COLUMNS);
		addField("用户id(必填)：", userIdField);

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
		userIdField.setText(String.valueOf(data.getUserId()));
		powerIdField.setText(String.valueOf(data.getPowerId()));
		contentField.setText(data.getContent());
		starSpinner.setValue(data.getStar());

		if (mode == MODIFY_MODE) {
			userIdField.setEditable(false);
			powerIdField.setEditable(false);
		}
	}

	@Override
	protected boolean onConfirmButtonClick() {
		if (userIdField.getText().equals("")
				&& powerIdField.getText().equals("")
				&& contentField.getText().equals("")
		) {
			return error("请输入必填项");
		} else {
			CommentService commentService = ServiceUtil.getService(CommentService.class);

			Comment comment = new Comment();
			comment.setUserId(Integer.valueOf(userIdField.getText()));
			comment.setPowerId(Integer.valueOf(powerIdField.getText()));
			comment.setContent(contentField.getText());
			if (starSpinner.getValue() instanceof Byte b) {
				comment.setStar(b);
			} else {
				comment.setStar(((Integer) starSpinner.getValue()).byteValue());
			}

			if (!ServiceUtil.getService(UserService.class).isUserExist(comment.getUserId())) {
				return error("不存在id为" + comment.getUserId() + "的用户！");
			}

			if (ServiceUtil.getService(GoodsService.class).isGoodsExist(comment.getPowerId())) {
				return error("不存在id为" + comment.getPowerId() + "的电源商品");
			}

			if (data != null
					&& comment.getUserId().equals(data.getUserId())
					&& comment.getPowerId().equals(data.getPowerId())
					&& comment.getContent().equals(data.getContent())
					&& comment.getStar().equals(data.getStar())
			) return error("信息未修改");

			if (Objects.nonNull(data)) {
				data.setUserId(comment.getUserId());
				data.setPowerId(comment.getPowerId());
				data.setContent(comment.getContent());
				data.setStar(comment.getStar());
				commentService.updateComment(data);
				return successAndExit("修改成功");
			} else {
				data = comment;
			}
			commentService.addNewComment(comment);
			return successAndExit("添加成功");
		}
	}
}
