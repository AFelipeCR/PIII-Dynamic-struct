package com.afcr.dynamicstructs.doublelist;

import java.util.Comparator;
import java.util.Iterator;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class DoubleList<T> implements Iterable<T> {
	protected DoubleNode<T> head;
	private Comparator<T> comparator;

	/**
	 * Constructor vacio, nohabra orden alguno
	 */
	public DoubleList() {
		this.comparator = null;
		this.head = null;
	}

	/**
	 * La lista se organizara dependeindo del {@link Comparator} entrante
	 * 
	 * @param comparator evaluacion
	 */
	public DoubleList(Comparator<T> comparator) {
		this.comparator = comparator;
		this.head = null;
	}

	/**
	 * Recursividad para herencia
	 * 
	 * @param list una lista ya creada
	 */
	public DoubleList(DoubleList<T> list) {
		this.head = list.head;
	}

	/**
	 * Añade el elemento entrante al final de la lista
	 * 
	 * @param data elemento entrante
	 */
	private void addLast(T data) {
		if (this.head == null) {
			this.head = new DoubleNode<T>(data);
		} else {
			DoubleNode<T> aux = head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = new DoubleNode<T>(data);
			aux.next.previous = aux;
		}
	}

	/**
	 * Añade el elemento entrante a la lista
	 * 
	 * @param data elemento entrante
	 */
	public void add(T data) {
		if (this.comparator == null)
			this.addLast(data);
		else
			this.addSort(data);
	}

	/**
	 * Añade el elemento entrante dependiendo del criterio del {@link Comparator}
	 * 
	 * @param data elemento entrante
	 */
	private void addSort(T data) {
		if (this.head == null)
			this.head = new DoubleNode<T>(data);
		else {
			if (this.comparator.compare(data, this.head.data) < 0)
				this.insert(data);
			else {
				DoubleNode<T> previousAux = null;
				DoubleNode<T> aux = this.head;
				while (aux != null && comparator.compare(data, aux.data) >= 0) {
					previousAux = aux;
					aux = aux.next;
				}
				previousAux.next = new DoubleNode<T>(data, previousAux, aux);
				if (aux != null)
					aux.previous = previousAux.next;
			}
		}
	}

	/**
	 * Obtiene el dato alojado en cierta posicion
	 * 
	 * @param index ubicacion en la lista
	 * @return dato
	 */
	protected T get(int index) {
		DoubleNode<T> aux = head;
		for (int i = 0; i < index; i++) {
			aux = aux.next;
		}
		return aux.data;
	}

	/**
	 * Establece un elemento en la posicion dada
	 * 
	 * @param index ubicacion
	 * @param data  dato a establecer
	 */
	public void set(int index, T data) {
		if (this.head != null) {
			DoubleNode<T> previousAux = null;
			DoubleNode<T> aux = this.head;
			for (int i = 0; i < index; i++) {
				previousAux = aux;
				aux = aux.next;
			}
			previousAux.next = new DoubleNode<T>(data, previousAux, aux.next);
		}
	}

	/**
	 * borra un dato identico al entrante
	 * 
	 * @param data dato entrante
	 */
	public void remove(T data) {
		if (this.head != null)
			if (this.head.data.equals(data)) {
				this.head = this.head.next;
				this.head.previous = null;
			} else {
				DoubleNode<T> previousAux = this.head;
				DoubleNode<T> aux = this.head.next;
				while (aux != null) {
					if (aux.data.equals(data)) {
						previousAux.next = aux.next;
						previousAux.next.previous = previousAux;
					}
					previousAux = aux;
					aux = aux.next;
				}
			}
	}

	/**
	 * inserta un dato por delante de la lista
	 * 
	 * @param data dato entrante
	 */
	public void insert(T data) {
		this.head = new DoubleNode<T>(data, this.head);
	}

	/**
	 * Muestra si un objeto esta en la lista
	 * 
	 * @param data dato a analizar
	 * @return resultado de condicion
	 */
	public boolean isInList(T data) {
		DoubleNode<T> aux = this.head;
		while (aux != null) {
			if (aux.data.equals(data)) {
				return true;
			}
			aux = aux.next;
		}
		return false;
	}

	/**
	 * Verifica si es el ultimo elemento de la lista
	 * 
	 * @param data elemento a comparar
	 * @return condicion
	 */
	public boolean isLast(T data) {
		DoubleNode<T> aux = this.head;
		while (aux.next != null) {
			aux = aux.next;
		}
		return data.equals(aux.data);
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	/**
	 * Busca los elementos que cumplan con el criterio entrate
	 * 
	 * @param base       dato desde donde toma referencia
	 * @param comparator compara si cumple con el criterio
	 * @return una lista con los datos que coinciden
	 */
	public DoubleList<T> getObjectBy(T base, Comparator<T> comparator) {
		DoubleList<T> auxList = new DoubleList<>();
		DoubleNode<T> aux = this.head;
		while (aux != null) {
			if (comparator.compare(base, aux.data) == 0) {
				auxList.add(aux.data);
			}
			aux = aux.next;
		}
		return auxList;
	}

	@Override
	public Iterator<T> iterator() {
		return new DoubleCursor<>(this);
	}
}
