package myMath;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation operation;

	public ComplexFunction(Operation operation,function left, function right)
	{
		this.left = left;
		this.right = right;
		this.operation = operation;
	}
	public ComplexFunction()
	{
		this.left =null;
		this.right = null;
		this.operation = Operation.None;	
	}


	public ComplexFunction(function f)
	{
		this.left = f;
		this.right = null;
		this.operation = Operation.None;	
	}

	public ComplexFunction(String operation,function left, function right)
	{	
		this.left = left;
		this.right = right;
		this.operation=Operation.valueOf(operation);
//		if(this.operation.name()=="None")this.setOp(Operation.valueOf("Comp"));
		if(this.operation.name()=="Error")throw new RuntimeException("Illegal Operation");


	}


	@Override
	public double f(double x) {

		String  opera = this.operation.name();
		switch (opera)
		{
		case "Plus" :
			return this.left().f(x)+ this.right().f(x);

		case "Times" :
			return this.left().f(x) * this.right().f(x);

		case "Divid" :
			return this.left().f(x) / this.right().f(x);

		case "Max" :
			if (this.left().f(x) > this.right().f(x))	{return this.left().f(x);}
			else
			{
				return this.right().f(x);
			}

		case "Min" :
			if (this.left().f(x) < this.right().f(x)) {return this.left().f(x);}
			else 
			{
				return this.right().f(x);
			}

		case "Comp" :
			return this.left().f( this.right().f(x));

		case "None" :
			return this.left().f(x);
		case "Error":
			throw new RuntimeException("Illegal Operation");


		default: //case "Error"
			throw new RuntimeException("Illegal Operation");

		}
	}

	@Override
	public function initFromString(String s) {
		function f;
		s=removeSpace(s);
		if(s.indexOf('(')==-1&&s.indexOf(')')==-1)//base case- without operator and complex function (just Monom or Polynom)
		{
			Polynom p=new Polynom(s);
			function l=p;
			return l.initFromString(s);
		}
		else
		{
			int start=s.indexOf('(');// index of open '('
			int end=s.indexOf(')');// index of close ')'
			String op = s.substring(0, start); // save operation
			int comma=s.indexOf(','); // index of ','
			function l=initFromString(s.substring(start+1, comma)); // recursive call of left function
			function r=initFromString(s.substring(comma+1,s.length()-1)); // recurcive call of right function
			f=new ComplexFunction(op,l,r);
		}
				return f;
	}

	@Override
	public function copy() {

		ComplexFunction temp;
		if(this.right== null)
		{
			temp= new ComplexFunction (left);
		}
		else
		{
			temp =  new ComplexFunction (this.operation.name(), this.left(),this.right());
		}
		return temp ;
	}

	@Override
	public void plus(function f1) {

		ComplexFunction temp;
		if(this.right()!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());
			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Plus"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Plus"));
		}
	}

	@Override
	public void mul(function f1) {


		ComplexFunction temp;
		if(this.right!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());
			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Times"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Times"));
		}	
	}

	@Override
	public void div(function f1) {

		ComplexFunction temp;
		if(this.right!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());

			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Divid"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Divid"));
		}
	}

	@Override
	public void max(function f1) {

		ComplexFunction temp;
		if(this.right!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());

			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Max"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Max"));
		}
	}

	@Override
	public void min(function f1) {

		ComplexFunction temp;
		if(this.right!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());

			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Min"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Min"));
		}
	}

	@Override
	public void comp(function f1) {

		ComplexFunction temp;
		if(this.right!= null)
		{
			temp = new ComplexFunction (this.operation,this.left.copy(),this.right.copy());

			this.setLeft(temp);
			this.setRight(f1.copy());
			this.setOp(Operation.valueOf("Comp"));
		}
		else
		{
			this.right=f1.copy();
			this.setOp(Operation.valueOf("Comp"));
		}

	}

	@Override
	public function left() {

		return this.left;
	}


	@Override
	public function right() 
	{
		return this.right;
	}


	public void setRight(function f) 
	{
		this.right= f;
	}


	public void setLeft(function f) 
	{
		this.left= f;
	}


	public void setOp(Operation op) 
	{
		this.operation= op;
	}


	@Override
	public Operation getOp() 
	{
		return this.operation;
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		if(this.operation.name()!=null)
		{
			String opera = this.operation.name();
			s.append(opera+"("+this.left+","+this.right+")");
		}
		return s.toString();
	}
	private String removeSpace(String str) 
	{ 
		str = str.replaceAll("\\s",""); 
		return str; 
	} 



	public static void main(String[] args) {

		Polynom p3 = new Polynom("x+3");
//		ComplexFunction cf3 = new ComplexFunction("Error",p3,p3);
		//		System.out.println("1:"+cf3);
		//		ComplexFunction cf4 = new ComplexFunction("Plus", p3,p3); 
		//		System.out.println("2:"+cf4);
		//		cf3.plus(cf4);
		//		System.out.println("3:"+cf3);
		//		cf3.plus(cf4);
		//		System.out.println("4:"+cf3);//not good..need to check
//		System.out.println(cf3);
		//		cf3.plus(cf3);
		//		System.out.println(cf3);


		//		System.out.println(cf4);
		//		cf4.plus(cf4);
		//		cf4.mul(cf4);
		//		cf4.div(cf4);
		//		cf4.min(cf4);
		//		cf4.max(cf4);
		//	ComplexFunction cf5=new ComplexFunction("Times",p3,p3);
		//			System.out.println(cf5);
            function cff = new ComplexFunction();
            System.out.println(cff);
           function cff2 = cff.initFromString("Plus(x,Plus(2x,x))");
           System.out.println(cff2);

	}

}