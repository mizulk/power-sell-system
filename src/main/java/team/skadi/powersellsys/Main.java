package team.skadi.powersellsys;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

public class Main {

	public static final String FONT_NAME = "微软雅黑";

	public static final Font DEFAULT_FONT = new Font(FONT_NAME, Font.PLAIN, 18);
	public static final Font MIDDLE_FONT = new Font(FONT_NAME, Font.PLAIN, 28);
	public static final Font TITLE_FONT = new Font(FONT_NAME, Font.PLAIN, 42);
	public static final Font TINY_FONT = new Font(FONT_NAME, Font.PLAIN, 14);

	static {
		String configPath = "src/main/resources/mybatis-config.xml";
		String templatePath = "src/main/resources/mybatis-config-template.xml";
		File file = new File(configPath);
		if (!file.exists()) {
			try {
				String password = JOptionPane.showInputDialog("检测到无mybatis配置文件\n请输入你的MySQL密码来生成配置文件：", "123456");
				BufferedReader in = new BufferedReader(new FileReader(templatePath));
				BufferedWriter out = new BufferedWriter(new FileWriter(file));
				String line;
				while ((line = in.readLine()) != null) {
					if (line.contains("password"))
						out.write("\t\t\t\t<property name=\"password\" value=\"" + password + "\"/>\n");
					else
						out.write(line + "\n");
				}
				out.flush();
				out.close();
				in.close();
				JOptionPane.showMessageDialog(null, "创建配置文件成功，如果需要修改请到src/main/resources/mybatis-config.xml下修改密码");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "生成配置文件失败: " + e);
			}
		}
	}

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

		try {
			Class.forName("team.skadi.powersellsys.utils.SqlSessionUtil");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(App::new);
	}
}