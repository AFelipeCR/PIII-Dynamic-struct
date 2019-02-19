package com.afcr.dynamicstructs.simplelist;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class CircularCursor<T> extends CircularList<T> {

	private Node<T> cursor;

	public CircularCursor(CircularList<T> list) {
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
	
	public T nextData() {
		T aux = this.cursor.data;
		this.cursor = this.cursor.next;
		return aux;
	}
}
