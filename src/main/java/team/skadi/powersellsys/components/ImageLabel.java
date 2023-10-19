package team.skadi.powersellsys.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.Objects;

public class ImageLabel extends JLabel {

	public ImageLabel(String label, String src) {
		super(label);
		setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(src))));
		setOpaque(true);
		setBackground(Color.decode("#bbbbbb"));
	}

	/**
	 * 设置描述文字的路径 {@link ImageButton#setTextPosition(int, int)}
	 *
	 * @param horizontal 水平位置 {@link #setHorizontalTextPosition(int)}
	 * @param vertical   垂直位置 {@link #setVerticalTextPosition(int)}
	 */
	public void setTextPosition(int horizontal, int vertical) {
		setHorizontalTextPosition(horizontal);
		setVerticalTextPosition(vertical);
	}
}
