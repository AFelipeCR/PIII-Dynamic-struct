package com.afcr.dynamicstructs.doublelist;

import java.util.Iterator;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class DoubleCursor<T> extends DoubleList<T> implements Iterator<T> {

	private DoubleNode<T> cursor;

	public DoubleCursor(DoubleList<T> list) {
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
	public void resetStart() {
		this.cursor = this.head;
	}

	/**
	 * Reinicia el recorrido de la lista
	 */
	public void resetFinish() {
		this.cursor = this.head;
		DoubleNode<T> aux = null;
		while (this.cursor != null) {
			aux = this.cursor;
			this.cursor = this.cursor.next;
		}
		if (aux != null)
			this.cursor = aux;
	}

	/**
	 * Obtiene el dato actual
	 * 
	 * @return dato
	 */
	public T getData() {
		return this.cursor.data;
	}

	public T previous() {
		T aux = this.cursor.data;
		this.cursor = this.cursor.previous;
		return aux;
	}

	public boolean hasPrevious() {
		return this.cursor != null;
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
