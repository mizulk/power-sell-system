package team.skadi.powersellsys.router;

import team.skadi.powersellsys.App;

import java.awt.CardLayout;
import java.util.LinkedList;

/**
 * 用于页面的跳转
 */
public class Router {
	// 使用栈来模拟类似于游览器的导航效果
	private final LinkedList<ViewName> viewStack;

	private final App app;

	private final CardLayout cardLayout;


	/**
	 * 展示指定页面
	 * @param from 从那个页面
	 * @param to 跳转到那个页面
	 */
	public void showView(ViewName from, ViewName to) {
		cardLayout.show(app.getContentPane(), to.getValue());
		viewStack.push(from);
	}

	/**
	 * 展示上一个页面，类似于返回上一级
	 */
	public void showPreviousView() {
		cardLayout.show(app.getContentPane(), viewStack.pop().getValue());
	}

	public Router(App app) {
		this.app = app;
		cardLayout = new CardLayout();
		viewStack = new LinkedList<>();
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
}
