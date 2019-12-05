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
		String [] p1= {"3x^2-6x^3+9x-2","-4x^2-5","-x^3-x^3+5x-4","4x-2+3x^4","1.1x"};
		String [] p2= {"x+5x-5","-4x^2-5","x^2-1+0-4x^4","2-3x^4-4x","2.3x"};
		String [] polynomExpected= {"-6.0x^3+3.0x^2+15.0x-7.0","-8.0x^2-10.0","-4.0x^4-2.0x^3+1.0x^2+5.0x-5.0","0","3.4x"};
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
		Monom positive=new Monom(2,2);//2.0x^2 //case i=0
		Monom negative=new Monom (-2,2);//-2.0x^2 //case i=1
		Monom Mdouble=new Monom (2.2,3);//2.2x^3 //case i=2
		Monom num=new Monom (2,0);//2.0 //case i=3
		Monom zero=new Monom (0,10);//0.0 //case i=4
		Monom reset=new Monom(-5,1);//-5.0x //case i=5
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
		String [] p1= {"3x^2-6x^3+9x-2","-x^3-x^3+5x-4","-4x^2-5","2.2x"};
		String [] p2= {"x+5x-5","4x^2-5-x","x^2-1+0-4x^4-x","1.1x"};
		String [] polynomExpected= {"-6.0x^3+3.0x^2+3.0x+3.0","-2.0x^3-4.0x^2+6.0x+1.0","4.0x^4-5.0x^2+1.0x-4.0","1.1x"};
		for (int i = 0; i < p1.length; i++) 
		{
			Polynom actual = new Polynom (p1[i]);
			Polynom subBy = new Polynom (p2[i]);
			Polynom expected = new Polynom (polynomExpected[i]);
			actual.substract(subBy);
			assertEquals(expected, actual);
		}
	}

	@Test
	void testMultiplyPolynom_able() {
		String [] p1= {"3x^2-6x^3+9x-2","4x^2-5","-x^3-x^3+5x-4","2.2x^2+3.5"};
		String [] p2= {"x+5x-5","4x^2-5","x^2-1+0-4x^4-x","1.2x+1"};
		String [] polynomExpected= {"-36.0x^4+48.0x^3+39.0x^2-57.0x+10.0","16.0x^4-40.0x^2+25.0","8.0x^7-22.0x^5+18.0x^4+7.0x^3-9.0x^2-1.0x+4.0","2.64x^3+2.2x^2+4.2x+3.5"};
		for (int i = 0; i < p1.length; i++) 
		{
			Polynom actual = new Polynom (p1[i]);
			Polynom mulBy = new Polynom (p2[i]);
			Polynom expected = new Polynom (polynomExpected[i]);
			actual.multiply(mulBy);
			assertEquals(expected, actual);
		}
	}


	@Test
	void testEqualsObject() {
		String [] polynomActual= {"4x+5x^2","-2-5x^4+6.4x","4x^20","2x+3x"};
		String [] polynomExpected= {"5x^2+4x","-5x^4+6.4x-4+2","4x^20","5x"};
		for (int i = 0; i < polynomExpected.length; i++) 
		{
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomExpected[i]);
			if(!actual.equals(expected))
			{
				fail();
			}
			
		}
	}

	@Test
	void testIsZero() {
		String [] zero= {"0","0x^2","2x-2x+1-1"};
		String [] notZero= {"5x^2","5x","5-4.99"};
		boolean t=true;
		boolean f=false;
		for (int i = 0; i < zero.length; i++)
		{
			Polynom actual1=new Polynom (zero[i]);
			Polynom actual2=new Polynom (notZero[i]);
			boolean tExpected=actual1.isZero();
			boolean fExpected=actual2.isZero();
			assertEquals(t,tExpected);
			assertEquals(f,fExpected);
		}
	}

	@Test
	void testRoot() {
		Polynom p1=new Polynom ("x^2-2x-8");
	    double expected []= {-2.0001220703125,-1.9998779296875};
	    double x []= {-1,-4,1,-4};
	    for (int i = 0; i < expected.length; i++) 
	    {
	    	double actual=p1.root(x[i], x[i+1], 0.001);
			assertEquals(expected[i], actual);
		}
	    try 
	    {
	    	p1.root(1, 4, 0.001);//f(x0)* f(x1) must be bigger from 0
	    	fail();
	    }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	void testCopy() {
		Polynom actual=new Polynom ("5x^2+4x-3");
		//copy
		Polynom_able expected = actual.copy();
		assertEquals(expected, actual);
		//deep copy
		actual.add(new Monom("x^2"));
		assertNotEquals(expected, actual); //notEquals
	}

	@Test
	void testDerivative() {
		String [] polynomActual= {"2.2x^3+x^2-x+3","-4x^2-5.6","7x","x^2-1+0-5x^4","3.3x-4.4+2.2x-10.5x^5"};
		String [] polynomExpected= {"6.6x^2+2.0x-1.0","-8.0x","7.0","-20.0x^3+2.0x","52.5x^4+5.5"};
		for (int i = 0; i < polynomExpected.length; i++)
		{
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomActual[i]);
			actual.derivative();
			assertEquals(expected, actual);
		}
	}
	@Test
	void testArea() {
		fail("Not yet implemented");
	}
	@Test
	void testMultiplyMonom() {
		String [] polynomActual= {"x^3+x^2-x+3.2","-4x^2-5+0.2x","4x^3-2.4","x^2-1+0"};
		Monom positive=new Monom(2,2);//2.0x^2 //case i=0
		Monom negative=new Monom (-2,2);//-2.0x^2 //case i=1
		Monom Mdouble=new Monom (2.2,3);//2.2x^3 //case i=2
		Monom num=new Monom (2,0);//2.0 //case i=3
//		Monom zero=new Monom (0,10);//0.0 //case i=4

		String [] polynomExpected= {"2.0x^5+2.0x^4-2.0x^3+6.4x^2","8.0x^4-0.4x^3+10.0x^2","8.8x^6-5.28x^3","2.0x^2-2.0"};
		for (int i = 0; i < polynomActual.length; i++) {
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomExpected[i]);
			switch(i) {
			case 0:
				actual.multiply(positive);
				assertEquals(actual,expected);
				break;
			case 1:
				actual.multiply(negative);
				assertEquals(actual,expected);
				break;
			case 2:
				actual.multiply(Mdouble);
				assertEquals(actual,expected);
				break;
			case 3:
				actual.multiply(num);
				assertEquals(actual,expected);
				break;
//			case 4:
//				actual.multiply(zero);
//				assertEquals(actual,expected);
//				break;
			default:
			}
		}
	}
	@Test
	void initFromString() {
//		Polynom actual=new Polynom ("2-4x^3+2.2x+4x^2-4x^1+3x^10");
//		Polynom expected=new Polynom ("3.0x^10-4.0x^3+4.0x^2-4.0x+2.0");
//		actual.toString();
//		assertEquals(expected, actual);
	}
}
