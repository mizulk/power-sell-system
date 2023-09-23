package team.skadi.powersellsys.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ImageButton extends JButton {

	private ImageIcon image;

	public ImageButton(String res) {
		image = new ImageIcon(Objects.requireNonNull(getClass().getResource(res)));
		init();
	}

	public ImageButton(String text, String res) {
		this(res);
		setText(text);
	}

	public ImageButton(String res, int width, int height) {
		this(res);
		setSize(width, height);
	}

	public void setTextPosition(int horizontal, int vertical) {
		setHorizontalTextPosition(horizontal);
		setVerticalTextPosition(vertical);
	}

	public void setSize(int width, int height) {
		image.setImage(image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	private void init() {
		setIcon(image);
		setBorderPainted(false);
		setBackground(new Color(204, 227, 255));
		setContentAreaFilled(false);
		setFocusPainted(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
			}
		});
	}
}
