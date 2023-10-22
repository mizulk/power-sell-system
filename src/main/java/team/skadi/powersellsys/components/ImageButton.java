package team.skadi.powersellsys.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * 图片按钮，可携带文字
 */
public class ImageButton extends JButton {

	private ImageIcon image;

	/**
	 * 仅图片的图片按钮，后面可以通过方法添加文字
	 *
	 * @param res 图片路径
	 */
	public ImageButton(String res) {
		setImage(res);
		init();
	}

	/**
	 * 有文字描述的图片按钮
	 *
	 * @param text 文字
	 * @param res  图片路径
	 */
	public ImageButton(String text, String res) {
		this(res);
		setText(text);
	}

	/**
	 * 缩放图片
	 *
	 * @param res    图片路径
	 * @param width  缩放后图片宽
	 * @param height 缩放后图片高
	 */
	public ImageButton(String res, int width, int height) {
		this(res);
		setSize(width, height);
	}

	/**
	 * 设置描述文字的路径
	 * <p>
	 * 例如
	 * <blockquote><pre>
	 * ImageButton btn = new ImageButton("res/img.png");
	 * btn.setTextPosition(ImageButton.CENTER, ImageButton.BOTTOM);
	 * </pre></blockquote>
	 * 设置描述文字位置未底部居中
	 * </p>
	 *
	 * @param horizontal 水平位置 {@link #setHorizontalTextPosition(int)}
	 * @param vertical   垂直位置 {@link #setVerticalTextPosition(int)}
	 */
	public void setTextPosition(int horizontal, int vertical) {
		setHorizontalTextPosition(horizontal);
		setVerticalTextPosition(vertical);
	}

	public void setImage(String res) {
		image = new ImageIcon(Objects.requireNonNull(getClass().getResource(res)));
		setIcon(image);
	}

	/**
	 * 设置图片缩放
	 *
	 * @param width  the new width of this component in pixels
	 * @param height the new height of this component in pixels
	 */
	public void setSize(int width, int height) {
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	private void init() {
		setBorderPainted(false);
		setBackground(new Color(204, 227, 255));
		setContentAreaFilled(false);
		setFocusPainted(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isEnabled())
					setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
			}
		});
	}
}
