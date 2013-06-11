package util;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author http://www.linewell.com <a href=mailto:cg@linewell.com>cg@linewell.com</a> 
 * @version 1.0 
 */
public class Sort {

	private static void swap(List<Integer> a, int i, int j) {
		int tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}

	private static int partition(List<Integer> a, int low, int high) {
		int pivot, p_pos, i;
		p_pos = low;
		pivot = a.get(p_pos);
		for (i = low + 1; i <= high; i++) {
			if (a.get(i) < pivot) {
				p_pos++;
				swap(a, p_pos, i);
			}
		}
		swap(a, low, p_pos);
		return p_pos;
	}

	private static void quicksort(List<Integer> a, int low, int high) {
		int pivot;
		if (low < high) {
			pivot = partition(a, low, high);
			quicksort(a, low, pivot - 1);
			quicksort(a, pivot + 1, high);
		}
	}
	
	public static void quicksort(List<Integer> list){
		quicksort(list, 0, list.size());
	}
	
	
	public static void insertsort(List<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			int j = i;
			int temp = list.get(j);
			while (j > 0 && list.get(j - 1) > temp ) {
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, temp);
		}
	}

	public static void main(String args[]) {
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(37);
		list.add(47);
		list.add(23);
		list.add(-5);
		list.add(19);
		list.add(56);
		
//		Sort.quicksort(list, 0, list.size()-1);
		insertsort(list);
		
		for(int i: list)
			System.out.println(i);
		
		System.out.println(Search.binarysearch(19,list));
		
	}

}
