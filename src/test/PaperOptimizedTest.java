package test;

import java.util.List;
import java.util.regex.Pattern;

import hb.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import algrithm.Terms;

import entity.TAssociativeRels;
import entity.TTerm;


public class PaperOptimizedTest {

//	@Test
	public void test1()throws Exception{
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm where termLabel = ?");
		query.setParameter(0, "麦芽茵陈茶");
		List<TTerm> list = query.list();
		TTerm term = list.get(0);
		System.out.println(term.getConceptId());
	}
	
//	@Test
	public void testType(){
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TAssociativeRels t where t.type = ?");
		query.setParameter(0, 0);
		List<TAssociativeRels> list = query.list();
//		for(TAssociativeRels rels: list)
//			System.out.println(rels.getLeftTerm().getTermLabel()+ " " +rels.getRightTerm().getTermLabel());
	}
	
//	@Test
	public void test2(){
		Pattern pattern = Pattern.compile("[\u4e00-\u9fff]+");
//		String cn = "八仙糕1";
//		System.out.println(pattern.matcher(cn).matches());
		
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm");
//		query.setParameter(0, "麦芽茵陈茶");
		int start=0;
		int maxRst=500;
		List<TTerm> list=null;
		
		while(list==null || list.size()!=0){
			Transaction tx = session.beginTransaction();
			query.setFirstResult(start);
			query.setMaxResults(maxRst);
			list = query.list();
			System.out.println("s:"+start);
			int count=0;
			for(TTerm term:list){
				if(!pattern.matcher(term.getTermLabel()).matches()){
					System.out.println(term.getTermLabel());
					session.delete(term);
					count++;
				}
			}
			start+=maxRst;
			start-=count;
			session.flush();
			session.clear();
			tx.commit();
		}
		
//		TTerm term = list.get(0);
//		System.out.println(term.getConceptId());
	}
	
//	@Test
	public void test3(){
		long a = System.currentTimeMillis();
		Terms terms = new Terms();
		long b = System.currentTimeMillis();
		System.out.println(b-a);
	}
	
	@Test
	public void test4(){
		Pattern pattern = Pattern.compile("[\u4e00-\u9fff]+");
		String cn = "MA1ZI3CAO3";
		System.out.println(pattern.matcher(cn).matches());
		System.out.println(Terms.mapLabel(123));
	}
}
