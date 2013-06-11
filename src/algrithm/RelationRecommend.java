package algrithm;

import hb.HibernateSessionFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import entity.TAssociativeRels;
import entity.TConceptRels;
import entity.TTerm;

public class RelationRecommend {

	public static  Map<TAssociativeRels, Integer> relationRecommend(TTerm t1, TTerm t2){
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.createQuery("from TConceptRels where childId = ?");
		query.setParameter(0, t1.getConceptId());
		List<TConceptRels> ft1 = query.list();
		System.out.println(ft1.size());
		
//		query = session.createQuery("from TConceptRels where childId = ?");
		query.setParameter(0, t2.getConceptId());
		List<TConceptRels> ft2 = query.list();
		TConceptRels cpt = ft2.get(0);
		TTerm term = (TTerm)cpt.getChildTerms().toArray()[0];
		System.out.println(term.getTermLabel());
		System.out.println(ft2.size());
		
		return null;
	}
	
	@Test
	public void testRelationRecommend(){
		TTerm t1 = new TTerm();
		t1.setConceptId(33606);
		
		TTerm t2 = new TTerm();
		t2.setConceptId(164427);
		
		relationRecommend(t1, t2);
		
	}
}
