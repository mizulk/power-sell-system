package team.skadi.powersellsys;

import team.skadi.powersellsys.router.ViewName;
import team.skadi.powersellsys.router.ViewRouter;
import team.skadi.powersellsys.store.Store;
import team.skadi.powersellsys.view.BasicView;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

/**
 * 主页面
 */
public class App extends JFrame {

	public static final String TITLE = "铌干犸电力有限公司";
	public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 605;
	public static final int FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT;
	private final ViewRouter viewRouter;
	private final Store store;

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
		viewRouter = new ViewRouter(this);
		store = new Store();
		buildLayout();
		setVisible(true);
	}

	private void buildLayout() {
		// 初始化在router.ViewName类下的所有页面
		for (ViewName panel : ViewName.values()) {
			try {
				BasicView basicView = panel.getTarget().getDeclaredConstructor(App.class).newInstance(this);
				add(basicView, panel.getValue());
				panel.setInstance(basicView);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException |
					 NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public ViewRouter useRouter() {
		return viewRouter;
	}

	public Store useStore() {
		return store;
	}
}
