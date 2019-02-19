package com.afcr.dynamicstructs.simplelist;
import java.util.Iterator;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class Cursor<T> extends SimpleList<T> implements Iterator<T>{

	private Node<T> cursor;

	public Cursor(SimpleList<T> list) {
		super(list);
		this.cursor = this.head;
	}

	/**
	 * Evalua si la lista llega a su fin
	 * 
	 * @return evaluacion
	 */
	public boolean isOut() {
		return this.cursor == null;
	}

	/**
	 * Evalua si el siguiente dato es nulo
	 * 
	 * @return verificacion
	 */
	public boolean isLast() {
		return this.cursor.next == null;
	}

	/**
	 * Reinicia el recorrido de la lista
	 */
	public void reset() {
		this.cursor = this.head;
	}

	/**
	 * Obtiene el dato actual
	 * 
	 * @return dato
	 */
	public T getData() {
		return this.cursor.data;
	}

	@Override
	public boolean hasNext() {
		return this.cursor != null;
	}

	@Override
	public T next() {
		T aux = this.cursor.data;
		this.cursor = this.cursor.next;
		return aux;
	}
}
