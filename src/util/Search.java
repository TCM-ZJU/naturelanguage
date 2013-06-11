package util;

import java.lang.annotation.Target;
import java.util.List;

import com.sun.swing.internal.plaf.metal.resources.metal;

public class Search {

	/**
	 * 
	 * @param tagert
	 * @param list
	 * @return -1：没有找到
	 */
	public static int binarysearch(int tagert, List<Integer> list){
		return bs(tagert, list, 0, list.size());
	}
	
	/**
	 *  数组范围[start,end)
	 * @param list
	 * @param start
	 * @param end
	 * @return -1：没有找到
	 */
	private static int bs(int tagert, List<Integer> list,int start,int end){
		if(end - start ==2){
			if(tagert == list.get(start))
				return start;
			if(tagert == list.get(start+1))
				return start+1;
			return -1;
		}
		
		if(end - start == 1){
			if(tagert == list.get(start))
				return start;
			return -1;
		}
		
		int mid = (end +start)/2;
//		System.out.println(mid+" "+list.get(mid)+" ");
		
		if(list.get(mid) == tagert)
			return mid;
		
		int rst = bs(tagert,list,start,mid);
		
		if(rst != -1 )
			return rst;
		
		return bs(tagert, list, mid+1, end);
		
	}
}
