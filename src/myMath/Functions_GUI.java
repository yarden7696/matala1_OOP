package myMath;
import java.util.ArrayList;
import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

     public class Functions_GUI implements functions{

     ArrayList<function> functions= new ArrayList<function>();
     public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 
	
	
	@Override
	public boolean add(function arg0) {
	return functions.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return functions.addAll(arg0);
	}

	@Override
	public void clear() {
	functions.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return functions.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return functions.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	/**
	 * this method remove an object from the array list
	 */
	@Override
	public boolean remove(Object o) {
		return functions.remove(o);
	}

	
	/**
	 * this method remove a collection of object 
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return functions.removeAll(c);
	}

	/**
	 * this method is used to remove all the array list’s elements that are not contained in the specified collection 
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return functions.retainAll(c);
	}

	/**
	 * this method returns the number of elements in the array list
	 */
	@Override
	public int size() {
		return functions.size();
	}

	
	/**
	 * this method is used to return an array containing all the elements in ArrayList in the correct order.
	 */
	@Override
	public Object[] toArray() {
		Object [] ans= functions.toArray();
		return ans;
	}

	
	/**
	 * 
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		
		int n= resolution;
		StdDraw.setCanvasSize(width, height);
		
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
