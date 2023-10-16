package queue;

import java.util.ArrayList;
import java.util.Arrays;

public class Queue {
	private ArrayList<Container> data = new ArrayList<>( Arrays.asList( new EmptyContainer() ) );

  	public boolean isEmpty() { return ( data.size() == 1 ); }

	public Queue add( Object  cargo ) {
		data.add( 1, new FilledContainer( cargo ) );
		return this;
	}

	public Object take() { return data.remove( this.size() ).getCargo(); }

	public Object head() { return data.get( this.size() ).getCargo(); }

	public int size() { return data.size() - 1; }
}