package queue;

import java.util.ArrayList;

public class Queue {

	private ArrayList<Container> data = new ArrayList<>();

	public Queue() { data.add(new EmptyContainer()); }

  	public boolean isEmpty() { return (data.size() == 1); }

	public Queue add( Object  cargo ) {
		data.add(1, new FilledContainer(cargo));
		return this;
	}

	public Object take() { return data.remove(getLastIndex()).getCargo(); }

	public Object head() { return data.get(getLastIndex()).getCargo(); }

	public int size() { return data.size() - 1; }

	private int getLastIndex() { return size(); }
}
