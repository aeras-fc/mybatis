package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisRead_ALL {

	public static void main(String args[]) throws IOException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();

		// select contact all contacts
        selectAllStudents(session);  
//		SelectStudent(1,session);
		System.out.println("Records Read Successfully ");
		session.commit();
		session.close(); // finite # of connections
	}

	private static void SelectStudent(int id,SqlSession session) {
		Student student = session.selectOne("mybatis.Student.getById", id);
		if(student != null)
			System.out.println(student.getEmail());
	}

	private static void selectAllStudents(SqlSession session) {
		List<Student> student = session.selectList("mybatis.Student.getAll");

		for (Student st : student) {
			System.out.println(st.getId());
			System.out.println(st.getName());
			System.out.println(st.getBranch());
			System.out.println(st.getPercentage());
			System.out.println(st.getEmail());
			System.out.println(st.getPhone());
		}
	}
} 