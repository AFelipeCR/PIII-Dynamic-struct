package com.afcr.dynamicstructs.simplelist;


/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class Node<T> {
	protected T data;
	protected Node<T> next;

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
	
	public T getData() {
		return data;
	}
	
	public Node<T> getNext() {
		return next;
	}
}
