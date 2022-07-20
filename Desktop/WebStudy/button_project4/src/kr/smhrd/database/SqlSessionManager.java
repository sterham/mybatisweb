package kr.smhrd.database;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {

	static SqlSessionFactory sqlSession;

	static {
		String resource = "kr/smhrd/database/Mybatis-config.xml";
		Reader reader;

		try {
			reader = Resources.getResourceAsReader(resource);

			sqlSession = new SqlSessionFactoryBuilder().build(reader);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static SqlSessionFactory getSqlSession() {
		return sqlSession;
	}
}
