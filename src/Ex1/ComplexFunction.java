package Ex1;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation operation;
	public static final double EPSILON = 0.0000001; // for equals
	public ComplexFunction(Operation operation,function left, function right)
	{
		this.left = left.copy();
		this.right = right.copy();
		this.operation = operation;
	}
	//	defoult constractor to test initfromstring
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
		if(left!=null)this.left = left.copy();
		if(right!=null)this.right = right.copy();
		//		this.operation=Operation.valueOf(operation);

		switch(operation.toLowerCase())
		{
		case "plus":
			this.operation=Operation.Plus;
			break;
		case "mul":
			this.operation=Operation.Times;
			break;
		case "div":
			this.operation=Operation.Divid;
			break;
		case "max":
			this.operation=Operation.Max;
			break;
		case "min":
			this.operation=Operation.Min;
			break;
		case "comp":
			this.operation=Operation.Comp;
			break;
		case "none":
			this.operation=Operation.None;
			if(this.left!=null&&this.right!=null)//if there is a left and right without operation
			{
				throw new RuntimeException("None operation on functions");
			}
			break;
		default:
			this.operation=Operation.Error;
			throw new RuntimeException("Illegal Operation");
		}
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


		default: 
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
			int comma=findComma(s, start); // index of ',' between left and right
			function l=initFromString(s.substring(start+1, comma)); // recursive call of left function
			function r=initFromString(s.substring(comma+1,s.length()-1)); // recurcive call of right function
			f=new ComplexFunction(op,l,r);
		}
		return f;
	}
	// help function to find the comma between left and right
	public int findComma(String s,int start){
		int iComma=0; // index of comma
		int open=1; //sum of '('
		int i=start+1; // start after first '('

		while(i<s.length()&&open!=iComma) 
		{
			if(s.charAt(i)=='(')open++;
			if(s.charAt(i)==',')iComma++;
			if(open==iComma)
			{
				iComma=i;
				break;
			}
			i++;
		}
		return iComma;
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
			temp =  new ComplexFunction (this.operation, this.left(),this.right());
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
	public boolean equals(Object obj) {
		boolean flag=true;

		if(obj instanceof ComplexFunction) // if object is Complexfunction type
		{
			ComplexFunction cf= (ComplexFunction) obj;
			if(cf.operation==this.operation&&cf.left==this.left&&cf.right==this.right)flag=true;
			double x;//value of x
			for (x=-15; x <= 15; x+=0.1) 
			{
				if(Math.abs(this.f(x)-cf.f(x))>EPSILON)return false;	// EPSILON=0.0000001	
			}
		}
		return flag;
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
		String opera ="";
		if(this.operation.name()!="None")
		{
			switch(this.operation.name()) {
			case "Plus":
				opera="plus";
				break;
			case "Times":
				opera="mul";
				break;
			case "Divid":
				opera="div";
				break;
			case "Max":
				opera="max";
				break;
			case "Min":
				opera="min";
				break;
			case "Comp":
				opera="comp";
				break;
			}
		}
		if(this.left!=null&&this.right!=null)
		{
			s.append(opera+"("+this.left+","+this.right+")"); // operator with 2 functions
		}
		if(this.left!=null&&this.right==null) // left function only = left
		{
			s.append(this.left);
		}
		if(this.left==null&&this.right!=null) // right function only = right
		{
			s.append(this.right);
		}

		return s.toString();
	}
	private String removeSpace(String str) 
	{ 
		str = str.replaceAll("\\s",""); 
		return str; 
	} 



	public static void main(String[] args) {

		Polynom p3 = new Polynom("x+1");
		Polynom p4 = new Polynom("x+1");
		ComplexFunction cf3 = new ComplexFunction("plus",p3,p3);
//		ComplexFunction cf5 = new ComplexFunction("plus",p3,p4);
//		System.out.println(cf3.equals(cf5));
						System.out.println("1:"+cf3);
						
						ComplexFunction cf4 = new ComplexFunction("mul", p3,p3); 
						System.out.println("2:"+cf4);
		//				cf3.plus(cf4);
		//				System.out.println("3:"+cf3);
		//				cf3.plus(cf4);
		//				System.out.println("4:"+cf3);//not good..need to check
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
		//		function cff = new ComplexFunction();
		//		System.out.println(cff);
		//		function cff2 = cff.initFromString("div(plus(1,2),3)");
		//		System.out.println(cff2);

	}

}