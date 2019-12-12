package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {


	@Test
	void testComplexFunction() {
		String [] op= {"plus","mul","div","max","min","comp"};
		String [] polynomLeft= {"2x^2-x+1.5"};
		String [] polynomRight= {"-x^4+3 .5x  + 1"};//spaces
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
				fail("jkj");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Test
	void testF() {
		String [] op = {"plus","mul","plus","div","max","min","comp"};
		String [] polynomLeft= {"2x^2-x+1.5","1.2x+1","5x^10-4x+7","4x^2+x-1","2x^2-2x","-4x^2","2x+2.2"};
		String [] polynomRight= {"-x^4","4x^3+5-0","-5x^10+4x-7","2x^2+2","2x^3-2x+1","-11.88x","2.2x^2-2"};
		double [] expected= {-64.5,519.8,0.0,1.9,49.0,-36.0,37.800000000000004};
		for (int i = 0; i < expected.length; i++) 
		{	
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			String opi=op[i];
			ComplexFunction cf=new ComplexFunction(opi,l,r);
			double actual=cf.f(3);
			assertEquals(expected[i], actual);
		}


	}

	@Test
	void testInitFromString() {

		String [] polynomLeft= {"2x^2-x+1.5","-1.2x+1"};
		String [] polynomRight= {"-x^4","4x^3"};

		Polynom l=new Polynom(polynomLeft[0]);
		Polynom r=new Polynom(polynomRight[0]);
		ComplexFunction cf=new ComplexFunction("Plus",l,r);
		function cf1=cf.initFromString("plus(2x^2-x+1.5,-x^4)");
		assertEquals(cf, cf1);
		cf.mul(cf1);
		cf1=cf.initFromString("mul(plus(2x^2-x+1.5,-x^4),plus(2x^2-x+1.5,-x^4))");
		assertEquals(cf, cf1);
		cf.div(cf1);
		cf1=cf.initFromString("div(mul(plus(2.0x^2-1.0x+1.5,-1.0x^4),plus(2.0x^2-1.0x+1.5,-1.0x^4)),mul(plus(2.0x^2-1.0x+1.5,-1.0x^4),plus(2.0x^2-1.0x+1.5,-1.0x^4)))");
		assertEquals(cf, cf1);
		//
		Polynom le=new Polynom(polynomLeft[1]);
		Polynom ri=new Polynom(polynomRight[1]);
		ComplexFunction cf3=new ComplexFunction("comp",le,ri);
		function cf4=cf3.initFromString("comp(-1.2x+1,4x^3)");
		assertEquals(cf4, cf3);
		cf3.max(cf4);
		cf1=cf.initFromString("max(comp(-1.2x+1,4x^3),comp(-1.2x+1,4x^3))");
		assertEquals(cf3, cf4);
		cf3.min(cf4);
		cf4=cf3.initFromString("min(max(comp(-1.2x+1,4x^3),comp(-1.2x+1,4x^3)),max(comp(-1.2x+1,4x^3),comp(-1.2x+1,4x^3)))");
		assertEquals(cf3, cf4);
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
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"plus(plus(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),plus(plus(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))","plus(plus(2.0x^3-1.2x+1.0,4.0x^3),plus(plus(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))","plus(plus(1.0x^4-5.3,1.0x^4-2.3),plus(plus(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("plus",l,r);
			cf.plus(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testMul() {
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"mul(mul(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),mul(mul(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))","mul(mul(2.0x^3-1.2x+1.0,4.0x^3),mul(mul(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))","mul(mul(1.0x^4-5.3,1.0x^4-2.3),mul(mul(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("mul",l,r);
			cf.mul(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testDiv() {
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"div(div(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),div(div(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))",
				"div(div(2.0x^3-1.2x+1.0,4.0x^3),div(div(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))",
		"div(div(1.0x^4-5.3,1.0x^4-2.3),div(div(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("div",l,r);
			cf.div(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testMax() {
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"max(max(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),max(max(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))",
				"max(max(2.0x^3-1.2x+1.0,4.0x^3),max(max(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))",
		"max(max(1.0x^4-5.3,1.0x^4-2.3),max(max(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("max",l,r);
			cf.max(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testMin() {
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"min(min(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),min(min(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))",
				"min(min(2.0x^3-1.2x+1.0,4.0x^3),min(min(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))",
		"min(min(1.0x^4-5.3,1.0x^4-2.3),min(min(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("min",l,r);
			cf.min(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testComp() {
		String [] polynomLeft= {"2x^2-x+1.5","2x^3-1.2x+1","x^4-5.3"};
		String [] polynomRight= {"-2x^2+x-1.5","4x^3","x^4-2.3"};
		String [] expected= {"comp(comp(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),comp(comp(2.0x^2-1.0x+1.5,-2.0x^2+1.0x-1.5),-2.0x^2+1.0x-1.5))","comp(comp(2.0x^3-1.2x+1.0,4.0x^3),comp(comp(2.0x^3-1.2x+1.0,4.0x^3),4.0x^3))","comp(comp(1.0x^4-5.3,1.0x^4-2.3),comp(comp(1.0x^4-5.3,1.0x^4-2.3),1.0x^4-2.3))"};
		for (int i = 0; i < expected.length; i++) 
		{
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			ComplexFunction cf=new ComplexFunction("comp",l,r);
			cf.comp(cf);
			assertEquals(expected[i], cf.toString());

		}
	}

	@Test
	void testEqualsObject() {
		String [] op = {"plus","mul","plus","div","comp"};
		String [] polynomLeft= {"4x^2-2.3","-5x+0.5","-x+x+2x^4","66x^1+2x","2x^3-2x^2-2.1x"};
		String [] polynomRight= {"3","2x+1","-2x-3.5","3.2x^2","0"};
		
		for (int i = 0; i < polynomLeft.length; i+=2) 
		{	
			Polynom l=new Polynom(polynomLeft[i]);
			Polynom r=new Polynom(polynomRight[i]);
			String opi=op[i];
			ComplexFunction actual=new ComplexFunction(opi,l,r);
			ComplexFunction expected=new ComplexFunction(opi,l,r);
			if(!actual.equals(expected))
			{
				fail("The objects are equals");
			}
			function f=new ComplexFunction(opi,l,r);
			actual.mul(f);
			expected.mul(f);
			if(!actual.equals(expected))
			{
				fail("The objects are equals");
			}
			actual.div(f);
			expected.div(f);
			if(!actual.equals(expected))
			{
				fail("The objects are equals");
			}
			actual.max(f);
			expected.max(f);
			if(!actual.equals(expected))
			{
				fail("The objects are equals");
			}
			actual.comp(f);
			expected.comp(f);
			if(!actual.equals(expected))
			{
				fail("The objects are equals");
			}
			actual.plus(f);
			expected.min(f);
			if(actual.equals(expected))
			{
				fail("The objects are not equals");
			}
		}
	}
}
