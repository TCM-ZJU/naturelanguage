package db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 将本地数据库（139）导入到远程数据库
 * @author Administrator
 *
 */
public class Local2Remote {

	@Test
	public void test1(){
		Session session_local = hb.HibernateSessionFactory.getSession();
		Session session_139 = hb.HibernateSessionFactory139.getSession();
		
		int c = 290000;
		while(true){
			
			Query query_local = session_local.createQuery("from TTerm");
			query_local.setMaxResults(200);
			query_local.setFirstResult(c);
			c+=200;
			
			List list = query_local.list();
			if(list.size() == 0)
				break;
			
			session_139.beginTransaction();
			for(Object o : list)
				session_139.save(o);
			session_139.getTransaction().commit();
			System.out.println(c);
			
		}
	}
}
