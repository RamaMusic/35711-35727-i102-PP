package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	private List<String> data = new ArrayList<>();
	public String emptyError = "Queue is empty empty";

  public boolean isEmpty() {
		return data.isEmpty();
	}

	public Queue add( Object  cargo ) {
		data.add(0, cargo.toString());
		return this;
	}

	public Object take() {
		if (!this.isEmpty()) {
			return data.remove(data.size() - 1);
		}

		throw new Error(this.emptyError);
	}

	public Object head() {
	  	if (!this.isEmpty()) {
			return data.get(data.size() - 1);
		}

		throw new Error(this.emptyError);
	}

	public int size() {
		// TODO Auto-generated method stub
		return data.size();
	}
}
