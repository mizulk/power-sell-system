package team.skadi.powersellsys;

import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.router.Router;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

/**
 * 主页面
 */
public class App extends JFrame {

	public static final String TITLE = "";
	public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 605;
	public static final int FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT;

	private final Router router;

	static {
		float scale = 0.9f;
		FRAME_MIN_HEIGHT = (int) (FRAME_HEIGHT * scale);
		FRAME_MIN_WIDTH = (int) (FRAME_WIDTH * scale);
	}

	public App() {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width >> 1) - (FRAME_WIDTH >> 1);
		int y = (screenSize.height >> 1) - (FRAME_HEIGHT >> 1);
		setBounds(x, y, FRAME_WIDTH, FRAME_HEIGHT);
		setMinimumSize(new Dimension(FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT));
		router = new Router(this);
		buildLayout();
		setVisible(true);
	}

	private void buildLayout() {
		setLayout(router.getCardLayout());
		// 初始化在router.ViewName类下的所有页面
		for (ViewName panel : ViewName.values()) {
			try {
				BasicView basicView = panel.getTarget().getDeclaredConstructor(App.class).newInstance(this);
				add(basicView, panel.getValue());
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
					 NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public Router useRouter() {
		return router;
	}
}
