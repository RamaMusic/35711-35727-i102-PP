package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	static Queue defaultQueue = new EmptyQueue();
	ArrayList<Object> data = new ArrayList<>();
	public String emptyError = "Queue is empty";

  	public boolean isEmpty() {
		return data.isEmpty();
	}

	public Queue add( Object  cargo ) {
		defaultQueue = defaultQueue.add(cargo);
		return defaultQueue;
	}

	public Object take() {
		  return defaultQueue.take();
	}

	public Object head() {
		return defaultQueue.head();
  	}

	public int size() {
		return defaultQueue.size();
	}
}

class EmptyQueue extends Queue {
	@Override
	public Queue add (Object cargo) {
		return new FilledQueue().add(cargo);
	}


	public Object take() {
		failEmpty();
		return null;
	}

	@Override
	public Object head() {
		failEmpty();
		return null;
	}

	private void failEmpty() {
		throw new Error(this.emptyError);
	}

	@Override
	public int size() {
		return 0;
	}
}

class FilledQueue extends Queue {

	@Override
	public Queue add (Object cargo) {
		data.add(0, cargo);
		return this;
	}

	public Object take() {
		return data.remove(data.size() - 1);
	}

	@Override
	public Object head() {
		return data.get(data.size() - 1);
	}

	@Override
	public int size() {
		return data.size();
	}
}