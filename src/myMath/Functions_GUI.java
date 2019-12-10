package myMath;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

     public class Functions_GUI implements functions{

     static ArrayList<function> functions= new ArrayList<function>();
     public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 
	
     public static void main(String[] a) {
    
 		Polynom s0 = new Polynom("x^2");
 		functions.add(s0);
 		
    	 Functions_GUI f= new Functions_GUI(); 
    	 int w=1000, h=600, res=200;
 		Range rx = new Range(-10,10);
 		Range ry = new Range(-5,10);
 		f.drawFunctions(w,h,rx,ry,res);
 	}
	
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
		T[] fun = functions.toArray(a);
		return fun;
	}

	@Override
	public void initFromFile(String file) throws IOException {
		BufferedReader br= null;
		function fun= new ComplexFunction(new Polynom("1"));
		try {
			br= new BufferedReader(new FileReader(file));
			String line;
			while((line= br.readLine()) != null)
			{
				functions.add((function)fun.initFromString(line));
			}
		    }
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		
		int res= resolution;
		StdDraw.setCanvasSize(width, height);
        int size = functions.size();
        double[] x = new double[res+1];
		double[][] yy = new double[size][res+1];
		double x_step = (rx.get_max()-rx.get_min())/res;
		double x0 = rx.get_min(); 
		for (int i=0; i<=res; i++) {
			x[i] = x0;
			for(int j=0 ; j<size ; j++) {
				yy[j][i] = functions.get(j).f(x[i]);
			}
			x0+=x_step;
		}
		
		StdDraw.setXscale(rx.get_min(), rx.get_max()); 
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		
		
	
		
		
          ////////vertical lines///////
		 StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = 0; i <= res; i=i + 5) {
		StdDraw.line(x[i], ry.get_min(), x[i], ry.get_max());
		}
		
             ////////horizontal lines////////
        for (double i = ry.get_min() ; i <= ry.get_max()-ry.get_min() ; i=i+1) {
       StdDraw.line(rx.get_min(), (0 +i) , rx.get_max() ,(0 +i));
       }             
		
		            ////////x axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), yy[0][res/2], rx.get_max(), yy[0][res/2]);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int i = 0; i <= res; i=i+10)
		{
			StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-res/2));
		}
		////////y axis
		StdDraw.line(x[res/2], ry.get_min(), x[res/2], ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+1) {
		StdDraw.text(x[res/2]-0.07, i+0.07, Double.toString(i));
		}
        
		
	for(int a=0;a<size;a++) {
		int c = a%Colors.length;
		StdDraw.setPenColor(Colors[c]);
	
		System.out.println(a+") "+Colors[a]+"  f(x)= "+functions.get(a));
		for (int i = 0; i < res; i++) {
			StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
		}
	}	
	
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

	public function get(int i) {
		return functions.get(i);
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/yarden7696/matala1_OOP.git
