package Ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	ArrayList<Monom> Polynom_arr = new ArrayList<Monom>();

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {

		Polynom_arr = new ArrayList<Monom>();

	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x","-5.7x+4x^6" };
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		
		s=removeSpace(s);
		String Monom="";
		int i=0;
		if(s.charAt(0)=='-')
		{
			Monom+='-';
			i++;
		}
		while(i<s.length())
		{
			if(i<s.length()&&s.charAt(i)=='+')
			{
				if(s.charAt(i-1)=='-'||s.charAt(i-1)=='+')throw new RuntimeException("Wrong Polynom");// Error for +++... or +-...
				Monom tmp1=new Monom(Monom);
				Polynom_arr.add(tmp1);
				Monom="";
				i++;
			}
			else if (i<s.length()&&s.charAt(i)!='+')
			{
				if(s.charAt(i)=='-')
				{
					Monom tmp2=new Monom(Monom);
					if(s.charAt(i-1)=='^')throw new RuntimeException("Negative power");
					if(s.charAt(i-1)=='-'||s.charAt(i-1)=='+')throw new RuntimeException("Wrong polynom");// Error for ---... or -+...
					else
					{
						Polynom_arr.add(tmp2);
						Monom="-";
						i++;
					}
				}
				if(i<s.length())
				{
					Monom+=s.charAt(i);
					i++;
					if(i==s.length())
					{
						Monom tmp3=new Monom(Monom);
						Polynom_arr.add(tmp3);
					}
				}
			}
		}


		polynomOrder ();

	}
	/**
	 * The function get number that represented by x , place it in this Polynom and return
	 * the result. 
	 * @param x represent the number that the function get.
	 */
	@Override

	public double f(double x)
	{
		double ans=0;
		for(int i=0;i<Polynom_arr.size();i++)
		{
			double p=(double)Polynom_arr.get(i).get_power();
			double a=Math.pow(x, p);	
			ans+=a*Polynom_arr.get(i).get_coefficient();
		}
		return ans;

	}
	/**
	 * The function add p1 to this polynom.
	 * @param p1 represent polynom.
	 */
	@Override
	public void add(Polynom_able p1) 
	{
		Iterator <Monom> iteratep1 = p1.iteretor();
		while( iteratep1.hasNext())
		{
			this.Polynom_arr.add(iteratep1.next());
		}
		polynomOrder();
	} 

	/**
	 * The function add m1 to this polynom . the function keep is sorted from the highest power to the 
	 * lower power with one monom from each power. 
	 * @param m1 represent monom . 
	 */
	@Override
	public void add(Monom m1) {
		if(!m1.equals(Monom.ZERO)) {
			if(Polynom_arr.isEmpty())
			{
				Polynom_arr.add(m1);
			}
			else
			{
				boolean flag=false;// Monom is insert or not
				int i=0;
				while(i<Polynom_arr.size()&&!flag)
				{
					if(Polynom_arr.get(i).get_power()==m1.get_power())
					{
						Polynom_arr.get(i).add(m1);
						flag=true;
					}
					i++;
				}
				if(!flag)Polynom_arr.add(m1);
			}

		}
		polynomOrder();
	}

	/**
	 * The function substract p1 from this polynom.
	 * @param p1 represent Polynom_able.
	 */
	@Override
	public void substract(Polynom_able p2) {
		Polynom_able p1 = new Polynom(p2.toString());
		Iterator <Monom> iterp1 = p1.iteretor();
		while(iterp1.hasNext())
		{
			Monom m = iterp1.next();
			double co=m.get_coefficient()*(-1);
			Monom tmp=new Monom(co,m.get_power());
			this.Polynom_arr.add(tmp);
		}
		polynomOrder();
	}


	/**
	 * The function multiply p1 with this polynom.
	 * @param p1 represent polynom_able.
	 */
	@Override
	public void multiply(Polynom_able p1)
	{
		ArrayList <Monom> arr= new ArrayList <Monom>();
		Polynom p = new Polynom (p1.toString());

		for(int i=0 ; i<p.Polynom_arr.size() ; i++)
		{
			for( int j=0 ; j<this.Polynom_arr.size() ; j++)
			{
				Monom temp = new Monom( p.Polynom_arr.get(i));
				temp.multipy(this.Polynom_arr.get(j));
				arr.add(temp);
			}
		}
		this.Polynom_arr=arr;
		polynomOrder();
	}


	/**
	 * The Function check if this polynom is logically equals to p1.
	 * @param p1 represent polynom_able.
	 */
	@Override
	public boolean equals(Object p1)
	{
		if(p1 instanceof Polynom)
		{
		Iterator <Monom> iterthis = this.iteretor();
		Iterator <Monom> iterp1 = ((Polynom) p1).iteretor();
		while(iterthis.hasNext()&&iterp1.hasNext())
		{
			if(!iterthis.next().equals(iterp1.next()))return false;
		}
		if(iterthis.hasNext()||iterp1.hasNext())return false;
		return true;
		}
		return false;
	}

	/**
	 * The function check if this is the zero polynom.
	 * @return true if the polynom is zero.
	 */
	@Override
	public boolean isZero() 
	{
		if(this.Polynom_arr.isEmpty())return true;

		else
		{
			for(int i=0;i<Polynom_arr.size();i++)
			{
				if (Polynom_arr.get(i).get_coefficient()!=0)return false;
			}
			return true;
		}
	}

	/**
	 * The function calculates a Polynomial root by using the intermediate value , when the function is 
	 * continuous  and f(x0)* f(x1) <0 else throw exception.
	 * The root that the function returns is x (about eps) when f(x)=0.    
	 * @param x0 represent start or end point.
	 * @param x1 represent start or end point.
	 * @param eps represent positive value (approximation).
	 */

	@Override
	public double root(double x0, double x1, double eps)
	{
		double res = 0;
		double mid=0;
		if(this.f(x0)*this.f(x1)<0)
		{
			if(this.f(x0)>0 && this.f(x1)<0)
			{
				while(Math.abs(f(mid))>eps)
				{
					mid=(x0+x1)/2;
					if(f(mid)>0)x0=mid;
					if(f(mid)<0)x1=mid;
					res=mid;
				}	
			}
			if(this.f(x0)<0 && this.f(x1)>0)
			{
				while(Math.abs(f(mid))>eps)
				{
					mid=(x0+x1)/2;
					if(f(mid)>0)x1=mid;
					if(f(mid)<0)x0=mid;
					res=mid;
				}
			}
			return res;
		}
		else 
		{
			throw new RuntimeException("No root");
		}
	}
	/**
	 * The function create a deep copy of this Polynom.
	 * @return Polynom that is deep copy of this Polynom.
	 */
	@Override
	public Polynom_able copy() 
	{
		Polynom p1= new Polynom ();
		Iterator <Monom> iteratorOnThis= this.iteretor();
		while ( iteratorOnThis.hasNext() )
		{
			p1.Polynom_arr.add(new Monom(iteratorOnThis.next()));
		}
		return p1;
	}

	/**
	 * The function compute a new Polynom which is the derivative of this Polynom.
	 * @return the new Polynom that is the derivative of this Polynom.
	 */
	@Override
	public Polynom_able derivative() {

		Polynom p2= new Polynom ();

		for( int i=0 ; i<Polynom_arr.size() ; i++)
		{
			p2.Polynom_arr.add(this.Polynom_arr.get(i).derivative());
		}
		return p2;
	}

	/**
	 * The function calculates the area of the Polynomial by using Riemann sum .
	 * if x0 > x1 the area is 0.
	 * @param x0 represent start point.
	 * @param x1 represent end point.
	 * @param eps represent positive value (approximation).
	 * 
	 */
	@Override
	public double area(double x0, double x1, double eps) 
	{
		double sum=0;
		if(x0>=x1)return 0; // the area is right from x0 and left from x1
		else
		{
			while((x0<=x1))
			{
				if(f(x0)>0)
					sum+=eps*f(x0);
				x0+=eps;
			}
		}
		return sum;
	}

	/**
	 * Iterator function for Polynom.
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return Polynom_arr.iterator();
	}


	/**
	 * The function multiply m1 with this Polynom.
	 * @param m1 represent monom.
	 */	
	@Override
	public void multiply(Monom m1) {
		for (int i = 0; i < Polynom_arr.size(); i++) 
		{
			Polynom_arr.get(i).multipy(m1);
		}
	}
	/**
	 * The function order the Polynom from the highest power to the lower power and add monoms
	 *  with same power.
	 */
	public void polynomOrder (){
		int n= this.Polynom_arr.size();
		for (int i = 0; i < n-1; i++) 
		{
			for (int j = 0; j < n-i-1; j++)
			{
				// Order by powers
				if(this.Polynom_arr.get(j).get_power()<this.Polynom_arr.get(j+1).get_power())
				{
					Collections.swap(this.Polynom_arr, j, j+1);
				}
			}
		}	
		// sum same powers
		for(int p=0 ; p<this.Polynom_arr.size() ; p++)
		{
			for( int j=0; j<this.Polynom_arr.size() ; j++)
			{
				if(p != j)
				{
					try {
						this.Polynom_arr.get(p).add(this.Polynom_arr.get(j));
						this.Polynom_arr.remove(j);

					} catch (Exception e) {

					}
				}
			}

		}
	}
	/**
	 * The function return String that represent this Polynom .
	 */
	public String toString()
	{
		String ans="";
		if ( Polynom_arr.size() == 0) {
			return "0";
		}
		else {
			for(int i=0;i<Polynom_arr.size();i++)
			{
				Monom s=Polynom_arr.get(i);
				if(s.get_coefficient()>0&&i!=0)ans+="+";
				if(s.get_coefficient()!=0)ans+=s.toString();
			}
			return ans;
		}
	}
	@Override
	public function initFromString(String s) {
		function p=new Polynom (s);
		return p;
	}
	
	

	 private String removeSpace(String str) 
   { 
       str = str.replaceAll("\\s",""); 
       return str; 
   } 
	
	
}


