package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testGetComp() {
	
	}

	@Test
	void testMonomDoubleInt() {
		fail("Not yet implemented");
	}

	@Test
	void testMonomMonom() {
		fail("Not yet implemented");
	}

	@Test
	void testGet_coefficient() {
		
		 int [] coefficient= {0,1,2,3,4,5,6,7,11};
		 for(int i=0; i<coefficient.length; i++) 
	     {
	    	 Monom m= new Monom(coefficient[i],3);
	    	 assertEquals(coefficient[i],m.get_coefficient());
	    	 
	     }
	}

	@Test
	void testGet_power() {
     int [] powers= {0,1,2,3,4,5,6,7,11};
     
     for(int i=0; i<powers.length; i++) 
     {
    	 Monom m= new Monom(2,powers[i]);
    	 assertEquals(powers[i],m.get_power());
     }
	}

	@Test
	void testDerivative() {
		String [] monoms= {"2x", "3x^4", "2", "-2x", "-3x^3", "5x^0", "6x^1","1.2x","3.5x^2"};
		String [] expected= {"2.0","12.0x^3", "0.0", "-2.0","-9.0x^2", "0.0", "6.0","1.2","7.0x"};
		String [] output= new String [9];
		
		for(int i=0; i<monoms.length; i++)
		{
			Monom DeriMonomBefore = new Monom (monoms[i]);
			Monom DeriMonomAfter= DeriMonomBefore.derivative();
			output[i]= DeriMonomAfter.toString();
			
			assertEquals(expected[i], output[i]);
		}
		
	}

	@Test
	void testF() {
	

		int [][] monom = {{3,5}, {1,2} ,{-6,1}, {0,2}, {8,0}, {-2,3}};
		int [] x = {0,1,-2,2,10};
		double [] [] res = {{0.0,3.0,-96.0,96.0,300000.0},{0.0,1.0,4.0,4.0,100.0},
				{0.0,-6.0,12.0,-12.0,-60.0},{0.0,0.0,0.0,0.0,0.0},{8.0,8.0,8.0,8.0,8.0},{0.0,-2.0,16.0,-16.0,-2000.0}};				
		
		for(int i=0; i<monom.length ;i++)
		{
			Monom m= new Monom(monom[i][0],monom[i][1]);
			System.out.println(m.toString());
			for(int j=0; j<x.length; j++) 
			{
				double re=m.f(x[j]);
				System.out.println(+x[j]+": expection : " + res[i][j] + " actual : " +re );
				 assertEquals( res[i][j], re);
		
			}
			
			
		}
		
	}	
	

	@Test
	void testIsZero() {
		int [][] monom = {{0,5}, {0,2} ,{-0,1}, {0,2}, {0,0}, {0,3}};
		for(int i=0; i<monom.length ;i++)
		{
			
			Monom m= new Monom(monom[i][0],monom[i][1]);
	    	assertEquals(true,m.isZero());
	    	
	    }
		
                 	}    
	
	@Test
	void testMonomString() {
     

		String [] monomActual= {"8x^0","-7x^2","x^5","1.2x","45.56x^65","0x","9","7.3x^8","7x^3"};
	    String [] monomExpected = {"8.0","-7.0x^2","1.0x^5","1.2x","45.56x^65","0.0","9.0",
			
	     "7.3x^8","7.0x^3"};
	    
	    
    for(int i=0; i<monomActual.length; i++)
    {
    	Monom actual= new Monom(monomActual[i]);
    
    	assertEquals(monomExpected[i],actual.toString());
    }
		 String [] wrongMo = {"bx^3","3x^-3","xt^3","5x^0.5","-7X^2","3^2","4x^-6","--3x^2",
				"4x^-3.5"};
		for(int i=0; i<wrongMo.length; i++)
		{
			try
			{
				Monom wrong= new Monom(wrongMo[i]);
				
				fail("Invalid monom: negative power/ fraction power/ char invalid");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} 
		}
	

	@Test
	 void testAdd() {
        String [] st1 ={"1.2x","5","3x^2","9.5","6x^7"};
        String [] st2 ={"8x","3","-8x^2","-9x^0","-6x^7"};

        String [] ans = {"9.2x","8.0","-5.0x^2","0.5","0.0"};

        for (int i = 0; i <st1.length; i++)
        {
            Monom p_1 = new Monom(st1[i]);
            Monom p_2 = new Monom(st2[i]);
            Monom ansM = new Monom(ans[i]);
            p_1.add(p_2);
            assertEquals(ansM.toString(), p_1.toString());
        }
       
        String [] wrong1 = {"bx^3","0x", "8x","3x^-3","bx^3","5x^0.5","7x^2","6x^5"};
        String [] wrong2= {"7x^3","5x","7x^3","7x^-3","8x^3","3x^0.5","2x^2.5","8x^7.3"};
        for(int i=0; i<wrong1.length; i++)
        {
        	try
        	{
        		Monom wro1= new Monom (wrong1[i]);
        		Monom wro2= new Monom (wrong2[i]);
        		wro1.add(wro2);
        		new Monom (wro1);
        		
        		fail("wrong input"); // if the add was illegal
        	}
        	catch (Exception e) 
        	{
        		e.printStackTrace();
        	}
        } 
	}

	@Test
	void testMultipy() {
		
		String [] st1 ={"10.5x^2","5x^3","2x^0","0","-4x^2","6x^2","-7x^0","-7x^0","3x^0","-5x^0"};
        String [] st2 ={"2.5x^3","1.2","3x^4","5x","-2x","-2","2","2x","2x^0","7x^0"};

        String [] ans = {"26.25x^5","6.0x^3","6.0x^4","0.0","8.0x^3","-12.0x^2","-14.0","-14.0x",
        		"6.0", "-35.0"};
        
        for (int i = 0; i <st1.length; i++)
        {
            Monom p1 = new Monom(st1[i]);
            Monom p2 = new Monom(st2[i]);
            Monom ans_M = new Monom(ans[i]);
            p1.multipy(p2);
            assertEquals(ans_M.toString(), p1.toString());
        }
        String [] wrong_1 = {"bx^3","0x", "8x","3x^-3","xc^3","5x^z","7x^2","6x^5"};
        String [] wrong_2= {"7x^3","5x^-3","7x^0.2","7xv^3","8x^3","3x^5","2x^2.5","8x^7.3"};
        for(int i=0; i<wrong_1.length; i++)
        {
        	try
        	{
        		Monom wro1= new Monom (wrong_1[i]);
        		Monom wro2= new Monom (wrong_2[i]);
        		wro1.multipy(wro2);
        		Monom wroRes= new Monom (wro1);
        		
        		fail("Invalid monom"); // if the multiply was illegal
        	}
        	catch (Exception e) 
        	{
        		e.printStackTrace();
        	}
        } 
        
        
	}

	@Test
	void testToString() {
		
	String [] st1= {"3.0x" ,"2.0x^8", "6.0", "1.0x^2", "-10.0x", "0.0"};
	
	int [][] st2= {{3,1},{2,8},{6,0},{1,2},{-10,1},{0,0}};
	
	String [] expected= new String [6];
	
	for(int i=0; i<st2.length; i++)
	{
		Monom m = new Monom(st2[i][0],st2[i][1]);
		expected[i]= m.toString();
		
	assertEquals(expected[i],st1[i]);
	}
	}
	
	@Test
	void testEqualsMonom() {
	
		String [] st1= {"2x^3","-4x^0","88x","-69.98x^1","-22x^4"};
		String [] st2= {"2.0x^3","-4x^0","88.0x","-69.98x^1","-22.0x^4"};
		
		for(int i=0; i<st1.length; i++)
		{
			Monom m1= new Monom(st1[i]);
			Monom m2= new Monom(st2[i]);
			
			assertEquals(m1.toString(),m2.toString());
		}
	}

}