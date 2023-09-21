package team.skadi.powersellsys.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		try {
			String resource = "team/skadi/powersellsys/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}

}
