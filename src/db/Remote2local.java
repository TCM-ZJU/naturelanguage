package db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 将远程数据库（139）导入到本地
 * @author Administrator
 *
 */
public class Remote2local {

	@Test
	public void test1(){
		Session session_local = hb.HibernateSessionFactory.getSession();
		Session session_139 = hb.HibernateSessionFactory139.getSession();
		
		int c = 283800;
		while(true){
			
			Query query_139 = session_139.createQuery("from TUr1023");
			query_139.setMaxResults(200);
			query_139.setFirstResult(c);
			c+=200;
			
			List list = query_139.list();
			if(list.size() == 0)
				break;
			
			session_local.beginTransaction();
			for(Object o : list)
				session_local.save(o);
			session_local.getTransaction().commit();
			System.out.println(c);
			
		}
	}
}
