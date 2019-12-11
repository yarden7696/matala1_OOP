package Ex1;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import com.google.gson.Gson;

public class Functions_GUI implements functions{

	static ArrayList<function> functions= new ArrayList<function>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 

	public static void main(String[] a) {

		function cf1=(new Polynom ("2x^3 + 2x^5"));
		functions.add(cf1);
		functions.add(new ComplexFunction("plus",cf1,cf1));


		Functions_GUI f= new Functions_GUI(); 
		f.add(new Polynom("0.0"));
		try {
			f.initFromFile("function_file.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			f.saveToFile("check.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//f.drawFunctions(w,h,rx,ry,res);
		f.drawFunctions("function_file.txt");
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
		function fun= new ComplexFunction(new Polynom("0"));
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
		File f= new File (file);

		try {
			PrintWriter pw= new PrintWriter (f);
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<this.functions.size(); i++)
			{
				function fun= this.functions.get(i);
				sb.append(fun.toString() + "\n" );	
			}
			pw.write(sb.toString());
			pw.close();
		}	
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;

		}
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
		StdDraw.line(rx.get_min(),0, rx.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int i = 0; i <= res; i=i+10)
		{
			StdDraw.text(x[i]-0.07,  -0.4, Integer.toString(i-res/2));
		}

		////////y axis
		StdDraw.line(x[res/2], ry.get_min(), x[res/2], ry.get_max());
		for (double i = ry.get_min(); i <= ry.get_max(); i=i+1) {
			StdDraw.text(x[res/2]-0.07, i+0.07, Double.toString(i));
		}



		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);

			System.out.println(a+") "+Colors[c]+"  f(x)= "+functions.get(a));
			for (int i = 0; i < res; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	

	}

	@Override
	public void drawFunctions(String json_file) {

		Gson gson = new Gson();

		try 
		{

			FileReader reader = new FileReader(json_file);
			GUI_Param param = gson.fromJson(reader,GUI_Param.class);

			Range rx = new Range(param.Range_X[0],param.Range_X[1]);
			Range ry = new Range(param.Range_Y[0],param.Range_Y[1]);
			drawFunctions(param.Width,param.Height,rx,ry,param.Resolution);
			return;

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public function get(int i) {
		return functions.get(i);
	}
}