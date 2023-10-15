//package levelsQueue;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//protected class LevelsQueue {
//	private ArrayList<Level> data = new ArrayList<>( Arrays.asList( new DeepWatersLevel() ) );
//
//  	public boolean isEmpty() { return ( data.size() == 1 ); }
//
//	public LevelsQueue add(Object  cargo ) {
//		data.add( 1, new SurfaceLevel( cargo ) );
//		return this;
//	}
//
//	public Object take() { return data.remove( this.size() ).getCargo(); }
//
//	public Object head() { return data.get( this.size() ).getCargo(); }
//
//	public int size() { return data.size() - 1; }
//}