package team.skadi.powersellsys.components.manager;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.information.CommentInformationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.components.dialog.BasicDialog;
import team.skadi.powersellsys.components.dialog.edit.CommentDialog;
import team.skadi.powersellsys.components.dialog.edit.EditDialog;
import team.skadi.powersellsys.model.manager.CommentTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

public class ManageCommentPanel extends ManagePanel {

	private Comment comment;
	private CommentTableModel commentTableModel;
	private JTable commentTable;
	private JButton addCommentBtn;
	private JButton delCommentBtn;
	private JButton modifyCommentBtn;
	private JButton showCommentBtn;
	private JButton refreshBtn;

	public ManageCommentPanel(App app) {
		super(app);
	}

	@Override
	protected void init() {
		commentTableModel = new CommentTableModel();
		comment = new Comment();
		super.init();
	}

	@Override
	protected JPanel getSearchPanel() {
		SearchPanel searchPanel = new SearchPanel(app, new String[]{"用户id", "电源id", "内容", "星"});
		searchPanel.addOnClickListener(this);
		return searchPanel;
	}

	@Override
	protected JTable getTable() {
		commentTable = new JTable(commentTableModel);
		commentTable.setRowHeight(30);
		commentTable.getTableHeader().setReorderingAllowed(false);
		commentTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		commentTable.getSelectionModel().addListSelectionListener(this);
		return commentTable;
	}

	@Override
	protected JPanel getBtnPanel() {
		JPanel commentBtnPanel = createBtnPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.insets.set(0, 20, 30, 20);

		addCommentBtn = new JButton("添加评论");
		commentBtnPanel.add(addCommentBtn, gbc);

		delCommentBtn = new JButton("删除评论");
		delCommentBtn.setEnabled(false);
		commentBtnPanel.add(delCommentBtn, gbc);

		modifyCommentBtn = new JButton("修改评论");
		modifyCommentBtn.setEnabled(false);
		commentBtnPanel.add(modifyCommentBtn, gbc);

		showCommentBtn = new JButton("查看评论");
		showCommentBtn.setEnabled(false);
		commentBtnPanel.add(showCommentBtn, gbc);

		refreshBtn = new JButton("刷新数据");
		commentBtnPanel.add(refreshBtn, gbc);

		return commentBtnPanel;
	}

	@Override
	public void initData() {
		if (!commentTableModel.hasData()) {
			CommentService commentService = ServiceUtil.getService(CommentService.class);
			PageBean<Comment> commentPageBean = commentService.queryComment(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(commentPageBean);
			commentTableModel.updateData(commentPageBean.getData());
		}
	}

	@Override
	public void refreshData() {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(paginationPanel.getCurPage(), paginationPanel.getPageSize(), comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	protected void addListener() {
		addCommentBtn.addActionListener(this);
		delCommentBtn.addActionListener(this);
		modifyCommentBtn.addActionListener(this);
		showCommentBtn.addActionListener(this);
		refreshBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addCommentBtn) {
			addComment();
		} else if (source == delCommentBtn) {
			delComment();
		} else if (source == modifyCommentBtn) {
			modifyComment();
		} else if (source == showCommentBtn) {
			showComment();
		} else if (source == refreshBtn) {
			refreshData();
		}
	}

	private void addComment() {
		CommentDialog commentDialog = new CommentDialog(app, EditDialog.ADD_MODE);
		if (commentDialog.getOption() == BasicDialog.CONFIRM_OPTION
				&& paginationPanel.isLastPage()
				&& paginationPanel.getLeftRecord() < paginationPanel.getPageSize())
			commentTableModel.addRow(commentDialog.getData());
	}

	private void delComment() {
		commentTableModel.delRow(commentTable.getSelectedRow());
	}

	private void modifyComment() {
		CommentDialog commentDialog = new CommentDialog(app, EditDialog.MODIFY_MODE);
		commentDialog.setData(commentTableModel.getRow(commentTable.getSelectedRow()));
		if (commentDialog.getOption() == BasicDialog.CONFIRM_OPTION)
			commentTableModel.modifyRow(commentTable.getSelectedRow(), commentDialog.getData());
	}

	private void showComment() {
		BasicDialog basicDialog = new BasicDialog(app, "评论详细信息") {
			@Override
			protected JComponent getCenterLayout() {
				CommentInformationPanel commentInformationPanel = new CommentInformationPanel(app);
				commentInformationPanel.showComment(commentTableModel.getRow(commentTable.getSelectedRow()));
				return commentInformationPanel;
			}
		};
		basicDialog.getOption();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean b = commentTable.getSelectedRow() != -1;
		delCommentBtn.setEnabled(b);
		modifyCommentBtn.setEnabled(b);
		showCommentBtn.setEnabled(b);
	}

	@Override
	public void firstPage(int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, paginationPanel.getPageSize(), comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(curPage, pageSize, comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(curPage, pageSize, comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(page, pageSize, comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void pageSizeChange(int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, comment);
		commentTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		comment = new Comment();
		try {
			switch (optionIndex) {
				case 0 -> comment.setUserId(Integer.parseInt(content));
				case 1 -> comment.setPowerId(Integer.parseInt(content));
				case 2 -> comment.setContent(content);
				case 3 -> {
					byte star;
					try {
						star = Byte.parseByte(content);
					} catch (NumberFormatException e) {
						return SearchPanel.SearchResult.NAN;
					}
					comment.setStar(star);
				}
			}
		} catch (NumberFormatException e) {
			return SearchPanel.SearchResult.NAN;
		}
		PageBean<Comment> commentPageBean = ServiceUtil.getService(CommentService.class).queryComment(1, paginationPanel.getPageSize(), comment);
		paginationPanel.setPageBean(commentPageBean);
		commentTableModel.updateData(commentPageBean.getData());
		return commentPageBean.getTotal() == 0 ? SearchPanel.SearchResult.NO_RESULT : SearchPanel.SearchResult.HAVE_RESULT;
	}

	@Override
	public void onCloseButtonCLick() {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, paginationPanel.getPageSize(), null);
		paginationPanel.setPageBean(commentPageBean);
		commentTableModel.updateData(commentPageBean.getData());
	}
}
