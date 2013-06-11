package algrithm;

import hb.HibernateSessionFactory;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import entity.TTerm;

public class Word2DbId {

	/**
	 * 对于输入的概念中文名称, 在数据库中找到概念的对应信息(包括数据库中的id)
	 * @param words
	 * @return
	 */
	public static LinkedList<TTerm> identifyWordInDB(String words[]){
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm where termLabel = ?");

		String rst = "";
		LinkedList<TTerm> rstlist = new LinkedList<TTerm>();
		
		for (String word : words) {
			query.setParameter(0, word);
			List<TTerm> list = query.list();
			
			if(list.size() > 0) {
				TTerm term = list.get(0);
				rstlist.add(term);
			}
			
//			if (list.size() > 0) {
//				TTerm term = list.get(0);
//				rst += term.getConceptId() + ",";
//			}
//			System.out.println(rst);
			
		}

		return rstlist;
	}
	
	@Test
	public void testIdentifyWordInDB(){
		String words[] = {"麦芽山楂饮", "萝卜饮", "山楂消食茶"};
		List<TTerm> list = identifyWordInDB(words);
		System.out.println(list.size());
		for(TTerm term:list)
			System.out.println(term.getTermLabel());
	}
}
