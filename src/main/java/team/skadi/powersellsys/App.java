package team.skadi.powersellsys;

import team.skadi.powersellsys.router.PanelName;
import team.skadi.powersellsys.router.Router;
import team.skadi.powersellsys.view.LoginView;
import team.skadi.powersellsys.view.SelectLoginView;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		LoginView loginView = new LoginView(this);
//		add(loginView, PanelName.LOGIN_PANEL.getValue());
		add(new SelectLoginView(this), PanelName.SELECT_LOGIN_PANEL.getValue());
	}

	public Router useRouter() {
		return router;
	}
}
