package util;

import hb.HibernateSessionFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.TTerm;

public class OutputTerms {

	public static void main(String[] args) throws FileNotFoundException {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm");
//		query.setParameter(0, "麦芽茵陈茶");
		int start=0;
		int maxRst=3000;
		List<TTerm> list=null;
		String outfile = "D:/eclipse-worksp/mye65/ccntrecuit/relation_identify/other/words-my1.dic";
		PrintWriter pw = new PrintWriter(outfile);
		
		while(list==null || list.size()!=0){
			query.setFirstResult(start);
			query.setMaxResults(maxRst);
			list = query.list();
			
			for(TTerm term:list){
				pw.print(term.getTermLabel()+"\n");
			}
			start+=maxRst;
			session.clear();
			System.out.println(start);
		}
		pw.close();
	}
}
