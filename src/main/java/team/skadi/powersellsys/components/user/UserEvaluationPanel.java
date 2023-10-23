package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.ImageButton;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.components.dialog.edit.UserCommentDialog;
import team.skadi.powersellsys.model.user.EvaluationTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class UserEvaluationPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel, ListSelectionListener {

	private Comment comment;
	private EvaluationTableModel evaluationTableModel;
	private PaginationPanel paginationPanel;
	private JTable evaluationTable;
	private ImageButton modifyEvaluationBtn;
	private ImageButton deleteEvaluationBtn;

	public UserEvaluationPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		evaluationTableModel = new EvaluationTableModel();
		comment = new Comment();
		super.init();
	}

	@Override
	protected void buildLayout() {
		setLayout(new BorderLayout());
		evaluationTableModel = new EvaluationTableModel();
		evaluationTable = new JTable(evaluationTableModel);
		evaluationTable.setRowHeight(30);
		evaluationTable.getTableHeader().setReorderingAllowed(false);
		evaluationTable.getSelectionModel().addListSelectionListener(this);
		evaluationTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(evaluationTable), BorderLayout.CENTER);

		JPanel southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.gridy = 1;

		modifyEvaluationBtn = new ImageButton("修改评论", "/images/comment.png");
		modifyEvaluationBtn.setEnabled(false);
		southPanel.add(modifyEvaluationBtn, gbc);

		deleteEvaluationBtn = new ImageButton("删除评论", "/images/delete.png");
		deleteEvaluationBtn.setEnabled(false);
		southPanel.add(deleteEvaluationBtn, gbc);

		gbc.gridwidth = 2;
		gbc.gridy++;
		paginationPanel = new PaginationPanel(app);
		paginationPanel.addOnclickListener(this);
		southPanel.add(paginationPanel, gbc);
		add(southPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"电源id", "评价内容", "评价时间", "评价分数"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!evaluationTableModel.hasData()) {
			comment = new Comment();
			comment.setUserId(app.useStore().userStore.id());
		}
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, paginationPanel.getPageSize(), comment);
		paginationPanel.setPageBean(commentPageBean);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void refreshData() {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(paginationPanel.getCurPage(), paginationPanel.getPageSize(), comment);
		paginationPanel.setPageBean(commentPageBean);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	protected void addListener() {
		modifyEvaluationBtn.addActionListener(this);
		deleteEvaluationBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == modifyEvaluationBtn) {
			modifyEvaluation();
		} else if (source == deleteEvaluationBtn) {
			deleteEvaluation();
		}
	}

	private void deleteEvaluation() {
		int option = JOptionPane.showConfirmDialog(app, "你确定要删除", "warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			if (evaluationTableModel.delRow(evaluationTable.getSelectedRow())) {
				JOptionPane.showMessageDialog(app, "已删除");
			} else {
				JOptionPane.showMessageDialog(app, "删除失败");
			}
		} else {
			JOptionPane.showMessageDialog(app, "已取消");
		}
	}

	private void modifyEvaluation() {
		UserCommentDialog userCommentDialog = new UserCommentDialog(app, EditDialog.MODIFY_MODE);
		userCommentDialog.setData(evaluationTableModel.getRow(evaluationTable.getSelectedRow()));
		if (userCommentDialog.getOption() == BasicDialog.CONFIRM_OPTION) {
			JOptionPane.showMessageDialog(app, "修改成功");
			evaluationTableModel.modifyRow(evaluationTable.getSelectedRow(), userCommentDialog.getData());
		}
	}

	@Override
	public void firstPage(int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, comment);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(curPage, pageSize, comment);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(curPage, pageSize, comment);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(page, pageSize, comment);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, comment);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		comment = new Comment();
		comment.setUserId(app.useStore().userStore.id());
		try {
			switch (optionIndex) {
				case 0 -> comment.setPowerId(Integer.parseInt(content));
				case 1 -> comment.setContent(content);
				case 2 -> comment.setCreateTime(LocalDateTime.parse(content));
				case 3 -> comment.setStar(Byte.parseByte(content));
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Comment> commentPageBean = ServiceUtil.getService(CommentService.class).queryComment(1, paginationPanel.getPageSize(), comment);
		evaluationTableModel.updateData(commentPageBean.getData());
		paginationPanel.setPageBean(commentPageBean);
		return commentPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		PageBean<Comment> commentPageBean = ServiceUtil.getService(CommentService.class).queryComment(1, paginationPanel.getPageSize(), null);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = evaluationTable.getSelectedRow() != -1;
		modifyEvaluationBtn.setEnabled(b);
		deleteEvaluationBtn.setEnabled(b);
	}
}
