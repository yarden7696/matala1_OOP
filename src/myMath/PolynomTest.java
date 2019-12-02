package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTest {

	@Test
	void testPolynomString() {
		String [] polynomActual= {"4x+5x^2","-2-5x^4+6.4x","0x-0+4x^20","2x+3x"};
		String [] polynomExpected= {"4.0x+5.0x^2","-2.0-5.0x^4+6.4x","4x^20","5x"};
		for (int i = 0; i < polynomExpected.length; i++) 
		{
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomExpected[i]);
			assertEquals(expected.toString(), actual.toString());
		}
		String [] wrongPolynom= {"x^-5","polynom","--5","++5","x^0.5","2^3","5X"};
		for (int i = 0; i < wrongPolynom.length; i++) 
		{
			try
			{
				Polynom wrong=new Polynom (wrongPolynom[i]);
				fail("The polynom(string) accepted the wrong polynom, its not good!");// if the wrong polynom was accepted
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Test
	void testF() {
		String [] polynomActual= {"-x","-x^2","2x^3","5x^3-x+2x-x^2+0-2","5.2+3x-2x^20","x^2"};
		double [] xValue = {-4,-1,-1,0,1,3.5};
		double [] expected = {4.0,-1.0,-2.0,-2.0,6.2,12.25};
		for (int i = 0; i < expected.length; i++)
		{
			Polynom p=new Polynom (polynomActual[i]);
			double actual=p.f(xValue[i]);
			assertEquals(expected[i], actual);
		}
	}

	@Test
	void testAddPolynom_able() {
		String [] p1= {"3x^2-6x^3+9x-2","-4x^2-5","-x^3-x^3+5x-4","4x-2+3x^4"};
		String [] p2= {"x+5x-5","-4x^2-5","x^2-1+0-4x^4","2-3x^4-4x"};
		String [] polynomExpected= {"-6.0x^3+3.0x^2+15.0x-7.0","-8.0x^2-10.0","-4.0x^4-2.0x^3+1.0x^2+5.0x-5.0","0.0"};
		for (int i = 0; i < p1.length; i++) 
		{
			Polynom actual = new Polynom (p1[i]);
			Polynom addBy = new Polynom (p2[i]);
			Polynom expected = new Polynom (polynomExpected[i]);
			actual.add(addBy);
			assertEquals(expected.toString(), actual.toString());
		}
	}

	@Test
	void testAddMonom() {
		String [] polynomActual= {"x^3+x^2-x+3.2","-4x^2-5","4x^3-2","x^2-1+0","-7x^3+2","5x+2x-2x"};
		Monom positive=new Monom(2,2);//2.0x^2 //i=0
		Monom negative=new Monom (-2,2);//-2.0x^2 //i=1
		Monom Mdouble=new Monom (2.2,3);//2.2x^3 //i=2
		Monom num=new Monom (2,0);//2.0 //i=3
		Monom zero=new Monom (0,10);//0.0 //i=4
		Monom reset=new Monom(-5,1);//-5.0x //i=5
		String [] polynomExpected= {"x^3+3.0x^2-x+3.2","-6.0x^2-5.0","6.2x^3-2.0","x^2+1.0","-7.0x^3+2.0","0"};
		for (int i = 0; i < polynomActual.length; i++) {
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomExpected[i]);
			switch(i) {
			case 0:
				actual.add(positive);
				assertEquals(actual,expected);
				break;
			case 1:
				actual.add(negative);
				assertEquals(actual,expected);
				break;
			case 2:
				actual.add(Mdouble);
				assertEquals(actual,expected);
				break;
			case 3:
				actual.add(num);
				assertEquals(actual,expected);
				break;
			case 4:
				actual.add(zero);
				assertEquals(actual,expected);
				break;
			case 5:
				actual.add(reset);
				assertEquals(actual,expected);
				break;
				default:
			}
		}
	}

	@Test
	void testSubstract() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyPolynom_able() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	void testIsZero() {
		fail("Not yet implemented");
	}

	@Test
	void testRoot() {
		fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		Polynom actual=new Polynom ("5x^2+4x-3");
		Polynom expected=new Polynom ("5x^2+4x-3");
		actual.copy();
		assertEquals(expected, actual);
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented");
	}

	@Test
	void testArea() {
		fail("Not yet implemented");
	}

	@Test
	void testIteretor() {
		fail("Not yet implemented");
	}

	@Test
	void testMultiplyMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testPolynomOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented");
	}

}
