package algrithm;

import hb.HibernateSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import entity.TCl25;

public class TypeMap {

	public static Map<Integer, String> getTypeMap(){
		Session session = HibernateSessionFactory.getSession();
		Map<Integer, String> map = new HashMap<Integer, String>();
		Query query = session.createQuery("from TCl25");
		List<TCl25> list = query.list();
		for(TCl25 cl25 : list){
			String s = map.get(cl25.getFItemId());
			if(s==null)
				map.put(cl25.getFItemId(), cl25.getFCode());
			else
				map.put(cl25.getFItemId(),s+" | "+cl25.getFCode());
		}
		return map;
	}
	
	@Test
	public void testGetTypeMap(){
		Map<Integer, String> map = getTypeMap();
		System.out.println(map);;
		
	}
}
