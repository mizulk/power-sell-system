package team.skadi.powersellsys.components.user;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.components.BasicComponent;
import team.skadi.powersellsys.components.PaginationPanel;
import team.skadi.powersellsys.components.SearchPanel;
import team.skadi.powersellsys.model.user.EvaluationTableModel;
import team.skadi.powersellsys.pojo.Comment;
import team.skadi.powersellsys.pojo.PageBean;
import team.skadi.powersellsys.service.CommentService;
import team.skadi.powersellsys.utils.ServiceUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class UserEvaluationPanel extends BasicComponent
		implements PaginationPanel.OnClickListener, SearchPanel.OnClickListener, DataPanel {

	private Comment comment;
	private EvaluationTableModel evaluationTableModel;
	private PaginationPanel paginationPanel;
	private JTable evaluationTable;

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
		JTable table = new JTable(evaluationTableModel);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		add(new JScrollPane(table), BorderLayout.CENTER);

		paginationPanel = new PaginationPanel(app, false);
		paginationPanel.addOnclickListener(this);
		add(paginationPanel, BorderLayout.SOUTH);

		SearchPanel searchPanel = new SearchPanel(app, new String[]{"评价时间"});
		searchPanel.addOnClickListener(this);
		add(searchPanel, BorderLayout.NORTH);
	}

	@Override
	public void initData() {
		if (!evaluationTableModel.hasData()) {
			CommentService commentService = ServiceUtil.getService(CommentService.class);
			PageBean<Comment> commentPageBean = commentService.queryComment(1, paginationPanel.getPageSize(), null);
			paginationPanel.setPageBean(commentPageBean);
			evaluationTableModel.updateData(commentPageBean.getData());
		}
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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void firstPage(int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, null);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void nextPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, null);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void previousPage(int curPage, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, null);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public void jumpTo(int page, int pageSize) {
		CommentService commentService = ServiceUtil.getService(CommentService.class);
		PageBean<Comment> commentPageBean = commentService.queryComment(1, pageSize, null);
		evaluationTableModel.updateData(commentPageBean.getData());
	}

	@Override
	public SearchPanel.SearchResult onSearchButtonClick(int optionIndex, String content) {
		comment = new Comment();
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
}
