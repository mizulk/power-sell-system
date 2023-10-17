package team.skadi.powersellsys.router;

import team.skadi.powersellsys.App;

import java.util.LinkedList;

/**
 * 用于页面的跳转
 */
public class ViewRouter extends Router {
	// 使用栈来模拟类似于游览器的导航效果
	private final LinkedList<ViewName> viewStack;

	private final App app;

	private ViewName top;// 正在显示的页面

	/**
	 * 展示指定页面
	 *
	 * @param from 从那个页面
	 * @param to   跳转到那个页面
	 */
	public void showView(ViewName from, ViewName to) {
		from.getInstance().onHide();
		cardLayout.show(app.getContentPane(), to.getValue());
		to.getInstance().onShow();
		top = to;
		viewStack.push(from);
	}

	/**
	 * 展示上一个页面，类似于返回上一级
	 */
	public void showPreviousView() {
		if (top != null) top.getInstance().onHide();
		ViewName viewName = viewStack.pop();
		cardLayout.show(app.getContentPane(), viewName.getValue());
		viewName.getInstance().onShow();
	}

	public ViewRouter(App app) {
		super();
		this.app = app;
		viewStack = new LinkedList<>();
		app.setLayout(getCardLayout());
	}

}
