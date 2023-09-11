package queue;
import queue.container.Container;
import queue.container.FilledContainer;
import queue.container.EmptyContainer;

import java.util.ArrayList;


public class Queue {

	private ArrayList<Container> data = new ArrayList<>();

	public Queue() { data.add(new EmptyContainer()); }

  	public boolean isEmpty() { return (data.size() == 1); }

	public Queue add( Object  cargo ) {
		data.add(1, new FilledContainer(cargo));
		return this;
	}

	public Object take() {
		Object cargo = this.head();
		data.remove(getLastIndex());
		return cargo;
	}

	public Object head() { return data.get(getLastIndex()).getCargo(); }

	private int getLastIndex() {
		return data.size() - 1;
	}

	public int size() { return (data.size() - 1); }
}
