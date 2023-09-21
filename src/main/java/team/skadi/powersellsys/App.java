package team.skadi.powersellsys;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

	public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 610;
	public static final int FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT;

	static {
		FRAME_MIN_HEIGHT = (int) (FRAME_HEIGHT * 0.85);
		FRAME_MIN_WIDTH = (int) (FRAME_WIDTH * 0.85);
	}
	public App(){
		super("");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width >> 1) - (FRAME_WIDTH >> 1);
		int y = (screenSize.height >> 1) - (FRAME_HEIGHT >> 1);
		setBounds(x, y, FRAME_WIDTH, FRAME_HEIGHT);
		setMinimumSize(new Dimension(FRAME_MIN_WIDTH, FRAME_MIN_HEIGHT));
		buildLayout();
		setVisible(true);
	}

	private void buildLayout() {

	}
}
