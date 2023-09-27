package team.skadi.powersellsys;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.Enumeration;

public class Main {

	public static final String FONT_NAME = "微软雅黑";

	public static final Font DEFAULT_FONT = new Font(FONT_NAME, Font.PLAIN, 18);
	public static final Font MIDDLE_FONT = new Font(FONT_NAME, Font.PLAIN, 28);
	public static final Font TITLE_FONT = new Font(FONT_NAME, Font.PLAIN, 42);
	public static final Font TINY_FONT = new Font(FONT_NAME, Font.PLAIN, 14);

	public static void main(String[] args) {
		// 设置全局字体
		FontUIResource fontResource = new FontUIResource(DEFAULT_FONT);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontResource);
			}
		}

		SwingUtilities.invokeLater(App::new);
	}
}