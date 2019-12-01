
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************
	
	/**
	 * The function get String that represent monom and build monom 
	 * @param s is a string that represent monom
	 * @throws ArithmeticException when the power is negative or the power is fraction or invalid letters 
	 * throughout the string . also, monom with the form a^b (a,b Integers) is invalid.
	 */
	
	public Monom(String s) throws ArithmeticException {
		int flag = 0;
		boolean isNegative = false, foundNumber = false;
		double x = 0, y = 0;
		int z = 0;
		int div = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x') {
				if (i == s.length() - 1) {
					z++;
				}
				if (foundNumber == false) {
					x = 1;
				}
			} else if (s.charAt(i) == '-' && i == 0) {
				isNegative = true;
			} else if (s.charAt(i) == '.')
				flag = 1;
			else if (s.charAt(i) == '^')
				flag = 2;
			else if (s.charAt(i) >= '0' && s.charAt(i) <= '9')

			{
				if (i < s.length() - 1) {
					if (s.charAt(i + 1) == '^')
						throw new ArithmeticException("Invalid monom!");
				}
				foundNumber = true;
				if (flag == 0) {

					x *= 10;
					x += s.charAt(i) - '0';
				} else if (flag == 1) {
					y *= 10;
					y += s.charAt(i) - '0';
					div *= 10;
				} else {
					if (i < s.length() - 1) {
						if (s.charAt(i + 1) == '.')
							throw new ArithmeticException("Invalid monom!");
					}
					z *= 10;
					z += s.charAt(i) - '0';
				}
			} else {
				throw new ArithmeticException("Invalid monom!");
			}
		}

		y /= div;
		x += y;

		if (isNegative) {
			x *= -1.0;
		}

		this.set_coefficient(x);
		this.set_power(z);

	}

	
	/**
	 * add m to this Monom
	 * @param m represent monom
	 */
	public void add(Monom m) {
		double new_Cofficient = 0;
		if (m.get_power() == this.get_power()) {
			new_Cofficient = m.get_coefficient() + this.get_coefficient();

			this.set_coefficient(new_Cofficient);
		} else {
			throw new RuntimeException(" The powers are different ! ");
		}

	}
/**
 * multiply d with this monom
 * @param d represent monom
 */
	public void multipy(Monom d) {
		double co = 0;
		int po = 0;
		if (d.get_power() >= 1 && this.get_power() >= 1) {
			co = d.get_coefficient() * this.get_coefficient();
			po = d.get_power() + this.get_power();
		}
		if ((d.get_power() == 0 && this.get_power() > 0) || (d.get_power() > 0 && this.get_power() == 0)) {
			co = d.get_coefficient() * this.get_coefficient();
			if (d.get_power() == 0) {
				po = this.get_power();
			}

			else {
				po = d.get_power();
			}
		}
		if (d.get_power() == 0 && this.get_power() == 0) {
			co = d.get_coefficient() * this.get_coefficient();
			po = 0;
		}
		if (this.get_coefficient() == 0 || d.get_coefficient() == 0) {
			co = 0;
			po = 0;
		}
		this.set_coefficient(co);
		this.set_power(po);
	}
/**
 * The function return String that represent this monom 
 */
	public String toString() {
		if (_power == 0) {
			return Double.toString(_coefficient);
		}
		String ans = "";
		ans += Double.toString(_coefficient);
		if (_power == 1) {
			return ans + "x";
		}
		ans += "x^" + Integer.toString(_power);
		return ans;
	}
	
	/**
	 * The function check if this monom equal to m
	 * @param m represent monom
	 * @return true if they equal and false if they not
	 */
	
	public boolean equals (Monom m) {
		if(m.isZero()&&this.isZero())return true;
		if(m==null||this==null)return false;
		double different= Math.abs(m.get_coefficient()-this.get_coefficient());
		return ((m.get_coefficient()==this.get_coefficient())&&(m.get_power()==this.get_power())&&different<=Monom.EPSILON);
	}
	
	
	// you may (always) add other methods.

	// ****************** Private Methods and Data *****************

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
