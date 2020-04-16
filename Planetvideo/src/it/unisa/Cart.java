package it.unisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<T> list;
	
	public Cart() {
		list = new ArrayList<T>();
	}
	
	public void addElement(T element) {
		list.add(element);
	}
	
	public void deleteElement(T element) {
		for(T elem : list) {
			if(elem.equals(element)) {
				list.remove(elem);
				break;
			}	
		}
	}
	
	public List<T> getList() {
		return list;
	}
}
