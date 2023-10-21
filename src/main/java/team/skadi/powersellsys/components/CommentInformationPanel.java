package team.skadi.powersellsys.components;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.service.GoodsService;
import team.skadi.powersellsys.service.UserService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;

public class CommentInformationPanel extends BasicComponent {

	private JLabel userNameLabel;
	private JLabel powerNameLabel;
	private JLabel starLabel;
	private JLabel contentLabel;
	private Comment comment;

	public CommentInformationPanel(App app) {
		super(app);
	}

	@Override
	protected void buildLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		ImageLabel imageLabel;

		// (1,1)
		gbc.gridy = 1;
		gbc.insets.set(0, 0, 10, 10);
		imageLabel = new ImageLabel("用户名: ", "/images/user.png");
		add(imageLabel, gbc);
		// (2,1)
		userNameLabel = new JLabel();
		add(userNameLabel, gbc);

		// (3,1)
		imageLabel = new ImageLabel("电源名称：", "/images/goods.png");
		add(imageLabel, gbc);
		// (4,1)
		powerNameLabel = new JLabel();
		add(powerNameLabel, gbc);

		gbc.gridy++;
		// (1,2)
		imageLabel = new ImageLabel("内容：", "/images/comment-content.png");
		add(imageLabel, gbc);
		// (2-4,2)
		gbc.gridwidth = 3;
		contentLabel = new JLabel();
		add(contentLabel, gbc);

		gbc.gridy++;
		// (1,3)
		gbc.gridwidth = 1;
		imageLabel = new ImageLabel("分数：", "/images/collect.png");
		add(imageLabel, gbc);
		// (2-4,3)
		starLabel = new JLabel();
		starLabel.setHorizontalAlignment(JLabel.RIGHT);
		ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/collect-added.png")));
		starLabel.setIcon(image);
		add(starLabel, gbc);
	}

	public void showComment(Comment comment) {
		this.comment = comment;
		String userName = ServiceUtil.getService(UserService.class).getUserNameById(comment.getUserId());
		String powerName = ServiceUtil.getService(GoodsService.class).getGoodsNameById(comment.getPowerId());
		userNameLabel.setText(userName);
		powerNameLabel.setText(powerName);
		contentLabel.setText(comment.getContent());
		starLabel.setText(String.valueOf(comment.getStar()));
	}

	public Comment getComment() {
		return comment;
	}

	@Override
	protected void addListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
