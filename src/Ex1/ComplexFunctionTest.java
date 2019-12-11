package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {


	@Test
	void testComplexFunction() {
		String [] op= {"plus","mul","div","max","min","comp"};
		String [] polynomLeft= {"2x^2-x+1.5"};
		String [] polynomRight= {"-x^4+3.5x+1"};
		String [] expected= {"plus(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)","mul(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)","div(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)","max(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)","min(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)","comp(2.0x^2-1.0x+1.5,-1.0x^4+3.5x+1.0)"};
		for (int i = 0; i < 1; i++) 
		{
			String opi=op[i];
				Polynom l=new Polynom(polynomLeft[0]);
				Polynom r=new Polynom(polynomRight[0]);
				ComplexFunction actual=new ComplexFunction(opi,l,r);
				assertEquals(expected[i], actual.toString());
		}
		String [] wrongOp= {"Times","none","+"}; // none refer to none on 2 functions
		for (int i = 0; i < wrongOp.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[0]);
			Polynom r=new Polynom(polynomRight[0]);
			try
			{
			
				ComplexFunction wrongCf=new ComplexFunction(wrongOp[i],l,r);
				fail();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Test
	void testF() {
		fail("Not yet implemented");
	}

	@Test
	void testInitFromString() {
		String [] op= {"plus",};
		String [] polynomLeft= {"2x^2-x+1.5","-1.2x+1","5"};
		String [] polynomRight= {"-x^4","4x^3","5.2x+1"};
		Polynom l=new Polynom(polynomLeft[0]);
		Polynom r=new Polynom(polynomRight[0]);
		ComplexFunction cf=new ComplexFunction(op[0],l,r);
		assertEquals(cf, cf.initFromString("plus(2x^2-x+1.5,-x^4)"));
		for (int i = 1; i < op.length; i++)
		{
			Polynom le=new Polynom(polynomLeft[i]);
			Polynom ri=new Polynom(polynomRight[i]);
			ComplexFunction cf1=new ComplexFunction(op[i],le,ri);
			cf.mul(cf1);
		}
	}



	@Test
	void testCopy() {
		Polynom l=new Polynom("x^2+2x-1");
		Polynom r=new Polynom("5x-1+0");
		ComplexFunction actual=new ComplexFunction("plus",l,r);
		function expected=actual.copy();
		assertEquals(expected, actual);
	}

	@Test
	void testPlus() {
		fail("Not yet implemented");
	}

	@Test
	void testMul() {
		fail("Not yet implemented");
	}

	@Test
	void testDiv() {
		fail("Not yet implemented");
	}

	@Test
	void testMax() {
		fail("Not yet implemented");
	}

	@Test
	void testMin() {
		fail("Not yet implemented");
	}

	@Test
	void testComp() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented");
	}
}
