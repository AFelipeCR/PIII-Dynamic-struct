package com.afcr.dynamicstructs.doublelist;

;

public class DoubleCircularList<T> {
	protected DoubleNode<T> cursor;

	/**
	 * Constructor vacio, nohabra orden alguno
	 */
	public DoubleCircularList() {
		this.cursor = null;
	}
	
	public DoubleCircularList(DoubleCircularList<T> circularList) {
		this.cursor = circularList.cursor;
	}

	/**
	 * Añade el elemento entrante al final de la lista
	 * 
	 * @param data elemento entrante
	 */
	public void add(T data) {
		if (this.cursor == null) {
			this.cursor = new DoubleNode<T>(data);
			this.cursor.next = this.cursor;
			this.cursor.previous = this.cursor;
		} else {
			DoubleNode<T> previousCursor = this.cursor;
			DoubleNode<T> aux = this.cursor;
			while (aux.next != previousCursor) {
				aux = aux.next;
			}
			this.cursor = new DoubleNode<T>(data, aux, previousCursor);
			previousCursor.previous = this.cursor;
			aux.next = this.cursor;
		}
	}

	/**
	 * borra un dato identico al entrante
	 * 
	 * @param data dato entrante
	 */
	public void remove(T data) {
		if (this.cursor != null)
			if (this.cursor.data.equals(data)) {
				DoubleNode<T> aux = this.cursor.previous;
				this.cursor = this.cursor.next;
				this.cursor.previous = aux;
				aux.next = this.cursor;
			} else {
				DoubleNode<T> previousAux = this.cursor;
				DoubleNode<T> aux = this.cursor.next;
				while (aux != this.cursor) {
					if (aux.data.equals(data)) {
						previousAux.next = aux.next;
						aux.next.previous = previousAux;
					}
					previousAux = aux;
					aux = aux.next;
				}
			}
	}

	public void set(T data) {
		this.cursor.data = data;
	}

	public DoubleNode<T> getCursor() {
		return cursor;
	}
}
