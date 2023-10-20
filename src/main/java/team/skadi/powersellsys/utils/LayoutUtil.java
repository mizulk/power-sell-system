package team.skadi.powersellsys.utils;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class LayoutUtil {

	/**
	 * 创建外边距占位面板
	 * @param inner 内部组件
	 * @param margin 外边距大小
	 * @return 占位面板
	 */
	public static JPanel createWrapper(Component inner, int margin) {
		return createWrapper(inner, margin, margin, margin, margin);
	}

	public static JPanel createWrapper(Component inner, int top, int left, int bottom, int right) {
		JPanel wrapperPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets.set(top, left, bottom, right);
		wrapperPanel.add(inner, gbc);
		return wrapperPanel;
	}
}
