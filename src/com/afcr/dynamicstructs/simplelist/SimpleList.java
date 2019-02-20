package com.afcr.dynamicstructs.simplelist;

import java.util.Comparator;
import java.util.Iterator;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1.0 24/08/2018
 *
 */
public class SimpleList<T> implements Iterable<T> {
	protected Node<T> head;
	private int size;
	private Comparator<T> comparator;

	/**
	 * Constructor vacio, nohabra orden alguno
	 */
	public SimpleList() {
		this.comparator = null;
		this.head = null;
		this.size = 0;
	}

	/**
	 * La lista se organizara dependeindo del {@link Comparator} entrante
	 * 
	 * @param comparator evaluacion
	 */
	public SimpleList(Comparator<T> comparator) {
		this.comparator = comparator;
		this.head = null;
		this.size = 0;
	}

	/**
	 * Recursividad para herencia
	 * 
	 * @param list una lista ya creada
	 */
	public SimpleList(SimpleList<T> list) {
		this.head = list.head;
		this.size = 0;
	}

	/**
	 * Añade el elemento entrante al final de la lista
	 * 
	 * @param data elemento entrante
	 */
	private void addLast(T data) {
		if (this.head == null) {
			this.head = new Node<T>(data);
		} else {
			Node<T> aux = head;
			while (aux.next != null) {
				aux = aux.next;
			}
			aux.next = new Node<T>(data);
		}
	}

	public void add(T... datas) {
		for (T data : datas) {
			this.add(data);
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
		this.size++;
	}

	/**
	 * Añade el elemento entrante dependiendo del criterio del {@link Comparator}
	 * 
	 * @param data elemento entrante
	 */
	private void addSort(T data) {
		if (this.head == null)
			this.head = new Node<T>(data);
		else {
			if (this.comparator.compare(data, this.head.data) < 0)
				this.insert(data);
			else {
				Node<T> previousAux = null;
				Node<T> aux = this.head;
				while (aux != null && comparator.compare(data, aux.data) >= 0) {
					previousAux = aux;
					aux = aux.next;
				}
				previousAux.next = new Node<T>(data, aux);
			}
		}
	}

	/**
	 * Obtiene el dato alojado en cierta posicion
	 * 
	 * @param index ubicacion en la lista
	 * @return dato
	 */
	public T get(int index) {
		Node<T> aux = head;
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
			Node<T> previousAux = null;
			Node<T> aux = this.head;
			for (int i = 0; i < index; i++) {
				previousAux = aux;
				aux = aux.next;
			}
			previousAux.next = new Node<T>(data, aux.next);
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
			} else {
				Node<T> previousAux = this.head;
				Node<T> aux = this.head.next;
				while (aux != null) {
					if (aux.data.equals(data)) {
						previousAux.next = aux.next;
					}
					previousAux = aux;
					aux = aux.next;
				}
			}
		this.size--;
	}

	/**
	 * borra un dato alojado en una ubicacion
	 * 
	 * @param index ubicacion
	 */
	public void remove(int index) {
		remove(get(index));
	}

	/**
	 * inserta un dato por delante de la lista
	 * 
	 * @param data dato entrante
	 */
	public void insert(T data) {
		this.head = new Node<T>(data, this.head);
	}

	/**
	 * Muestra si un objeto esta en la lista
	 * 
	 * @param data dato a analizar
	 * @return resultado de condicion
	 */
	public boolean isInList(T data) {
		Node<T> aux = this.head;
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
		Node<T> aux = this.head;
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
	public SimpleList<T> getObjectBy(T base, Comparator<T> comparator) {
		SimpleList<T> auxList = new SimpleList<>();
		Node<T> aux = this.head;
		while (aux != null) {
			if (comparator.compare(base, aux.data) == 0) {
				auxList.add(aux.data);
			}
			aux = aux.next;
		}
		return auxList;
	}

	/**
	 * Pone la lista de reves y genera una nueva lista con los datos al reves
	 * 
	 * @return lista al reves
	 */
	public SimpleList<T> reverseList() {
		SimpleList<T> auxList = new SimpleList<>();
		Node<T> reverseAux = null;
		while (reverseAux != this.head) {
			Node<T> aux = this.head;
			while (aux.next != reverseAux) {
				aux = aux.next;
			}
			auxList.add(aux.data);
			reverseAux = aux;
		}
		return auxList;
	}

	public Node<T> getHead() {
		return this.head;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void clean() {
		this.head = null;
	}

	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Cursor<>(this);
	}
}
