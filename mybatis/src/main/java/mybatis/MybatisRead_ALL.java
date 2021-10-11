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
        //selecting by id.
//		SelectStudent(1,session);
        
        //update the entry.
        update(session, 1);
        
        //To perform delete operation.
        delete(session, 1);
        
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
	
	private static void update(SqlSession session, int id) {
		  Student student = (Student) session.selectOne("mybatis.Student.getById", id);
	      
	      System.out.println("Current details of the student are: " );
	      System.out.println(student.toString());  
	      
	      //Set new values to the mail and phone number of the student
	      student.setEmail("mohamad131@gmail.com");
	      student.setPhone(90000000);
	      
	      //Update the student record
	      session.update("mybatis.Student.update",student);
	      System.out.println("Record updated successfully");   
	  
		  
	      //verifying the record 
	      Student std = (Student) session.selectOne("mybatis.Student.getById", id);
	      
	      System.out.println("Details of the student after update operation" );
	      System.out.println(std.toString());
//	      System.out.println(std.getPhone());
	}
	
	private static void delete(SqlSession session, int id) {
	      session.delete("mybatis.Student.deleteById", id);    
	      System.out.println("Record successfully deleted!");

	}
} 