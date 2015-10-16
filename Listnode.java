package project2;

public class Listnode<E> {
	private E data;
	private Listnode<E> prev;
	private Listnode<E> next;
	
	 Listnode(E data) {this(data,null,null);}

	 Listnode(E data, Listnode<E> prev, Listnode<E> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	
	 	E getData() {
			return data;
		}

	
		Listnode<E> getNext() {
			return next;
		}
		
		
		Listnode<E> getPrev() {
			return prev;
		}


		void setData(E data) {
			this.data = data;
		}

		
		void setNext(Listnode<E> next) {
			this.next = next;
		}
		
		
		void setPrev(Listnode<E> prev) {
			this.prev = prev;
}
}