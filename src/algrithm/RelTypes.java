package algrithm;

import hb.HibernateSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import entity.TCl25;


public class RelTypes {
	static private Map<Integer, String> map;
	static Logger logger = Logger.getLogger(RelTypes.class);
	
	public static void init(){
		
		map = new HashMap<Integer, String>();
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TCl25");
//		query.setParameter(0, "麦芽茵陈茶");
		int start=0;
		int maxRst=1000;
		List<TCl25> list=null;
		
		logger.info("初始化 TCl25，中医药概念关系...");
		while(list==null || list.size()!=0){
			query.setFirstResult(start);
			query.setMaxResults(maxRst);
			list = query.list();
			
			for(TCl25 term:list){
				if(map.get(term.getFItemId())!=null){
					String t=map.get(term.getFItemId())+ " " +term.getFCode();
					map.put(term.getFItemId(), t);
				}else{
					map.put(term.getFItemId(), term.getFCode());
				}
			}
//			System.out.println(start);
			start+=maxRst;
			session.clear();
		}
		logger.info("成功初始化 TCl25，中医药概念关系，数量："+map.size());
	}
	
	/**
	 * 对应的中医药概念名称
	 * @param key
	 * @return
	 */
	public static String mapRelName(int key){
		return map.get(key);
	}
}
