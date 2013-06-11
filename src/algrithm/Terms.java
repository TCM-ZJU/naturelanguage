package algrithm;

import hb.HibernateSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import entity.TTerm;

public class Terms {

	static private Map<Integer, String> map;
	static Logger logger = Logger.getLogger(Terms.class);
	
	public static void init(){
		
		map = new HashMap<Integer, String>();
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm");
//		query.setParameter(0, "麦芽茵陈茶");
		int start=0;
		int maxRst=3000;
		List<TTerm> list=null;
		
		logger.info("初始化 terms，中医药概念词...");
		while(list==null || list.size()!=0){
			query.setFirstResult(start);
			query.setMaxResults(maxRst);
			list = query.list();
			
			for(TTerm term:list){
				if(map.get(term.getConceptId())!=null){
					String t=map.get(term.getConceptId())+ " " +term.getTermLabel();
					map.put(term.getConceptId(), t);
				}else{
					map.put(term.getConceptId(), term.getTermLabel());
				}
			}
//			System.out.println(start);
			start+=maxRst;
			session.clear();
		}
		logger.info("成功初始化 terms，中医药概念词。概念词数量："+map.size());
	}
	
	/**
	 * 对应的中医药概念名称
	 * @param key
	 * @return
	 */
	public static String mapLabel(int key){
		return map.get(key);
	}
	
}
