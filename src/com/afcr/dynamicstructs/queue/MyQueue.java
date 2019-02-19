package com.afcr.dynamicstructs.queue;

import java.util.Comparator;

import com.afcr.dynamicstructs.stack.Node;


public class MyQueue<T> {
	private Node<T> first;
	private Node<T> last;

	public MyQueue() {
		this.first = null;
		this.last = null;
	}

	public void put(T data) {
		if (this.first == null) {
			this.last = this.first = new Node<T>(data);
		} else {
			this.last.setNext(new Node<T>(data));
			this.last = this.last.getNext();
		}
	}

	public T get() {
		T data = this.first.getData();
		this.first = this.first.getNext();
		if (this.first == null)
			this.last = null;
		return data;
	}

	public void sort(Comparator<T> comparator) {
		MyQueue<T> queueA = new MyQueue<>();
		MyQueue<T> queueB = new MyQueue<>();
		divideQueue(queueA, queueB, comparator);
		mergeQueue(queueA, queueB, comparator);

	}

	private void divideQueue(MyQueue<T> queueA, MyQueue<T> queueB, Comparator<T> comparator) {
		MyQueue<T> auxQueue = queueA;
		T aux = this.get();
		T aux2 = get();
		System.out.println("******Separate*******");
		while (!isEmpty()) {
			if (queueA.isEmpty()) {
				queueA.put(aux);
			}
			if (comparator.compare(aux, aux2) >= 0) {
				System.out.print(aux + ">" + aux2);
				if (auxQueue == queueA)
					auxQueue = queueB;
				else
					auxQueue = queueA;
				auxQueue.put(aux2);
			} else {
				System.out.print(aux + "<" + aux2);
				auxQueue.put(aux2);
			}
			System.out.println((auxQueue == queueA ? "A" : "B"));
			aux = aux2;
			aux2 = get();
		}

		if (comparator.compare(aux, aux2) >= 0) {
			System.out.print(aux + ">" + aux2);
			if (auxQueue == queueA)
				auxQueue = queueB;
			else
				auxQueue = queueA;
			auxQueue.put(aux2);
		} else {
			System.out.print(aux + "<" + aux2);
			auxQueue.put(aux2);
		}
		System.out.println((auxQueue == queueA ? "A" : "B"));
	}

	private void mergeQueue(MyQueue<T> queueA, MyQueue<T> queueB, Comparator<T> comparator) {
		System.out.println("******Merge*******");
		T aux = queueA.get();
		T aux2 = queueB.get();
		while (!queueA.isEmpty() && !queueB.isEmpty()) {
			if (comparator.compare(aux, aux2) >= 0) {
				System.out.println(aux + ">" + aux2);
				System.out.println(aux2);
				put(aux2);
				aux2 = queueB.get();
			} else {
				System.out.println(aux + "<" + aux2);
				System.out.println(aux);
				put(aux);
				aux = queueA.get();
			}
		}
		if (comparator.compare(aux, aux2) >= 0) {
			System.out.println(aux + ">" + aux2);
			System.out.println(aux2);
			put(aux2);
		} else {
			System.out.println(aux + "<" + aux2);
			System.out.println(aux);
			put(aux);
		}
		if (queueA.isEmpty() && !queueB.isEmpty()) {
			System.out.println(aux2);
			put(aux2);
			while (!queueB.isEmpty()) {
				T a = queueB.get();
				put(a);
				System.out.println(a);
			}
		}
		if (queueB.isEmpty() && !queueA.isEmpty()) {
			System.out.println(aux);
			put(aux);
			while (!queueA.isEmpty()) {
				T a = queueA.get();
				put(a);
				System.out.println(a);
			}
		}
	}

	// Ordenar pilas con colas

	public boolean isEmpty() {
		return this.first == null;
	}

}
