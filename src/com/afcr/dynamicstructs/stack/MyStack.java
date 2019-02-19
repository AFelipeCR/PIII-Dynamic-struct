/**
 * 
 */
package com.afcr.dynamicstructs.stack;

/**
 * The Class MyStack.
 *
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 03/09/2018
 * @param <T> the generic type
 */
public class MyStack<T> {

	private Node<T> top;

	/**
	 * Instantiates a new my stack.
	 */
	public MyStack() {
		this.top = null;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return this.top == null;
	}

	/**
	 * Coloca un nuevo dato en la pila.
	 *
	 * @param data the data
	 */
	public void push(T data) {
		this.top = new Node<T>(data, this.top);
	}

	/**
	 * Recoje un dato de la pila.
	 *
	 * @return the t
	 */
	public T pop() {
		T aux = this.top.getData();
		this.top = this.top.getNext();
		return aux;
	}

}
