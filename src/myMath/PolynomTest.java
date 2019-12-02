package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTest {

	@Test
	void testPolynomString() {
		String [] polynomActual= {"4x+5x^2","-2-5x^4+6.4x","0x-0+4x^20"};
		String [] polynomExpected= {"4.0x+5.0x^2","-2.0-5.0x^4+6.4x","4x^20"};
		for (int i = 0; i < polynomExpected.length; i++) 
		{
			Polynom actual=new Polynom (polynomActual[i]);
			Polynom expected=new Polynom (polynomExpected[i]);
			assertEquals(expected.toString(), actual.toString());
		}
		String [] wrongPolynom= {"x^-5","polynom","--5","++5","x^0.5"};
		for (int i = 0; i < wrongPolynom.length; i++) {
			try
			{
				Polynom wrong=new Polynom (wrongPolynom[i]);
				fail();// if the wrong polynom was accepted
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
		for (int i = 0; i < expected.length; i++) {
			Polynom p=new Polynom (polynomActual[i]);
			double actual=p.f(xValue[i]);
			assertEquals(expected[i], actual);
		}
	}

	@Test
	void testAddPolynom_able() {

	}

	@Test
	void testAddMonom() {

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
		fail("Not yet implemented");
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
