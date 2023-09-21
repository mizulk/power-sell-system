package team.skadi.powersellsys;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.skadi.powersellsys.mapper.UserMapper;
import team.skadi.powersellsys.pojo.User;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

public class Main {

	public static final Font DEFAULT_FONT = new Font("微软雅黑", Font.PLAIN, 18);
	public static final Font MIDDLE_FONT = new Font("微软雅黑", Font.PLAIN, 28);
	public static final Font TITLE_FONT = new Font("微软雅黑", Font.PLAIN, 42);
	public static final Font TINY_FONT = new Font("微软雅黑", Font.PLAIN, 12);

	public static void main(String[] args) throws IOException {
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

		String resource = "team/skadi/powersellsys/mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.selectAll();
		for (User user : users) {
			System.out.println(user);
		}
		sqlSession.close();
	}
}