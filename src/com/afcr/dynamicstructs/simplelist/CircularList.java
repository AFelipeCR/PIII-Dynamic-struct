package com.afcr.dynamicstructs.simplelist;

public class CircularList<T> {
	protected Node<T> cursor;

	/**
	 * Constructor vacio, nohabra orden alguno
	 */
	public CircularList() {
		this.cursor = null;
	}
	
	public CircularList(CircularList<T> circularList) {
		this.cursor = circularList.cursor;
	}

	/**
	 * Añade el elemento entrante al final de la lista
	 * 
	 * @param data elemento entrante
	 */
	public void add(T data) {
		if (this.cursor == null) {
			this.cursor = new Node<T>(data);
			this.cursor.next = this.cursor;
		} else {
			Node<T> previousCursor = this.cursor;
			Node<T> aux = this.cursor;
			this.cursor = new Node<T>(data, aux);
			while (aux.next != previousCursor) {
				aux = aux.next;
			}
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
				this.cursor = this.cursor.next;
			} else {
				Node<T> previousAux = this.cursor;
				Node<T> aux = this.cursor.next;
				while (aux != this.cursor) {
					if (aux.data.equals(data)) {
						previousAux.next = aux.next;
					}
					previousAux = aux;
					aux = aux.next;
				}
			}
	}

	public void set(T data) {
		this.cursor.data = data;
	}
}
