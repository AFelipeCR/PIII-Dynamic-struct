package com.afcr.dynamicstructs.doublelist;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @date 23/08/2018
 *
 */
public class DoubleNode<T> {
	protected T data;
	protected DoubleNode<T> previous;
	protected DoubleNode<T> next;
	
	/**
	 * Incializa la clase con el dato actual, el anterior y el siguiente
	 * 
	 * @param data dato
	 * @param previous dato anterior
	 * @param next siguiente dato
	 */
	public DoubleNode(T data,DoubleNode<T> previous, DoubleNode<T> next) {
		this.previous = previous;
		this.data = data;
		this.next = next;
	}
	
	
	/**
	 * Incializa la clase con el dato actual y el siguiente
	 * 
	 * @param data dato
	 * @param next siguiente nodo
	 */
	public DoubleNode(T data, DoubleNode<T> next) {
		this.previous = null;
		this.data = data;
		this.next = next;
	}

	/**
	 * Inicializa con el dato actual
	 * 
	 * @param data dato
	 */
	public DoubleNode(T data) {
		this.data = data;
		this.previous = null;
		this.next = null;
	}
}
