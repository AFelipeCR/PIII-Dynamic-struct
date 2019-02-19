package com.afcr.dynamicstructs.stack;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class Node<T> {
	private T data;
	private Node<T> next;

	/**
	 * Incializa la clase con el dato actual y el siguiente
	 * 
	 * @param data dato que se alojara en el nudo
	 * @param next nudo siguiente
	 */
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Inicializa con el dato actual
	 * 
	 * @param data dato
	 */
	public Node(T data) {
		this.data = data;
		this.next = null;
	}

	/**
	 * retorna el dato actual
	 * 
	 * @return dato
	 */
	public T getData() {
		return data;
	}

	/**
	 * Retorna el dato siguiente
	 * 
	 * @return dato
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Establece el dato siguiente
	 * 
	 * @param next dato siguiente
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
