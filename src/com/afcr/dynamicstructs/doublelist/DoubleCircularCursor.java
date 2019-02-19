package com.afcr.dynamicstructs.doublelist;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class DoubleCircularCursor<T> extends DoubleCircularList<T> {

	private DoubleNode<T> cursor;

	public DoubleCircularCursor(DoubleCircularList<T> list) {
		super(list);
		this.cursor = super.cursor;
	}

	/**
	 * Obtiene el dato actual
	 * 
	 * @return dato
	 */
	public T getData() {
		return this.cursor.data;
	}

	public void next() {
		this.cursor = this.cursor.next;
	}
	
	public void previous() {
		this.cursor = this.cursor.previous;
	}
	
	public T previousData() {
		T aux = this.cursor.data;
		this.cursor = this.cursor.previous;
		return aux;
	}
	
	public T nextData() {
		T aux = this.cursor.data;
		this.cursor = this.cursor.next;
		return aux;
	}
}
