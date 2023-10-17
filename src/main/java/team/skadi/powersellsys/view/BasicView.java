package team.skadi.powersellsys.view;

import team.skadi.powersellsys.App;
import team.skadi.powersellsys.router.ViewName;

import javax.swing.JPanel;

/**
 * 所有功能面板的基类
 * 继承后需要在 {@link ViewName} 中注册对应的面板名字
 */
public abstract class BasicView extends JPanel {

	protected final App app;

	public BasicView(App app) {
		this.app = app;
		init();
	}

	/**
	 * 初始化参数
	 */
	protected void init() {
		buildLayout();
		addListener();
	}

	/**
	 * 构造页面的详细组件
	 */
	abstract protected void buildLayout();

	/**
	 * 为页面中的组件增加监听事件
	 */
	abstract protected void addListener();

	/**
	 * 生命周期：当页面显示的时候调用
	 */
	abstract public void onShow();

	/**
	 * 生命周期：当页面被隐藏时调用
	 */
	abstract public void onHide();
}
